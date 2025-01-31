package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
//코드상 문제 없을 때 -> Project -> clean으로 이전 기록을 지워준다

@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// request : 사용자가 보내준 데이터를 가지고 있다
		// response : 사용자 브라우저 정보
		request.setCharacterEncoding("UTF-8");
		//=>2byte형식 전송값을 받는다(디코딩)
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		//정상수행하는지 확인
		System.out.println("이름 : "+name);
		System.out.println("제목 : "+subject);
		System.out.println("내용 : "+content);
		System.out.println("비번 : "+pwd);
		
		//오라클 연동
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO dao = BoardDAO.newInstance();
		dao.boardInsert(vo);
		//화면이동
		response.sendRedirect("BoardList");
		//흐름 -> 요청 -> 어떤 파일로 이동할것인지
		
	}

}
