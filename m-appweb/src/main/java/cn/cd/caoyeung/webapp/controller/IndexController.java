package cn.cd.caoyeung.webapp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cd.caoyeung.webapp.bean.LogMessage;
import cn.cd.caoyeung.webapp.context.WebContextUtils;
import cn.cd.caoyeung.webapp.model.User;
import cn.cd.caoyeung.webapp.utils.file.ImageUtils;

import com.alibaba.fastjson.JSON;
import com.ctc.wstx.util.StringUtil;
@Controller
public class IndexController {
	private static Logger lg = Logger.getLogger(IndexController.class);
	@LogMessage(description = "校验验证码")
	@RequestMapping("/checkCode.do")
	public String checkCode(HttpServletRequest request, HttpServletResponse response) {
		String code = WebContextUtils.getSessionAttrValue("code",String.class);
		String webParam = WebContextUtils.getServletContextInitParamValue(request,"contextParam");
		String inputCode = request.getParameter("contextParam-checkCode");
		lg.info("是否进行验证码校验:" + webParam);
		boolean checkResult = true;
		if(StringUtils.equals(inputCode, "true")){
			if(!StringUtils.equals(code, inputCode)){
				checkResult = false;
			}
		}
		List<String> allAPIURL = WebContextUtils.getAllAPIURL(request, response);
		lg.info("allAPIURL:" + allAPIURL);
		return "redirect:/";
	}
	@LogMessage(description = "获取验证码")
	@RequestMapping("/getCode.do")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Paragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		String code = ImageUtils.getNumber(4);
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		BufferedImage image = ImageUtils.drawCode(code);
		ImageIO.write(image, "jpeg", os);
		os.close();

	}
	@LogMessage(description = "获取用户信息")
	@RequestMapping(value = "/getUserInfo.do", method = RequestMethod.GET)
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setName("LiWEi");
		user.setGender("M");
		user.setAge(28);
		user.setAddress("CHENGDU");
		String jsonStr = JSON.toJSONString(user);
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
	}

}
