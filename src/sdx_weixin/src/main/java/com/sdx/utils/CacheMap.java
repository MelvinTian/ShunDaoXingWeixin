
package com.sdx.utils;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 缓存管理器
 * 
 * @author	田广文
 * @date	2012-1-12 下午12:03:00
 */
public class CacheMap<K,T>
{
	private final HashMap<K, SoftReference<T>> cacheMap = new HashMap<K, SoftReference<T>>();
	private final CacheGetter<K,T> getter;
	public CacheMap(CacheGetter<K, T> getter)
	{
		this.getter = getter;
	}

	public T get(K key)
	{
		if (key == null)
			return null;
		SoftReference<T> ref = cacheMap.get(key);
		if (ref == null || ref.get() == null)
		{
			T value = getter.getObject(key);
			if (value != null)
				cacheMap.put(key, new SoftReference<T>(value));
			return value;
		} else {
			return ref.get();
		}
	}
}