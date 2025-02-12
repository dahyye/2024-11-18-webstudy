<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, java.util.*"%>
<%@ page import="java.text.*" %>
<%--
	import : 외부 라이브러리 첨부
	
	import만 유일하게 여러개 사용할 수 있다
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%
 	/*
 		java.lang.*
 		jakarta.servlet.* ==> tomcat 10버전부터 // 이전에는 javax
 		javarta.servlet.http.* 생략가능
 	*/
 	Date date=new Date();
 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 	String today=sdf.format(date); 	
 	
 %>
</body>
</html>