package cn.cd.caoyeung.webapp.context;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AuthorityFilter implements Filter {
	
	protected List<String> ignoreUrlList = new ArrayList<String>();
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();
		String path = httpRequest.getRequestURI();
//		httpResponse.setContentType("text/html;charset=UTF-8");
//		httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Enumeration enums = filterConfig.getInitParameterNames();
		if (enums == null)
			return;
		String argName = null;
		while (enums.hasMoreElements()) {
			argName = enums.nextElement().toString();
			if ("ignore-url".equals(argName)) {
				ignoreUrlList = this.getIgnoreUrlList(filterConfig.getInitParameter(argName));
			}
		}
	}
	private List<String> getIgnoreUrlList(String xmlPath) {
		List<String> list = new ArrayList<String>();
//		Document doc = null;
//		SAXReader reader = new SAXReader();
//	    try {
//	    	File f = new File(this.getClass().getResource(xmlPath).toURI());
//			doc = reader.read(f);
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		if(doc != null) {
//			List<Element> eles = doc.selectNodes("//url");
//			for(Element ele:eles){
//				if(!"".equals(ele.getTextTrim()))
//					list.add(ele.getTextTrim());
//			}
//		}
		return list;
	}
}
