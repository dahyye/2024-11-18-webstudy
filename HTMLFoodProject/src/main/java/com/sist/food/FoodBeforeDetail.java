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
		cookie.setMaxAge(60*60*24); //60*60*24 -> 하루를 뜻함
		response.addCookie(cookie);
	}

}
