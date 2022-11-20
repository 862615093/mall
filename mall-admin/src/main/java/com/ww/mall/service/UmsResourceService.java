package com.ww.mall.service;

import com.ww.mall.model.UmsResource;

import java.util.List;

/**
 * 后台资源管理service层
 */
public interface UmsResourceService {

    /**
     * 查询全部资源
     */
    List<UmsResource> listAll();
}
