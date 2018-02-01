package cn.cd.caoyeung.webapp.context;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener,HttpSessionAttributeListener,ServletContextListener{
	private AtomicInteger count = new AtomicInteger(0);
	ServletContext ctx = null;
	@Override
	public void sessionCreated(HttpSessionEvent eve) {
			
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent eve) {
		HttpSession session = eve.getSession();
	}

	@Override
	public void contextDestroyed(ServletContextEvent eve) {
		ctx = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent eve) {
		ctx = eve.getServletContext();
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent eve) {
		String attrName = eve.getName();  
		Object attrValue = eve.getValue();  
		HttpSession session = eve.getSession();
		String sessionId = session.getId();
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent eve) {
		String attrName = eve.getName();  
		Object attrValue = eve.getValue();  
		HttpSession session = eve.getSession();
		String sessionId = session.getId();
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent eve) {
		String attrName = eve.getName();  
		Object attrValue = eve.getValue();  
		HttpSession session = eve.getSession();
		String sessionId = session.getId();
	}

}
