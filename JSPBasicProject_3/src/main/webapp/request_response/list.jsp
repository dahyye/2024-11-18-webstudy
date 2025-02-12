<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<%
	//사용자가 보내준 값 받기
	//내장객체중에 page가 있기 떄문에 변수이름을 page로 설정하면 충돌날 수 있음
	String strPage=request.getParameter("page");
	/*
		list.jsp      ==> null값
		list.jsp?page=  ==> 공백 ""
		list.jsp?page=1 ==> "1"
	
		list.jsp?page=1&no=10
		
		=> 크롤링 할 때 &가 들어가면 입력(스캐너)으로 알기 때문에 replayce로 &를 다른 값(ex. "^")으로 변경해서 가져와야해
		
		
	*/
	//default페이지를 잡을 때 null값처리 꼭 해주기
	if(strPage==null)
	{
		strPage="1";
	}
		
	int curpage=Integer.parseInt(strPage);
	//데이터베이스 연결
	DataBoardDAO dao = DataBoardDAO.newInstance();
	
	
	//데이터베이스에서 출력할 데이터 가져오기
	List<DataBoardVO> list = dao.dataBoardListData(curpage);
	int totalpage=dao.databoardTotalPage();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
.container{
	width: 900px;
	margin-top: 50px;
}
h3{
	text-align: center;
}
.table_content{
	width: 600px;
	margin: 0ox auto;
}
a{
	text-decoration: none;
	color: black;
}
a:hover{
	text-decoration: underline;
	color: green;
}
</style>
</head>
<body>
	<div class="container">
	 <h3>자료실</h3>
	  <table class="table_content">
	   <tr>
	    <td>
	     <a href="insert.jsp">새글</a>
	    </td>
	   </tr>
	  </table>
	  <table class="table_content">
	   <tr>
	    <th width=10%>번호</th>
	    <th width=45%>제목</th>
	    <th width=15%>이름</th>
	    <th width=20%>작성일</th>
	    <th width=10%>조회수</th>
	   </tr>
	   <%
	   	for(DataBoardVO vo : list)
	   	{
	   %>
	  	 <tr>
	    <td width=10% align="center"><%= vo.getNo() %></td>
	    <td width=45%>
	     <a href="detail.jsp?no=<%= vo.getNo()%>"><%= vo.getSubject() %></td>
	    <td width=15% align="center"><%= vo.getName() %></td>
	    <td width=20% align="center"><%= vo.getRegdate().toString() %></td>
	    <td width=10% align="center"><%= vo.getHit() %></td>
	   </tr>	 
	   <%
	   	}
	   %>
	   <tr>
	    <td colspan="5" align="center">
	     <a href="list.jsp?page=<%= curpage>1?curpage-1:curpage %>">이전</a>
	     &nbsp; <%=curpage %> page / <%=totalpage %> pages
	     &nbsp;
	     <a href="list.jsp?page=<%= curpage<totalpage?curpage+1:curpage %>">다음</a> 
	     <!-- if문을 사용하면 코드가 너무 길어져서 삼항연산자로 사용하는게 편리함
	     		보통 소스가 길거나(게임) 프로그램언어가 여러개(웹)를 사용하는 경우 삼항연산자를 많이 사용함
	      -->
	    </td>
	   </tr>
	  </table>
	</div>
</body>
</html>