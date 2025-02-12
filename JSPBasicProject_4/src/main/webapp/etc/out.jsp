<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>JspWroter : 버퍼관리</h1>
 1. 버퍼크기 : <%= out.getBufferSize() %><%-- 8*1024 --%>
 2. 남아 있는 버퍼 : <%= out.getRemaining() %>
 3. 사용중인 버퍼 : <%= out.getBufferSize()-out.getRemaining() %>
 <table border="1" bordercolor=bloack width=300> 
 	<tr>
 	<td>Hello JSP!!</td>
 	</tr>
 	<tr>
 	<td>
 	 <% 
 	 	for(int i=1;i<=2;i++)
 		{
 	 		out.println("&nbsp;&nbsp;");
 		}
 	%>
 	Hello JSP2...
 	</td>
 	</tr>
 </table>
</body>
</html>