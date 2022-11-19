package com.ww.mall.service;

import com.ww.mall.model.UmsAdmin;
import com.ww.mall.model.UmsResource;

import java.util.List;

/**
 * 后台用户缓存 service层
 *
 * @author wangwei
 */
public interface UmsAdminCacheService {

    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}
