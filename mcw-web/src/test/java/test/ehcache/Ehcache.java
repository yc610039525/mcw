package test.ehcache;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;

import base.SpringTestBaseCase;

public class Ehcache extends SpringTestBaseCase {
	@Test
	public void ehcacheTest(){
		EhCacheCacheManager cacheManager=(EhCacheCacheManager)context.getBean("ehCacheManager");
//		cacheManager.getCacheManager();
		Assert.assertNotNull(cacheManager);
		System.out.println("cacheManager:"+cacheManager);
		Cache cache = cacheManager.getCache("xmlCache");
		cache.put("greeting", "Hello, World!");
		Object object = cache.get("greeting").get();
		System.out.println(object);
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		
	}
}
