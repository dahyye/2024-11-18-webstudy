<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	=> MVC / Spring / Spring-Boot / 실무
	EL : 표현식(브라우저에 데이터 출력) <%= %>
	JSTL : 태그형 라이브러리
	
	
	=> 출력시 
		연산처리 -> 조건, 출력(EL)
		출력형식
	
	=> 제어문 (JSTL)
	
	
	
	** ${"10"+10} => 자동으로 integer.parseInt("10") => 정수 변경
					=> 결과값 20
					
	** ${null+0} => null값을 "0"으로 자동 인식
	
	EL에서 처리하는 문자 / 날짜도 사용한다
	
	비교연산쟈=> 결과값을 true / false
			문자 / 날짜도 비교가 가능
		
		==(eq), !=(ne), <(gt), >(lt), <=(le), >=(ge)
		ex. 10==10  10 eq 10
			10!=5   10 ne 5
			${sessionScope.id=null} => 로그인이 안된 상태
	
	논리연산자
		&& (and), ||(or)
	
		<c:if test="EL"> => if()
		<c:when test="EL"> => switch
	
	삼항연산자
		=> &{데이터출력}
			 ------- 일반 변수는 사용할 수 어 ㅄ다
			   |
			   request에 담긴 값
			   request.setAttribute("id", "hong")
			   ${requestScope.id}
			   				  --- 키
			   => <%= request.getAttribute("id')%>
			   => requestScope는 생략이 가능하다
			   => ${id}
			   
			   |
			   session에 담긴 값
			   session.setAttribute("id", "admin");
			   => <%= session.getAttribite("id") %>
			   => ${sessionScope.id}
			   					 -- 키
			   => sessionScope는 생략이 가능하다
			   => request / session
			   
			   
			   *** ${id} => request
			   *** ${sessionScope.id} => session
			   
	제어문(JSTL) => JSP => MVC사용시에는 자바를 사용하지 않는다
	---------- 자바가 없는 것 처럼 보인다 => 자바 제어문
			   -------------------- 태그로 만들어진 라이브러리
			   
			   
	= core : 제어문, 변수선언 , 화면이동
			=> import : uri="jakarta.tags.core"
			=> 제어문
			   조건문  => else가 없다! if문만 사용한다 => 단일 조건문만 사용이 가능
			   		<형식>
			   		 <c:if test="${조건문(논리/비교)}">
			   		 	조건이 true면 수행하는 HTML을 사용
			   		 </c:if>
			   		 
			   		 => else를 표현하고 싶을 땐
			   		 <c:if test="${!조건문(논리/비교)}">
			   		 	조건이 true면 수행하는 HTML을 사용
			   		 </c:if>
			   		 
			   선택문	=> 다중 조건문 사용
		   			<c:choose>
		   			 <c:when test=""></c:when>
		   			 <c:when test=""></c:when>
		   			 <c:when test=""></c:when>
		   			 ...
		   			 <c:otherwise></c:otherwise> => else
		   			</c:choose>
		   			
			   		
			   반복문
			    			for(int i=1; i<=10; i++)
			   		<c:forEach var="i", begin="1" end="10" step="1">
			   											=> step이 1인 경우는 생략이 가능하다
			   											
			   			단점 : 감소식은 사용할 수 없다
			   			
					</c:forEach>
					
	= fmt : 날짜변환, 숫자변환
			(SimpleDateFormat , DecimalFormat)
			=> 많이 사용하지 않는다 : 오라클에서 변경이 가능
			uri="jakarta.tags.function"
			
 	= fn : String클래스의 메소드 호출
			length(), substring()...
			
	-----------
	=xml
	=sql : DAO
	----------- MVC/MV구조에서는 사용하지 않는다(순수JSP로 제작)

--%>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- 
	
	core: 제어문을 지원 / 변수 선언 / 화면 이동
	prefix="c" => <c:foreach>
	prefix="core" => <core:foreach>
	------------- 개발자가 선택
	
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>일반 자바 for문</h3>
	<%
		for(int i=1; i<=10; i++)
		{
	%>	
			<%= i %>&nbsp;
	
	<%
		}
	%>
	
	
	<h3>&lt;forEach&gt; 사용</h3>
	<c:forEach var=i begin=1 end=10 step=1>
		${i }&nbsp;
	</c:forEach>
</body>
</html>


