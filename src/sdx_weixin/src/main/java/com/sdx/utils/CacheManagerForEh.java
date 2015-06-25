package com.sdx.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.blocking.BlockingCache;

/**
 * 缓存获取接口
 * @author 田广文
 * @date 2012-1-12 下午12:15:37
 */
public final class CacheManagerForEh
{
	private CacheManager cacheManager = null;
	private BlockingCache pageCache = null;

	private static CacheManagerForEh INSTANCE = null;

	private CacheManagerForEh()
	{
		super();
		this.cacheManager = CacheManager.create("/ehcache.xml");
		this.pageCache = new BlockingCache(getCacheManager().getCache("SimplePageCachingFilter"));
	}

	public static CacheManagerForEh getInstance()
	{
		return INSTANCE;
	}

	public static CacheManager getCacheManager()
	{
		return getInstance().cacheManager;
	}

	public static Cache getShortTimeCache()
	{
		return getCacheManager().getCache("SimpleShortTimeCache");
	}

	public static Cache getLongTimeCache()
	{
		return getCacheManager().getCache("SimpleLongTimeCache");
	}

	public static BlockingCache getPageCache()
	{
		return getInstance().pageCache;
	}
}