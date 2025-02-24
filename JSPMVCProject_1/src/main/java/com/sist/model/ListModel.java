package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import java.text.*;
//
/*
 	인터페이스 / 상속
 	=> 클래스의 영향을 미친다 -> 결합성이 높은 프로그램
 	=> 독립적인 클래스
 		---------- POJO
 		=> 인터페이스를 사용하지 않을 예정 => 어노테이션 
 
 */
public class ListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//1. 사용자 요청 -> page
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		//데이터 읽기
		Map map = new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", 10*curpage);
		
		List<BoardVO> list = BoardDAO.boardListData(map);
		
		int totalpage=BoardDAO.boardTotalPage();
		
		//list.jsp에 값 전송
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today);
		return "board/list.jsp";
	}

}
