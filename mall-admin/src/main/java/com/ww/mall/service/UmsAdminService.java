package com.ww.mall.service;

import com.ww.mall.dto.UmsAdminParam;
import com.ww.mall.model.UmsAdmin;
import com.ww.mall.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台管理 service层
 * @author wangwei
 */
public interface UmsAdminService {

    /**
     * 获取缓存服务  不使用依赖注入方式
     */
    UmsAdminCacheService getCacheService();

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 用户登录
     * @param username 登录账户
     * @param pwd 密码
     * @return token
     */
    String login(String username, String pwd);

    /**
     * 获取用户信息
     * @param username 登录账号
     * @return security权限 用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户资源权限
     * @param adminId 用户ID
     * @return 资源权限
     */
    List<UmsResource> getResourceList(Long adminId);
}
