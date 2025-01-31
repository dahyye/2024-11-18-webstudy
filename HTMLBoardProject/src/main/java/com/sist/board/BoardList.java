package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

//tomcat -> 9버전  => javax.servlet.*
//tomcat -> 10이상 => jakarta.servlet.*
/*

class ServletClass extends HttpServlet
{
	public void init()
	{
		초기화 담당 -> 변수에 대한 초기화
	}
	public void destory()
	{
		화면 변경 / 새로고침 => 메모리회수 => 새로고침할 때마다 창을 다시 띄움
		=> 장점 : 바로 메모리 해제
	}
	----------------------------------
	사용자 요청에 대한 처리
	public void doGet()
	{
		//사용자 요청 => GET
		// => 단순한 검색(page, rjator) -> 화면UI
		//DEFAULT
		// <a> location.href = " "
		//sendRedirect()
	}
	public void doPost()
	{
		//사용자 요청 => POST
		//처리용 => <form>, ajax, vuejs => 지정이 가능
	}
	// GET/POST를 동시에 처리 =>  MVC구조 (get/post) 같은 처리방식
	public void service()
	{
	
	}
	-----------------------------------
	=> _jspInit()-> _jspDestory(), _jspService()
}
==========================================================

							servlet/jsp엔진
브라우저(사용자요청) ==> 웹서버 ==> 웹컨테이터
	-> 주소창		  html/xml		톰캣
								 |
								요청한 JSP/Servlet을 찾아서 컴피일(.class)
								 |
								인터프리터를 한줄씩 번역
								 |
								메모리에 html저장
								 | 
								브라우저에서 읽어서 출력

*/

//서블릿을 찾는 URL주소 => 톰캣이 해당 서블릿을 찾아서 실행
@WebServlet("/BoardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변환해서 브라우저로 html을 전송 
		// 변환 (브라우저에서 실행)
		// 1. html 2. xml 3.json
		// 사용자 전송(사용자가 보낸 값), 브라우저 전송
		// request -> BufferedReader    response -> OutputStream
		response.setContentType("text/html;charset=UTF-8"); //이 파일은 html로 보내겠다
		/*
		  html=> text/html
		  xml => text/xml
		  json => text/plain -> Ajax(javascript)		 
		 */
		//어떤 브라우저에 보내는지 확인
		PrintWriter out=response.getWriter();
		//출력
		//1. 사용자로부터 요청한 페이지를 받는다
		//웹 -> String 
		//  /BoardList?page=2;
		//	/BoardList;  => page는 널값
		// /BoardList?page=   => page는 "" 공백
		String page=request.getParameter("page");
		if(page==null)
			page="1"; //처음에는 페이지설정이 안되어있으니까 1페이지로 임의설정해준다
		//현재페이지 지정
		int curpage=Integer.parseInt(page);
		//현재페이지에 대한 데이터를 오라클로부터 가져오기
		BoardDAO dao = BoardDAO.newInstance();
		List<BoardVO> list= dao.boardListData(curpage);
		//총페이지가져오기
		int totalpage=dao.boardTotalPage();
		
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		Date date=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String today=sdf.format(date);
//		-> 3줄을 한 번에 코딩 하기		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=css/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>게시판</h1>");
		out.println("<table class=table_content width=700>");
		out.println("<tr>");
		out.println("<td><a href=board/insert.html>새글</a></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<table class=table_content width=700>");
		out.println("<tr>");
		out.println("<th width=10%>번호</th>");
		out.println("<th width=45%>제목</th>");
		out.println("<th width=15%>내용</th>");
		out.println("<th width=20%>작성일</th>");
		out.println("<th width=10%>조회수</th>");
		out.println("</tr>");
		//출력위치
		for(BoardVO vo : list)
		{
			//html에서 화면 이동 방식 ==> 화면만 이동 : <a>  데이터를 전송+화면이동 : <form>
			out.println("<tr>");
			out.println("<td width=10% align=center>"+vo.getNo()+"</td>");
			out.println("<td width=45%><a href=BoardDetail?no="+vo.getNo()+">"+vo.getSubject()+"</a>");
			//boardDetail한테 no값을 보내겠다 => get방식 -> ?뒤에 보낼 값을 작성
			out.println("&nbsp;");
			if(today.equals(vo.getDbday()))
			{
				out.println("<sup><img src=image/new.gif></sup>");
			}
			out.println("</td>");
			out.println("<td width=15% align=center>"+vo.getName()+"</td>");
			out.println("<td width=20% align=center>"+vo.getDbday()+"</td>");
			out.println("<td width=10% align=center>"+vo.getHit()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<table class=table_content width=700>");
		out.println("<tr>");
		out.println("<td align=left>");
		out.println("<select>");
		out.println("<option>이름</option>");
		out.println("<option>제목</option>");
		out.println("<option>내용</option>");
		out.println("</select>");
		out.println("<input type=text size=15>");
		out.println("<input type=button value=검색>");
		out.println("</td>");
		out.println("<td align=right>");
		out.println("<a href=BoardList?page="+(curpage>1? curpage-1: curpage)+">이전</a>");
		out.println(curpage+" page / "+totalpage+" pages");
		out.println("<a href=BoardList?page="+(curpage<totalpage? curpage+1: curpage)+">다음</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}

}
