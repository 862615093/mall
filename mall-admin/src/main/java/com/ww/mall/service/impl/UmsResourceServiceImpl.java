package com.ww.mall.service.impl;

import com.ww.mall.dao.UmsResourceMapper;
import com.ww.mall.model.UmsResource;
import com.ww.mall.model.UmsResourceExample;
import com.ww.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台资源管理业务层
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}
