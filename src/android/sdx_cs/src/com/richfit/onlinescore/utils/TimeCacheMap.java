package com.richfit.onlinescore.utils;

import java.util.HashMap;

/**
 * 缓存管理器
 * 
 * @author 田广文
 * @date 2012-1-12 下午12:03:00
 */
public class TimeCacheMap<K, T> {
	private final HashMap<K, MapEntity> cacheMap = new HashMap<K, MapEntity>();
	private final long cacheTime;

	public TimeCacheMap(long cacheTime)
	{
		this.cacheTime = cacheTime;
	}

	public T get(K key)
	{
		if (key == null)
			return null;
		MapEntity e = cacheMap.get(key);
		if (e != null)
		{
			long now = System.currentTimeMillis();
			if (cacheTime < 0 || (now - e.t < cacheTime))
			{
				return e.v;
			} else {
				cacheMap.remove(key);
			}
		}
		return null;
	}

	public void addToCache(K k, T t)
	{
		if (t != null && k != null)
		{
			cacheMap.put(k, new MapEntity(t));
		}
	}

	public void remove(K k)
	{
		if (k != null)
			cacheMap.remove(k);
	}

	private class MapEntity {
		MapEntity(T v)
		{
			t = System.currentTimeMillis();
			this.v = v;
		}

		T v;
		long t;
	}
}