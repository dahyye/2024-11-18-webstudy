package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
 
 	2개 -> 폼 제작  /  요청처리
 		  -----		 -------
 		  GET		  POST
 		 수정/삭제
 		-> jsp는 GET/POST가 나뉘지지 않았다
 			delete.jsp / delete_ok.jsp
 
 */
import java.io.PrintWriter;

import com.sist.dao.BoardDAO;

@WebServlet("/BoardDelete")
//  html이 필요한 영역 : List / Insert / Delete / Detail / Update 
//-> 처리가 필요한 부분 :  o		  o => 화면 관련이 없다
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//삭제 폼 제작
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저에 전송 방식
		response.setContentType("text/html;charset=UTF-8");
		// 2. html을 읽어갈 브라우저의 정보  확인
		PrintWriter out = response.getWriter();
		// 3. 사용자가 보낸 데이터 받기
		String no=request.getParameter("no");
		// 웹은 전송된 모든 데이터가 String
		//삭제화면
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=css/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>삭제하기</h1>");
		out.println("<form method=post action=BoardDelete>");
		//BoardDelete가 가지고 있는 doPost를 호출하라는 뜻
		//doGet() doPost() 두개를 동시에 호출하지 않는다
		out.println("<table class=table_content width=350>");
		out.println("<tr>");
		out.println("<td>");
		out.println("비밀번호:<input type=password size=20 name=pwd required>");
		out.println("<input type=hidden name=no value="+no+">");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td align=center>");
		out.println("<input type=submit value=삭제>");
		out.println("<input type=button value=취소 onclick=javascript:history.back()>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	//삭제 관련 요청처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제처리 -> 비밀번호 처리 필요 -> JavaScript -> alert(java의 joptionpanel 역할)
		// 1. 브라우저에 전송 방식
		response.setContentType("text/html;charset=UTF-8");
		// 2. html을 읽어갈 브라우저의 정보  확인
		PrintWriter out = response.getWriter();
		// 3. 사용자가 보낸 데이터 받기
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		//오라클 연결
		BoardDAO dao = BoardDAO.newInstance();
		boolean bCheck = dao.boardDelete(Integer.parseInt(no), pwd);
		
		//화면이동
		//1. 비밀번호가 틀린 경우 -> alert
		if(bCheck==false)
		{
			out.println("<script>");
			out.println("alert(\"비밀번호가 틀립니다!!\");");
			out.println("history.back();");
			out.println("</script>");
		}
		//2. 비밀번호가 맞은 경우 -> 게시판 목록이동
		if(bCheck==true)
		{
			
			response.sendRedirect("BoardList");
		}
	}

}
