<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.EmpDAO"/>
<!-- EmpDAO dao=new EmpDAO(); -->
<%
	List<EmpBean> list = dao.empListData();
%>
<%--
		8장 => basic
		-------------
		1. 빈즈 / 액션
			JSP => Bean -> VO(Spring), DTO(Mybatis)
							|
							Spring : Java => 화면출력(JSP)
							  				  --------- VIEW
							 -------------
							 		Model
							 =================== + 
							 		스프링 라이브러리 : Servlet
							 						  |
							 						Controller			
			: 데이터를 모아서 브라우저 한 번에 전송
			
			1) 자바진즈 만들기
				데이터를 저장하는 변수 설정(캡슐화) => private 설정
					=> 설정된 변수마다 기능 부여
							---
							메모리에 저장 : setter
							메모리에서 읽기 : getter
					=> 변수는 이미 결정이 된 상태
					   -------------------- 데이터베이스 => 컬럼명
					   						크롤링 필요한 데이터
					   						OpenAPI
				설정하는 방식
				  getter / setter : 자동완성기 / lombok
				  => name
				  	setName() / getName()
				  => boolean : 존재여부 확인
				  	 boolean login
				  	 setter : isLogin()
				  	 getter : getLogin()
				
				데이터를 보호 / 데이터를 모아서 처리
				VO와 동일
				액션태그 사용
			      => <jsp:useBean> : 객체 메모리 할당
			      		MemberVO vo = new MemberVO()
			      		<jsp:useBean id="vo" class="MemberVO"/>
			      	 <jsp:setProperty> : 객체의 변수값 주입
			      	 	vo.setName="aaa"
			      	 	<jsp:setProperty name="vo" property="name" value"aaa">
			      	 	<jsp:setProperty name="vo" property="*"> 
			      	 		=> vo가 가지고 있는 모든 setter에 값을 채워라
			      	 <jsp:getProperty> : 객체의 변수값 출력
			      	 	<
			      	 <jsp:param> : 다른 페이지 이동시 필요한 데이터 전송
					    
		2. DBCP(Database Connection Pool) -> 293page
			1) 데이터베이스 연결에 소모되는 시간을 단축할 수 있다
						---------------- 가장 많은 시간이 투자
			2) 미리 Connection을 데이터베이스에 연결한 상태에서 저장
				--------------> Connection 생성 지정
			3) 웹프로그램은 일반적으로 DBCP를 사용한다
			4) Connection의 객체를 제한
			5) 사용후에 재사용한다
			6) Connection을 미리 생성했기 때문에 => 객체 관리가 쉽다
			7) 쉽게 서버가 다운되지 않는다
			
			사용하는 목적 : 웹 사용자에게 응답시간을 단축해준다(빠른 속도) -> 효율적인 데이터베이스
			
			DBCP 연결방법
				1. 시작과 동시에 Connection을 생성(연결된 상태)
					=> 몇 개를 생성할 지 결정
						maxIdle="10"
					=> 만약 초과가 되면 확장할 수 있다
						maxActive="10"  ?? 인터넷에는 maxActive가 동시에 사용할 수 있는 최대 커넥션의 갯수라고 나와있다
				2. 저장(저장공간:Pool)
				3. 사용자가 요청하면 Connection의 주소를 얻어온다
				4. 사용자 요청처리
				5. 사용후에는 반드시 Pool에 반환 => 재사용
				
			
			코딩
				1. server.xml 등록
				2. getConnection() => 미리 생성된 Connection객체 얻기
				3. disConnection() => pool로 반환
				4. 기능은 동일
				
		
			
		3. Cookie / Session
		4. JSTL / EL
		5. MVC => Spring
		
		=> 지도 / Cookie => 전체

--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
h3{
	width: 500px;
	text-align: center;
}
</style>
</head>
<body>
 <h3>사원목록</h3>
  <table class="table_content" width=500>
   <tr>
    <th>사번</th>
    <th>이름</th>
    <th>직위</th>
    <th>입사일</th>
    <th>급여</th>
   </tr>
   <%
   	 for(EmpBean vo:list)
   	 {
   %>
   
   <tr>
    <td><%= vo.getEmpno() %></td>
    <td><%= vo.getEname() %></td>
    <td><%= vo.getJob() %></td>
    <td><%= vo.getHiredate() %></td>
    <td><%= vo.getSal() %></td>
   </tr>
   
   <%
   	 }
   %>
 
  </table>
</body>
</html>