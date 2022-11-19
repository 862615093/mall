package com.ww.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ww.mall.bo.AdminUserDetails;
import com.ww.mall.dao.UmsAdminRoleRelationMapper;
import com.ww.mall.dto.UmsAdminParam;
import com.ww.mall.exception.Asserts;
import com.ww.mall.model.UmsResource;
import com.ww.mall.service.UmsAdminCacheService;
import com.ww.mall.service.UmsAdminService;
import com.ww.mall.dao.UmsAdminMapper;
import com.ww.mall.model.UmsAdmin;
import com.ww.mall.model.UmsAdminExample;
import com.ww.mall.util.JwtTokenUtil;
import com.ww.mall.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台管理业务层
 * @author wangwei
 */
@Service
@Slf4j
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        //1.查询是否存在同账户用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdminParam.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //2.不存在，则新增
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        String encodePwd = passwordEncoder.encode(umsAdminParam.getPassword());
        umsAdmin.setPassword(encodePwd);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String pwd) {
        //1.获取用户信息
        UserDetails userDetails = loadUserByUsername(username);
        //2.账户校验
        if(!passwordEncoder.matches(pwd, userDetails.getPassword())) {
            Asserts.fail("密码错误");
        }
        if(!userDetails.isEnabled()) {
            Asserts.fail("密码已被禁用");
        }
        //3.创建SpringSecurity中权限资源管理器
        // userDetails.getAuthorities() : 从AdminUserDetails获取用户资源
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken
                (userDetails, null, userDetails.getAuthorities());
        //存入本地变量 方便获取和存储
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //4.生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        log.info("登录用户生成token={}", token);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //1.获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (null == admin) throw new UsernameNotFoundException("用户名或密码错误");
        //2.获取用户资源信息
        List<UmsResource> resourceList = getResourceList(admin.getId());
        //返回自定义的 SpringSecurity中实现的UserDetails实现类
        return new AdminUserDetails(admin, resourceList);
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        //1.尝试缓存中获取用户信息
        UmsAdmin admin = getCacheService().getAdmin(username);
        if (null != admin) return admin;
        //2.数据库中获取
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
        if (umsAdmins != null && umsAdmins.size() > 0) {
            admin = umsAdmins.get(0);
            //3.存入redis
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        //1.缓存尝试获取用户资源权限
        List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        //2.从数据库中获取用户资源权限
        resourceList = adminRoleRelationMapper.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            //3.用户资源权限存入redis
            getCacheService().setResourceList(adminId, resourceList);
        }
        return resourceList;
    }
}
