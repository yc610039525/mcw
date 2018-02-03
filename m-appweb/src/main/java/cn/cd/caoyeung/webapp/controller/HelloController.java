package cn.cd.caoyeung.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.net.util.Charsets;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.Response;

import cn.cd.caoyeung.webapp.bean.LogMessage;
import cn.cd.caoyeung.webapp.common.file.ImageUtils;
import cn.cd.caoyeung.webapp.model.User;
@Api(description="RESTfull API接口测试",tags={"2018年2月3日21:14:48"})
@Controller
public class HelloController {
	private static Logger lg = Logger.getLogger(HelloController.class);
	@ApiOperation(value="根据Id获取产品",notes = "根据Id获取产品")
	@RequestMapping(value = "/get/{id}/product",
	method={RequestMethod.POST,RequestMethod.GET}
//	,produces ={"application/json;charset=utf-8"
//			,MediaType.TEXT_HTML_VALUE+";charset=utf-8" //尽量确定一种返回形式
//			}
)
	public @ResponseBody User sayHello(@PathVariable("id") String id){
		User u = new User();
		u.setName("LIWEIWEI");
		u.setAge(23);
		u.setAddress("水资源");
		return u;
	}
	@ApiOperation(value="生成图片返回给前台",notes = "生成图片返回给前台")
	@RequestMapping(value = "/get/code",
	method={RequestMethod.POST,RequestMethod.GET}//当请求类型中包含此类请求才返回
	)
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
