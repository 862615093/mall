package com.ww.mall.dao;

import com.ww.mall.model.UmsAdmin;
import com.ww.mall.model.UmsAdminExample;

import java.util.List;

/**
 * 后台用户管理 mapper层
 * @author wangwei
 */
public interface UmsAdminMapper {

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    int insert(UmsAdmin record);
}
