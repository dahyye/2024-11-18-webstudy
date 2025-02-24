package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.model.DeleteModel;
import com.sist.model.InsertModel;
import com.sist.model.ListModel;
import com.sist.model.UpdateModel;
/*
 		  요청		   해당model찾기	
 	사용자 ===== 컨트롤러 ========= model <==========> 데이터베이스 연동
 		 =====		  =========
 	      응답			데이터베이스 연동 후 데이터 읽기
 	      
 	 1. MVC => MVP / MVVM(VueJS)
 		MVC는 양방향 통신이 안된다(단방향 통신만 가능)
 			
 		=> 약자 (Model ------------------- View ----------------------- Controller)
 				 |							|							   |
 			  데이터베이스처리 DAO			처리된 데이터 출력				 송수신 ( 요청->응답 )
 			  데이터를 모아준다 VO
 			  처리내용 전송 Model
 			  서비스 처리단
 	
 	=> 장점
 		구성요소가 명확히 나눠져 있다(Front, Back) => 역할 분담이 좋다
 		데이터 흐름이 단방향이라서 분석이 쉽다
 		재사용이 좋디
 		리팩토링 비용이 낮아진다
 		----- 변수 / 메소드 => 가독성
 	=> 단점
 		컨트롤러가 복잡해지고 코드 중복이 발생
 		뷰와 모델을 수동으로 처리해야한다
 		양방향 통신으로 변경하면 추가 작업이 필요
 		컨트롤러에 의존하는 경향이 많다
 		----- 분산(MSA)
 	
 	=> 역할
 		1. Controller
 			사용자 요청 받기
 			요청 처리 Model클래스 호출 -> 어떻게 찾을 것인데
 			요청 처리된 데이터를 View로 전송
 		2. Model
 			요청 처리 담당(비지니스 로직)
 		3. View
 			전송 데이터를 출력
 			
 		view-> controller -> model -> controller -> view
 		
 		Controller : 서빙
 		Model : 주방
 		View : 손님
 		
 		손님 -> 주문 -> 서빙 -> 주방에 요청
 		
 		서빙 -> 완성된 음식 -> 손님
 		
 		 단점 => 손님이 많으면 속도가 느려지거나 실수르 할 수 있다
 		 
 		 웹사이트는 98%가 MVC로 되어 있다
		
		요청 => ?cmd=list => ListModel => list.jsp
 			  
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Servlet은 화면 출력은 없다 => 자바와 JSP연결
		//1. 사용자 주문을 받는다
		String cmd=request.getParameter("cmd");
		if(cmd==null)
			cmd="list";
		//2. 주방에 요청 => 해당 모델을 찾는다
		String jsp="";
		if(cmd.equals("list"))
		{
			ListModel model=new ListModel();
			model.execute(request);
			jsp="list.jsp";
		}
		else if(cmd.equals("inset"))
		{
			InsertModel model=new InsertModel();
			model.execute(request);
			jsp="insert.jsp";
		}
		else if(cmd.equals("update"))
		{
			UpdateModel model=new UpdateModel();
			model.execute(request);
			jsp="update.jsp";
		}
		else if(cmd.equals("delete"))
		{
			DeleteModel model=new DeleteModel();
			model.execute(request);
			jsp="delete.jsp";
		}
		
		//3. 주방에서 음식을 가지고 온다
		//4. 테이블에 올려준다
		RequestDispatcher rd = request.getRequestDispatcher("board/"+jsp);
		rd.forward(request, response);
		
	}

}
