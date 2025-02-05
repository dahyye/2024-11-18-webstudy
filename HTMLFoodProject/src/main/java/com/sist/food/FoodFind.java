package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;


@WebServlet("/FoodFind")
public class FoodFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// GET / POST를 동시에 처리할 때 사용한다
	/*
		doGet()
		{
			화면출력
		}
		doPost()
		{
			검색어를 받는다 => 데이터연동
		}
		
		==> 따로하면 검색어를 받을 때 화면출력이 안된다 
		==> 그래서 두 개를 합친 service 사용
		
	
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
