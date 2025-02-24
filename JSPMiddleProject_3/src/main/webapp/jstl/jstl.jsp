<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 


--%> 
<%@ taglib prefix="c" uri="jakarta.tags.core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h3>일반 자바</h3>
 <%
 	for(int i=1; i<=10; i++)
 	{
 		if(i%2==0)
 		{
 		%>
 			<%=i %>&nbsp;
 		<%
 		}
 	}
 %>
 <h3>JSTL을 이용한 출력</h3>
 <c:forEach var="i" begin="1" end="10" step="1">
 	<c:if test="${i%2==0 }">${i} </c:if>
 </c:forEach>
 
 <h3>다중 조건문</h3>
 <%
 	int star=3;
 %>
 <%
 	if(star==0)
 	{
 %>
	<span style="color:orange">☆☆☆☆☆</span>
 <%		
 	}
 	else if(star==1)
 	{
   %>
   <span style="color:orange">★☆☆☆☆</span>
  <% 	
 	}
 	else if(star==2)
 	{
 %>
	 <span style="color:orange">★★☆☆☆</span>
 <%
 	}
 	else if(star==3)
 	{
 	%>
 	<span style="color:orange">★★★☆☆</span>
 	<%
 			
 	}
 %>
 
 <h3>JSTL을 이용한 다중 조건문</h3>
 <c:set var="star" value="3"/>
 <c:choose>
 	<c:when test="${star==0}"><span style="color:orange">☆☆☆☆☆</span></c:when>
 	<c:when test="${star==1}"><span style="color:orange">★☆☆☆☆</span></c:when>
 	<c:when test="${star==2}"><span style="color:orange">★★☆☆☆</span></c:when>
 	<c:when test="${star==3}"><span style="color:orange">★★★☆☆</span></c:when>
 	<c:when test="${star==4}"><span style="color:orange">★★★★☆</span></c:when>
 	<c:when test="${star==5}"><span style="color:orange">★★★★★</span></c:when>
 </c:choose>
  <h3>자바 선택 조건문</h3>
  <% 
  	int sex=1;
  %>
  <%
  	if(sex==1)
  	{
  %>
  		남자
  <%		
  	}
  	else
  	{
  %>
  		여자
  <%
  	}
  %>
  
  <h3>JSTL 선택문</h3>
  <c:set var="sex" value="1"/>
  <c:choose>
  	<c:when test="${sex==1}">남자</c:when>
  	<c:otherwise>여자</c:otherwise>
  </c:choose>
</body>
</html>









 