package com.sist.model;
/*
 	JSP ======== FoodModel <==> 데이터베이스 연동
 	 |	  요청		| 
 	 |	  		List<FoodVO>
 	 | ____________ |	|
 		    			
 		    |
 		    request(List<FoodVO>)
 		    |
 		    ${}
 	
 	JSP ======== Controller ============> Model <====> DB
 								
 */
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
public class FoodModel {
	public void foodListData(HttpServletRequest request)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1"; // 초기값 지정 => 오류 
		// 현재페이지 설정
		int curpage=Integer.parseInt(page);
		// 현재 페이지에 대한 데이터 얻기
		Map map=new HashMap();
		map.put("start", (12*curpage)-11);
		map.put("end", 12*curpage);
		List<FoodVO> list=FoodDAO.foodListData(map);
		// 총페이지 읽기 
		int totalpage=FoodDAO.foodTotalPage();
		
		final int BLOCK=10;
		/*
		 *  1~10 => startPage = 1
		 *  1~10 => endPage = 10
		 *  
		 *  11~20 => startPage = 11
		 */
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		//               10-1 => 9/10 0
		//               11-1/10 => 1*10 => 10+1 => 11
		// 1 11 21 31 ...
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// 10 20 30 40...
		if(endPage>totalpage)
		    endPage=totalpage;
		
		//JSP로 화면에 출력핳ㄹ 데이터를 선종
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
	}
	
	public void foodDetailData(HttpServletRequest request)
	{
		String fno=request.getParameter("fno");
		FoodVO vo = FoodDAO.foodDetailDate(Integer.parseInt(fno));
		request.setAttribute("vo", vo);
	}
}
