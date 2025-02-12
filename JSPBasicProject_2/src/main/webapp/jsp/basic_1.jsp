<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 주석처리 방법 --%>
<%--

	*JSP 실행과정(69page)
		
						  jsp파일 요청
		웹브라우저(클라이언트) 	======>		웹서버
										  |
										웹애플리케이션서버
										톰캣(WAS)-> jsp/servlet의 엔진역할
										------------------------------
												처리과정
													1. jsp를 찾는다
													2. jsp를 클래스로 변경
													3. 변경된 클래스를 컴파일
													4. .class생성 (서블릿)
													5. 인터프리터 이용 출력
													6. HTML만 메모리에 출력
													  		  -----
														      buffer
													7. 메모리에 있는 HTML을 브라우저에 읽기 
				
				*클래스 변경 => 한 번만 수행
					|
				   이미 생성된 경우 -> 소스만 수정후에 컴파일
				   생성이 안된 경우 -> 자바 생성 후 컴파일
													
													
		=> a.jsp  => a_jsp.java => a_jsp.class(서블릿 파일)
									----------
									| 실행 => out.write("<html>")
		
		b.jsp => b_jsp.java
		main.jsp  => main_jsp.java
			
		=> 자바로 변경
		public final class a_jsp extends HttpJspBase
			   ----- 상속이 안된다 (확장이 안된다)
		{
			public void _jspInit()
			{
				//생성자 역할
				//멤버변수의 초기화
				//연결된 파일 => web.xml
			}
		
		}
		//사용자의 요청에 대한 처리 => HTML => 브라우저로 전송
		public void _jspService(HttpServletRequest request, HttpServletResponse response)
		{
			
			PageContext pageContext;
			HttpSession session=null;
			ServletContext application;
			ServletConfig config;
			JspWriter out = null;
			Object page = this;
			try
			{
				//jsp 코딩영역 => _jspService 메소드를 완송
				  ----------메소드 영역
				=> JSP 파일을 읽어서 추가 ( 사용자 요청 처리하는 위치 )
				
				=> JSP에서 변수 선언
				
				=> 
			} 
			catch(Exception ex){}
		}
		
		public void _jspDestory()
		{
			//화면 변경 / 새로고침 => 자동 호출 (메모리에서 삭제)
		}
		
		서블릿
		
						서블릿 파일요청
		client(브라우저) =============== 
										톰캣
										-- 서블릿 찾기
										     |
										   서블릿 객체 생성
										   	 |
										   init() : 초기화
										   			web.xml
										   			
										   doGet() / doPost()
										   ------------------- 메모리처리
										      |
										   요청 화면을 브라우저로 전송
										   
		=> 서블릿 : 개발자가 직접 처리
					컴파일 / 인터프리터 => 브라우저 출력
		   JSP : 톰캣에 의해 처리 -> 브라우저 출력
		   			=> 바로 확인 가능
			-> 서블릿
				웹서비스 기능을 해주는 자바 클래스
				=> 자바안에서 HTML코드를 추가
					out.write("<html>")
				=> 단점
					HTML을 사용하기가 어렵다 -> CSS/JavaScript
					에러 처리가 어렵다
					자바를 약간 수정 => 컴파일을 다시 한다
					-> 리눅스 : javac / java
				=> 서블릿 
					1. html 출력하지 않는 웹개발
						=> java / html 분리
							|		|
							--------- 연결
								|
							  Controller (서블릿으로 제작)
							  ---------- 1차 프로젝트는 직접 제작	
							  			 2차 스프링 -> 이미 제작이 되어 있다 -> 포털 사이트
					2. 보안이 필요한 부분 : Spring Security
										--------------
										권한부여 / 자동로그인 / 접근거부
										
				=> 서블릿 단점을 보완한 파일(JSP)
				JSP
				 1. 서블릿보다는 쉽게 html을 작성할 수 있다
				 2. html/java가 나뉘어져 있다(구분)
				 3. html/java를 따로 구현이 가능(여러명이 동시에 개발)
				 4. 단순하다->데이터베이스 연동
				 5. html 따로 나와있어서 첨부하기 편리하다
				 6. jsp는 파일이 아니고 메소드
		
			
			
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>