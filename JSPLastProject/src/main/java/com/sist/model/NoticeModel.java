package com.sist.model;
import java.util.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

@Controller
public class NoticeModel {
	private String[] types={"","일반공지","이벤트공지","맛집공지","여행공지","레시피공지"};
	@RequestMapping("admin/notice_list.do")
	public String notice_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		System.out.println(curpage);
		Map map=new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", 10*curpage);
		List<NoticeVO> list = NoticeDAO.boardListData(map);
		for(NoticeVO vo : list)
		{
			vo.setTypes(types[vo.getType()]);
		}
		int count= NoticeDAO.noticeTotalPage();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((curpage*10)-10);
		
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		//이중인크루드
		request.setAttribute("admin_jsp", "../notice/notice_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("admin/notice_insert.do")
	public String notice_insert(HttpServletRequest request, HttpServletResponse response)
	{
		
		request.setAttribute("admin_jsp", "../notice/notice_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("admin/notice_insert_ok.do")
	public String notice_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		String type=request.getParameter("type");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setType(Integer.parseInt(type));
		vo.setSubject(subject);
		vo.setContent(content);
		NoticeDAO.noticeInsert(vo);
		
		return "redirect:../admin/notice_list.do";
	}
	

	@RequestMapping("admin/notice_detail.do")
	public String notice_detail(HttpServletRequest request, HttpServletResponse response)
	{	
		String no=request.getParameter("no");
		NoticeVO vo = NoticeDAO.noticeDetailData(Integer.parseInt(no), 0);
		vo.setTypes(types[vo.getType()]);
		
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../notice/notice_detail.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("notice/notice_user_list.do")
	public String notice_user_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		System.out.println(curpage);
		Map map=new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", 10*curpage);
		List<NoticeVO> list = NoticeDAO.boardListData(map);
		for(NoticeVO vo : list)
		{
			vo.setTypes(types[vo.getType()]);
		}
		int count= NoticeDAO.noticeTotalPage();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((curpage*10)-10);
		System.out.println("유저리스트");
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		
		request.setAttribute("main_jsp", "../notice/notice_user_list.jsp");
		return "../main/main.jsp";
	}
		
	@RequestMapping("notice/notice_user_detail.do")
	public String notice_user_detail(HttpServletRequest request, HttpServletResponse response)
	{	
		System.out.println("디테일");
		String no=request.getParameter("no");
		NoticeVO vo = NoticeDAO.noticeDetailData(Integer.parseInt(no), 1);
		vo.setTypes(types[vo.getType()]);
		System.out.println("값받기완료");
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../notice/notice_user_detail.jsp");
		return "../main/main.jsp";
	}
		
}
