package test.ehcache;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import base.SpringTestBaseCase;
import cn.cd.caoyeung.webapp.service.UserServiceI;

public class Ehcache extends SpringTestBaseCase {
	@Test
	public void ehcacheTest(){
//		EhCacheCacheManager cacheManager=(EhCacheCacheManager)context.getBean("ehCacheManager");
//		cacheManager.getCacheManager();
//		Assert.assertNotNull(cacheManager);
//		System.out.println("cacheManager:"+cacheManager);
//		Cache cache = cacheManager.getCache("xmlCache");
//		cache.put("greeting", "Hello, World!");
//		Object object = cache.get("greeting").get();
//		System.out.println(object);
//		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		UserServiceI userService =(UserServiceI) context.getBean("userService");
		List<Map> users = userService.getUserById("");
		LogFactory.getLog(Ehcache.class).debug("users:"+users);
		
	}
}
