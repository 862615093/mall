package com.ww.mall.service.impl;

import com.ww.mall.dto.UmsAdminParam;
import com.ww.mall.service.UmsAdminService;
import com.ww.mall.dao.UmsAdminMapper;
import com.ww.mall.model.UmsAdmin;
import com.ww.mall.model.UmsAdminExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        //1.查询是否存在同账户用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdminParam.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //2.不存在，则新增
        String encodePwd = passwordEncoder.encode(umsAdminParam.getPassword());
        umsAdmin.setPassword(encodePwd);
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }
}
