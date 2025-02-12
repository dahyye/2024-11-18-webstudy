<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	139page
	지시자 -> 내장 개체 -> 액션태그 순으로 수업할 예정
	Cooket / Session / 데이터베이스
	JSTL / EL -> MVC (Spring)
	----------------------------- 자바관련 (데이터 관리)
	
	
	지시자 
	 page / taglib(598page) / include(거의 사용안함 -> jsp:include로 대체)
	 
	 => jsp에 대한 정보
		1. import -> 라이브러리 가져올 때 사용
			<%@ page import="java.io.*, java.uilt.*"%>
				=> 2~3개일때
			<%@ page import="java.io.*"%>
			<%@ page import="java.util.*"%>
			 	=> 수가 많으면 따로 쓰는게 편함
		2. contentType -> 브라우저에 보낼 데이터 형식 지정
			HTML => contentType="text/html; charset=UTF-8" 
			XML =>  contentType="text/xml; charset=UTF-8"
			JSON => contentType="text/plain; charset=UTF-8"
			
			AJax 
			   -- xml => 유지보수에서 사용
			   
		3. errorPage -> 에러발생시 화면 이동(직접 지정해야함)
			404/403/405/412/500 -> 화면 이동 
		4. buffer -> 출력에 필요한 HTML을 저장할 메모리 공간 크기
		   ------ 8kb
		   		늘리고 싶을 땐 buffer="16kb"
		
		5. isErrorPage="true" => exception사용시에 주로 사용 (많이 사용하진 않아)
		   
		*** page에서 제공하는 모든 속성은 한 번만 사용이 가능
			예> import는 예외
		
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%= application.getRealPath("/") %>
<!--  C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPBasicProject_2\ -->
</body>
</html>