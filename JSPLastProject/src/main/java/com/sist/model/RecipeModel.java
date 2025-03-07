package com.sist.model;
import java.util.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

/*
 
 	MVC 
 	=> jsp : 링크(요청)
 	=> 요청 내용 받기
 	=> -------------- request.getParametor()
 	=> 요청 처리 => DAO 연동
 	=> JSP로 결과값 전송
 	
 	
 */
@Controller
public class RecipeModel {
	@RequestMapping("recipe/recipe_list.do") //if문이 포함! -> if문 대신에 사용한다
	public String recipe_list(HttpServletRequest request, HttpServletResponse response)
	{
		//처리 -> 주문서, 처리 후 -> 어떤 테이블에 가져갈 것인지 결정
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", (12*curpage)-11);
		map.put("end", 12*curpage);
		List<RecipeVO> list=RecipeDAO.recipeListData(map);
		
		int totalpage=FoodDAO.foodTotalPage();
		  
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		  
		if(endPage>totalpage)
			endPage=totalpage;
		  
		request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
				
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef_list.do") //if문이 포함! -> if문 대신에 사용한다
	public String chef_list(HttpServletRequest request, HttpServletResponse response)
	{
		
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", (12*curpage)-11);
		map.put("end", 12*curpage);
		List<ChefVO> list=RecipeDAO.recipeChefListData(map);
		System.out.println(list.size());
		System.out.println("list");
		int totalpage=FoodDAO.foodTotalPage();
		System.out.println(totalpage);   
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "../main/main.jsp";
	}
	
	
	
	
	
	
	
	
	

}
