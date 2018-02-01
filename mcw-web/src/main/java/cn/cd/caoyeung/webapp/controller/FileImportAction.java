package cn.cd.caoyeung.webapp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@RequestMapping("/view")
@Controller
public class FileImportAction {
	@RequestMapping(value="/import/index.do")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws IOException  {
		String fileParam = request.getParameter("fileParam"); 
		System.out.println("fileParam:"+fileParam);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileGroup1 = multipartRequest.getFiles("fileGroup1");
		List<MultipartFile> fileGroup2 = multipartRequest.getFiles("fileGroup2");
		System.out.println("fileGroup1:"+fileGroup1.size());
		System.out.println("fileGroup2:"+fileGroup2.size());
		for(MultipartFile file : fileGroup1){
			CommonsMultipartFile commonFile = (CommonsMultipartFile)file;
			String name = commonFile.getFileItem().getName();
			System.out.println("fileName:"+commonFile.getFileItem().getName());
			FileOutputStream fos = new FileOutputStream(new File("D:\\"+name));
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			BufferedInputStream bis = new BufferedInputStream(commonFile.getInputStream());
			byte[] bytes = new byte[1024];
			while(bis.read(bytes)!=-1){
				bos.write(bytes);
			}
			bos.close();
			bis.close();
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("上传"+fileGroup1.size()+"个文件成功");
		return null;
	}
}
