package com.ww.mall.dao;

import com.ww.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色关系 mapper层
 *
 * @author wangwei
 */
public interface UmsAdminRoleRelationMapper {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);
}
