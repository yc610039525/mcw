package cn.cd.caoyeung.webapp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.cd.caoyeung.webapp.bean.LogMessage;
import cn.cd.caoyeung.webapp.model.User;
import cn.cd.caoyeung.webapp.utils.file.ImageUtils;

import com.alibaba.fastjson.JSON;

@Controller
public class LoginController {
	private static Logger lg = Logger.getLogger(LoginController.class);
	@LogMessage(description = "login")
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("pwd", pwd);
		String code = (String) session.getAttribute("code");
		ServletContext applcontext = request.getSession().getServletContext();
		String webParam = (String) applcontext.getInitParameter("webParam");
		lg.info("code:" + code);
		lg.info("webParam:" + webParam);
		String method = request.getMethod();
		lg.info("Request Method:" + method);
//		Cookie cookie = new Cookie("name", "xiaoming");
//		cookie.setPath("/");
//		Cookie[] cookies = request.getCookies();
//		response.addCookie(cookie);
//		return "redirect:/view/success.jsp";
		return "redirect:/view/FileImport.html";
	}

	@LogMessage(description = "获取验证码")
	@RequestMapping("/checkcode.do")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Paragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		String number = ImageUtils.getNumber(4);
		HttpSession session = request.getSession();
		session.setAttribute("code", number);
		BufferedImage image = ImageUtils.drawCode(number);
		ImageIO.write(image, "jpeg", os);
		os.close();

	}

	@LogMessage(description = "获取用户信息")
	@RequestMapping(value = "/getUserInfo.do", method = RequestMethod.GET)
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setName("LiWEi");
		user.setGender("F");
		user.setAge(12);
		user.setAddress("CHENGDU");
		String jsonStr = JSON.toJSONString(user);
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
	}

	public Object getAttrFromSession(String attrName) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		Object obj = requestAttributes.getAttribute(attrName, RequestAttributes.SCOPE_SESSION);
		return obj;
	}

	public HttpServletRequest getHttpServletRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		return request;
	}
}
