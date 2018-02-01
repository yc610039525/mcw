<%@ page language="java" import="java.util.*" 
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+
"://"+request.getServerName()+
":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <hr>
    <hr>
    <p>Spring框架搭建</p>
    <hr>
    <hr>
    <p>协议request.getScheme()：<%=request.getScheme()%></p>
    <p>服务名request.getServerName()：<%=request.getServerName()%></p>
    <p>端口request.getServerPort()：<%=request.getServerPort()%></p>
    <p>上下文根路径request.getContextPath()：<%=request.getContextPath()%></p>
    <p>登陆IPrequest.getRemoteAddr()：<%=request.getRemoteAddr()%></p>
    <p>资源路径request.getServletPath()：<%=request.getServletPath()%></p>
	<hr>
	<hr>
	<p>http://localhost/spring.framework/cxf/helloWorld?wsdl</p>
	
	<a href="getUserInfo.do">Controller use response PrintWriter write String to Brower </a>
	<hr>
	<hr>
	<p> &lt;img style="cursor:pointer;" src="checkcode.do" border="1" onclick="this.src='checkcode.do?' + Math.random();"/&gt;</p>
	<form action="login.do" method="post">
	<label>name:</label>
	<input name="name" />   
	<label>password:</label>
	<input name="pwd" type="password" />
	<label>code:</label>
	<%--response.setIntHeader("Refresh", 5);<%--JSP验证码图片还未生成，获取不到session数据 --%>
	<input name="code" value="<%=session.getAttribute("code")==null?"":session.getAttribute("code") %>">
	<img style="cursor:pointer;" src="checkcode.do" border="1" onclick="this.src='checkcode.do?' + Math.random();"/>&nbsp;&nbsp;
	<c:if test="${sessionScope.isLogin eq false}"><span>${sessionScope.errorMsg}</span></c:if>
	<button type="submit">LOGIN</button>
	</form>
	
  </body>
</html>
