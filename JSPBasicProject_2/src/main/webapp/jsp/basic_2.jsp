<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--

	JSP의 기초
		public void _jspService(HttpServletRequest request, HttpServletResponse response)
		{
			--------------------------------
			멤버변수 선언 / 메소드 제작
			<%! %> => 선언문 => 극히 드물다
			--------------------------------
			public void _jspInit(){}
			public void _jspDestory(){}
			public void _jspServece(request, response)
			{
				HttpSession session
				PageContext pageContext
				JspWriter out
				Exception exception
				Object page = this
				ServletContext application
				ServletConfig config
				
				try
				{
					//jsp코딩부분
					<%
					
					%>
				}catch(Exception ex){}
				
			}
		}
		
		= <%@ %> : 선언문
		  <%@ page %>
		  	=> 파일에 대한 정보
		  	=> 변환 : contentType = "|"
		  						파일 형식 지정
		  						1. text/html = > html 전송
		  						2. text/xml = > xml 전송
		  						3. text/plain = > json 전송
		  						
		  	=> pageEncoding="UTF-8"; => 한글 처리
		  	=> import : 외부 라이브러리 / 사용자 정의 라이브러리
		  	=> buffer : HTML을 출력할 메모리 공간의 크기 
		  		=> 8kb - > 16kb
		  	=> errorPage -> error가 발생시 자동으로 화면 이동
		  		200
		  		404
		  		403
		  		405
		  		412
		  		400
		  		500
		  		
		 <%@ include %> : JSP 특정부분에 다른 JSP를 첨부
		 				: 정적 
		
		
		<!-- --> 는 남아있다(html)
		< %-- --% > 는 사라진다 
		<% 
			<%
			     -> 오류 %%안에 또 %%가 들어가면 오류가 난다
			%>
		
		%>
		

 --%>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	  // 선언문 : 멤버변수 , 메소드 => class영역
	  public int add(int a, int b)
	  {
		  return a+b;
	  }
	%>
	
	<%

		//_jspService영역(메소드영역)
		int a=10;
		int b=20;
		int c=add(a,b);
	
	%>
</body>
</html>