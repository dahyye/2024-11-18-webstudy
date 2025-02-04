package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/FoodBeforeDetail")
public class FoodBeforeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키만 보낼거라서(html전송이 아님) response.setContentType 을 사용하지 않아
		String fno=request.getParameter("fno");
		Cookie cookie=new Cookie("food_"+fno, fno); //1번을 3번 클릭해도 한 번만 저장 // 고유번호
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24); //60*60*24 -> 하루를 뜻함 (저장기간)
		response.addCookie(cookie); //브라우저 전송
		// 쿠키 -> 브라우저에 저장 (클라이언트에 저장)
		// 보안에 취약 / 저장 -> 문자열만 저장이 가능
		// => 최신방문 / 로그인 여부
		// => 서버에 저장
		//Map방식 (키, 값) => 키는 중복이 불가능
		//상세보기로 이동
		response.sendRedirect("FoodDetail?fno="+fno);
		//		 ------------ GET
		/*
		 
		 사이트
		 ----
		 	목록-> 인라인뷰(페이징)
		 	상세보기 (찜하기, 좋아요, 예약, 결제) -> 댓글
		 	커뮤니티 : 게시판, 묻고답하기, 자료실, 실시간 채팅
		 	회원가입, 검색
		 	
		 			 
		 	=> 관리자페이지, 마이페이지
		 	
		 			 
		 */
		
	}

}
