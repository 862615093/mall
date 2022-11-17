package com.ww.mall.service;

import com.ww.mall.dto.UmsAdminParam;
import com.ww.mall.model.UmsAdmin;

/**
 * 后台管理 service层
 * @author wangwei
 */
public interface UmsAdminService {

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);
}
