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
	
	@RequestMapping("recipe/recipe_find.do")
	public String recipe_find(HttpServletRequest request, HttpServletResponse response)
	{
		String[] findArr = request.getParameterValues("fs"); /* 하나가 들어올 지 여러개가 들어올 지 모르기 때문에 배열로 */
		if(findArr==null)
		{
			findArr=new String[] {"T"};
		}
		String ss = request.getParameter("ss"); /* 검색어 */
		if(ss==null)
		{
			ss="만개";
		}
		Map map=new HashMap();
		map.put("findArr", findArr);
		map.put("ss", ss);
		List<RecipeVO> list = RecipeDAO.recipeFindData(map);
		request.setAttribute("list", list);		
		//메인으로 넘어갈 때 헤더 풋터만 채워져있고 홈은 비워져있으니까 그 부분을 채워주야해
		request.setAttribute("main_jsp",
				"../recipe/recipe_find.jsp"); /* 인크루드되면 데이터가 공유되기 때문에 list를 메인,헤더,풋터,파인드 다 사용가능 */
		return "../main/main.jsp";
	}
	
	/*
	 1. return "../main/main_jsp"; => 화면출력할 때 사용 (ex. 메뉴클릭시)
	 
	 2. return "../food/food_jsp"; => ajax 사용시 결과 가져올 때
 
	 3. return "redirect:../main/main.do"; => insert나 update시 
	 */
	
	

	@RequestMapping("recipe/recipe_detail.do")
	public String recipe_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		RecipeDetailVO vo = RecipeDAO.recipeDetailData(Integer.parseInt(no));
		//jsp는 받을 때 request아니면 session
		//request는 한 번 받고 버리는데 session은 계속 가지고 있어
		List<String> mList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		
		String[] datas=vo.getFoodmake().split("\n");
		for(String make:datas)
		{
			StringTokenizer st = new StringTokenizer(make,"^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
			/* <c:forEach var="make" item="${mList}">  이건 이중for문 만들기가 어려워*/
		
		}
		
		ReplyVO rvo = new ReplyVO();
		rvo.setRno(Integer.parseInt(no));
		rvo.setType(2);
		  
		List<ReplyVO> list = ReplyDAO.replyListData(rvo);
		  
		int count=ReplyDAO.replyCount(rvo);
		  
	    request.setAttribute("count", count);
		  
		request.setAttribute("vo", vo);
		request.setAttribute("mList", mList);
		request.setAttribute("iList", iList);
		request.setAttribute("main_jsp", "../recipe/recipe_detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef_make.do")
	public String chef_make(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map = new HashMap();
		map.put("no", no);
		map.put("start", (12*curpage)-11);
		map.put("end", 12*curpage);
		List<RecipeVO> list=RecipeDAO.recipeChefMakeData(map);	
		int totalpage=RecipeDAO.recipeChefMakeDataTotalpage(Integer.parseInt(no));
		  
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
		request.setAttribute("no", no);
		request.setAttribute("chef", list.get(0).getChef());
		
		request.setAttribute("main_jsp", "../recipe/chef_make.jsp");
		return "../main/main.jsp";
	}
	

}



















