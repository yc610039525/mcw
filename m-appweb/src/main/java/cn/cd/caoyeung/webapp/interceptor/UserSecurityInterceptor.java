package cn.cd.caoyeung.webapp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class UserSecurityInterceptor  implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(UserSecurityInterceptor.class);
	List<String> ignoreUrls;
	public void setIgnoreUrls(List<String> ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String username =(String)request.getParameter("name");
		String pwd =(String)request.getParameter("pwd");;
		Boolean isLogin =(Boolean)session.getAttribute("isLogin");
		boolean flag=false;
		if(username!=null&&!username.isEmpty()){
			if("admin".equals(username)||"000000".equals(pwd)){
				flag=true;
			}else if(ignoreUrls!=null&&!ignoreUrls.isEmpty()){
				for(String ignoreUrl : ignoreUrls){
					if(ignoreUrl.equals(username)){
						flag=true;
					}
				}
			}
		}
		if(isLogin!=null&&isLogin){
		}
		if(!flag){
			response.sendRedirect("/spring.framework/index.jsp");

		}
		session.setAttribute("isLogin", flag);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("HandlerInterceptor-postHandle():preHandle()返回true执行postHandle()...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("HandlerInterceptor-afterCompletion():拦截完成后...一般用于日志处理");
	}

}
