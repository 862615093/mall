package com.ww.mall.dao;

import com.ww.mall.model.UmsResource;
import com.ww.mall.model.UmsResourceExample;

import java.util.List;

public interface UmsResourceMapper {

    List<UmsResource> selectByExample(UmsResourceExample example);
}
