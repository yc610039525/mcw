package test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheMainTest {
	public static void main(String[] args) {
//        final CacheManager cacheManager = new CacheManager();
//        final Cache cache = cacheManager.getCache("helloworld");
//        final String key = "greeting";
//        final Element putGreeting = new Element(key, "Hello, World!");
//        cache.put(putGreeting);
//        final Element getGreeting = cache.get(key);
//        System.out.println(getGreeting.getObjectValue());
		long t1 = 24*60*60*1000*1000;
		System.out.println(t1);
		long t2 = 24*60*60*1000*1000L;
		System.out.println(t2);
		long t3 = 24L*60*60*1000*1000;
		System.out.println(t3);
	}

}
