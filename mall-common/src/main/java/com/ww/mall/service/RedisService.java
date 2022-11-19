package com.ww.mall.service;

/**
 * redis 操作service层
 *
 * @author wangwei
 */
public interface RedisService {

    /**
     * 获取属性
     */
    Object get(String key);

    /**
     * 保存属性
     */
    void set(String key, Object value, long time);
}
