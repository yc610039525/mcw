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
    <title>接口测试</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var ctx = "${ctx}";
	</script>
  </head>
  
  <body>
    <hr>
    <hr>
    <!-- <p>协议：<%=request.getScheme()%></p>
    <p>服务名：<%=request.getServerName()%></p>
    <p>端口：<%=request.getServerPort()%></p>
    <p>上下文根路径：<%=request.getContextPath()%></p>
    <p>登陆IP：<%=request.getRemoteAddr()%></p>
    <p>资源路径：<%=request.getServletPath()%></p>
	<hr> -->
	<p>功能点</p>
	<li><a href="/m-appweb/getUserInfo.do">获取用户信息</a></li>
	<li><a href="/m-appweb/view/FileImport.html">上传文件</a></li>
	<hr>
	<form action="/m-appweb/checkCode.do" method="post">
	<input name="code" ><br/><!-- value="<%=session.getAttribute("code")==null?"":session.getAttribute("code") %>" -->
	<img style="cursor:pointer;" src="/m-appweb/getCode.do" 
	border="1" onclick="this.src='/m-appweb/getCode.do?' + Math.random();"/><br/>
	<c:if test="${sessionScope.isLogin eq false}">
		<span>${sessionScope.msg}</span>
	</c:if>
	<hr/>
	<button type="submit">ACCEPT</button> <button type="reset">RESET</button><br/>
	</form>
	
  </body>
</html>
