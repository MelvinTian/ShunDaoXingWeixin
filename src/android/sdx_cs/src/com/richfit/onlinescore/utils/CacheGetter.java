package com.richfit.onlinescore.utils;

/**
 * 缓存获取接口
 * @author 田广文
 * @date 2012-1-12 下午12:15:37
 */
public interface CacheGetter<K, T>
{
    T getObject(K key);
}