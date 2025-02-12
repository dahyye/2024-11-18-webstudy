<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	**request / **response / **out / pageContext
	*application / **session
	config / page / exception 
	 |		  |
	 |		 this
	web.xml 
	 
	application
	클래스명 : ServletContext
	용도 : 서버관리
			버전 : getMajorVersion()
				  getMinorVersion()
					=> 서블릿 버전
			서버명 : getServerInfo()
		  로그파일
		   	: log()
		   	: web/xml연동 => getInitParameter()
		  **실제경로명
		    : getRealPath()
		   
	 
--%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
MajorVersion:<%= application.getMajorVersion() %><br>
MinorVersion:<%= application.getMinorVersion() %><br>
Version:<%= application.getMajorVersion()+"."+application.getMinorVersion() %>
<br>
서버명 : <%= application.getServerInfo() %>
<%
	String driver=application.getInitParameter("driver");
	String url=application.getInitParameter("url");
	String username=application.getInitParameter("username");
	String password=application.getInitParameter("password");
	
	application.log("Driver:"+driver);
	application.log("URL:"+url);
	application.log("Username:"+username);
	application.log("Password:"+password);
	
%>
</body>
</html>