package cn.cd.caoyeung.webapp.utils.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

/**
 * BeanNameAware 获得到容器中Bean的名称 BeanFactoryAware 获得当前bean Factory,从而调用容器的服务
 * ApplicationContextAware 当前的application context从而调用容器的服务 MessageSourceAware
 * 得到message source从而得到文本信息 ApplicationEventPublisherAware 应用时间发布器,用于发布事件
 * ResourceLoaderAware 获取资源加载器,可以获得外部资源文件
 */

public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		SpringContextUtils.ctx = ctx;
	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}

	public static Resource getResource(String path) {
		return ctx.getResource(path);
	}
}
