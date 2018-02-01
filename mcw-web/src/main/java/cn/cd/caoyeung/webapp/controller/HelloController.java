package cn.cd.caoyeung.webapp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cd.caoyeung.webapp.bean.LogMessage;
import cn.cd.caoyeung.webapp.model.User;
import cn.cd.caoyeung.webapp.utils.file.ImageUtils;

@Controller
public class HelloController {
	private static Logger lg = Logger.getLogger(HelloController.class);
	@ResponseBody
	@RequestMapping("/get/{id}/product")
	public User sayHello1(@PathVariable("id") String id){
		lg.info("id:"+id);
		User u = new User();
		u.setName("liweiwei");
		u.setAge(23);
		u.setAddress("chengdu");
		return u;
	}
	@LogMessage(description = "RPC")
	@RequestMapping("/get/code")
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
}
