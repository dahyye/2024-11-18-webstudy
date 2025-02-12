package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.sist.dao.*;
import com.sist.vo.*;
import jakarta.servlet.http.HttpSession;
//session에서 id랑 name을 가져와야해 

@WebServlet("/ReplyInsert")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fno=request.getParameter("fno");
		String rno=request.getParameter("rno");
	
		ReplyDAO dao = ReplyDAO.newInstanse();
		dao.replyDelete(Integer.parseInt(rno));

		response.sendRedirect("MainServlet?mode=2&fno="+fno);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//사용자가 보낸 데이터 받기
		//<input name="fno">
		//?fno=10
		String fno=request.getParameter("fno");
		String msg=request.getParameter("msg");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id"); //세션에 저장된 값 가져올 때 사용하는 코드
		String name=(String)session.getAttribute("name");
		
		ReplyVO vo = new ReplyVO();
		vo.setFno(Integer.parseInt(fno));
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		//dao랑 데이터값 맞추기 
		
		ReplyDAO dao = ReplyDAO.newInstanse();
		dao.replyInsert(vo);
		
		//화면출력 -> FoodDetail출력
		response.sendRedirect("MainServlet?mode=2&fno="+fno);
		/*								   ---	  ---
										   main	   detail
		*/
		
		
		
	}

}
