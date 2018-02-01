package cn.cd.caoyeung.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cd.caoyeung.webapp.bean.LogMessage;

@Controller
@RequestMapping("/view")
public class MainController {
	@LogMessage(description="登录成功")
	@RequestMapping("/success.do")
    public String login(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("username", "mainView");
        return "/view/success";
    }
}
