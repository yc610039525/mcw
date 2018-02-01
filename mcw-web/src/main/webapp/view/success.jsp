<%@ page language="java" import="java.util.*" 
pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+
"://"+request.getServerName()+
":"+request.getServerPort()+ path+"/";
%>
<html>
  <head>
    <title>登录成功</title>
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
	<script>
		 var t=3;
		 window.setInterval("refer()",1000); 
		 function refer(){
			 if(t==0){
			 location="/mcw/html/index.html"; 
			 }
			 document.getElementById('show').innerHTML="登陆成功，"+t+"秒后即将跳转到新页面"; 
			t--; 
		 }
	</script>
	<span id="show"></span>
  </body>
</html>