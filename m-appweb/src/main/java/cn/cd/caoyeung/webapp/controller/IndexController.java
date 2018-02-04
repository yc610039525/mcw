package cn.cd.caoyeung.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cd.caoyeung.webapp.bean.LogMessage;
import cn.cd.caoyeung.webapp.common.file.ImageUtils;
import cn.cd.caoyeung.webapp.context.SpringContextUtils;
import cn.cd.caoyeung.webapp.context.WebContextUtils;
import cn.cd.caoyeung.webapp.controller.base.BaseController;
import cn.cd.caoyeung.webapp.model.pojo.User;
import cn.cd.caoyeung.webapp.service.UserServiceI;

import com.alibaba.fastjson.JSON;

@Api(description="2018年2月3日20:17:30",tags={"获取用户信息以及校验码"})
@Controller
public class IndexController extends BaseController{
	private static Logger lg = Logger.getLogger(IndexController.class);
	
	@ApiOperation(value="校验验证码",notes = "note:根据验证码图片校验信息")
	@LogMessage(description = "")
	@RequestMapping(value="/checkCode.do"
	,method={RequestMethod.GET,RequestMethod.POST}
	,produces = "application/json;charset=utf-8")
	public @ResponseBody String checkCode(HttpServletRequest request, HttpServletResponse response) {
		String code = WebContextUtils.getSessionAttrValue("code",String.class);
		String checkCodeFlag = WebContextUtils.getServletContextInitParamValue(request,"contextParam-checkCode");
		String inputCode = request.getParameter("code");
		boolean checkResult = true;
		String msg = "检验成功";
		if(StringUtils.equals(checkCodeFlag, "true")){
			if(!StringUtils.equals(code, inputCode)){
				checkResult = false;
				msg = "校验失败";
			}
		}
		UserServiceI UserServiceI = (UserServiceI)SpringContextUtils.getBean("userService");
		List<Map> userById = UserServiceI.getUserById("");
		lg.info(""+userById);
		return buildResultVOInfo(checkResult,msg,checkCodeFlag);
	}
	@ApiOperation(value="获取验证码",notes = "note:获取验证码")
	@RequestMapping(value = "/getCode.do",method={RequestMethod.GET,RequestMethod.POST})
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
	@ApiOperation(value="获取用户信息",notes = "获取用户信息")
	@RequestMapping(value = "/getUserInfo.do", method = RequestMethod.GET)
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		user.setName("李明");
		user.setGender("M");
		user.setAge(28);
		user.setAddress("成都万年场");
		String jsonStr = JSON.toJSONString(user);
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
	}
	@ApiOperation(value="获取员工信息",notes = "获取员工信息")
	@RequestMapping(value = "/getEmployer", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody User getEmployer(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "name",defaultValue = "李云") String name, 
			@RequestParam(value="address",required = false) String address){
		User user = new User();
		user.setName(name);
		user.setGender("M");
		user.setAge(28);
		user.setAddress(address);
		return user;
	}
	
}
