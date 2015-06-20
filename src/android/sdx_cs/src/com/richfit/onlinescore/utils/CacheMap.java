
package com.richfit.onlinescore.utils;

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
	private final HashMap<K, SoftReference<MapEntity>> cacheMap = new HashMap<K, SoftReference<MapEntity>>();
	private final CacheGetter<K,T> getter;
	private final long cacheTime;

	public CacheMap(long cacheTime)
	{
		getter = null;
		this.cacheTime = cacheTime;
	}

	public CacheMap(CacheGetter<K, T> getter)
	{
		this.getter = getter;
		cacheTime = -1;
	}

	public T get(K key)
	{
		if (key == null)
			return null;
		SoftReference<MapEntity> ref = cacheMap.get(key);
		if (ref != null && ref.get() != null)
		{
			if (cacheTime < 0 || (System.currentTimeMillis() - ref.get().t > cacheTime))
			{
				return ref.get().v;
			}
		}
		if (getter != null)
		{
			T value = getter.getObject(key);
			if (value != null)
				cacheMap.put(key, new SoftReference<MapEntity>(new MapEntity(value)));
			return value;
		}
		return null;
	}

	public void addToCache(K k, T t)
	{
		if (t != null && k != null)
		{
			cacheMap.put(k, new SoftReference<MapEntity>(new MapEntity(t)));
		}
	}

	private class MapEntity
	{
		MapEntity(T v)
		{
			t = System.currentTimeMillis();
			this.v = v;
		}

		T v;
		long t;
	}
}