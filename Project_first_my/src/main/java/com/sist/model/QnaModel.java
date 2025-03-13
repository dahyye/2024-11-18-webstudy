package com.sist.model;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class QnaModel {
	@RequestMapping("qna/qna_list.do")
	public String qna_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		System.out.println(curpage);
		Map map=new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", 10*curpage);
		List list = QnaDAO.qnaListData(map);
		int count= QnaDAO.qnaRowCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((10*curpage)-10);
		
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		request.setAttribute("main_jsp", "../qna/qna_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("qna/qna_insert.do")
	public String qna_insert(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../qna/qna_insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("qna/qna_insert_ok.do")
	public String qna_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("인서트 진입");
		QnaVO vo =new QnaVO();
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String type=request.getParameter("type");
		String type_detail=request.getParameter("type_detail");
		String reserve_no=request.getParameter("reserve_no");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		//id와 name는 session에서 받아오기
		HttpSession session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		String name=(String)session.getAttribute("name");
		System.out.println("try진입전");
		try {
			Part filePart=request.getPart("filename"); //항상 ("")안에는 name 속성값이 들어가야한다
			String filename=filePart.getSubmittedFileName();
			System.out.println(filename);
			if(filename==null || filename.equals(""))//업로드가 안된상태
			{
				System.out.println("파일없을때");
				vo.setFilename("");
				vo.setFilesize(0);
			}
			else
			{
				String uploadDir="c:\\upload";
				File file=new File(uploadDir,filename);
				//오라클 -> 파일 업로드
				//try ~ resource -> 자동으로 input/output 바뀜
				try(InputStream input=filePart.getInputStream();
						FileOutputStream output=new FileOutputStream(file))
				{
					byte[] buffer=new byte[1024];
					int i=0;
					while((i=input.read(buffer,0,1024))!=-1)
					{
						output.write(buffer,0,i);
					}
				}
				System.out.println("파일업로드");
				vo.setFilename(filename);
				vo.setFilesize((int)file.length());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("트라이나와서");
		vo.setUser_id(user_id);
		vo.setName(name);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setType(type);
		vo.setType_detail(type_detail);
		vo.setReserve_no(reserve_no);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		System.out.println("값 넣고");
		QnaDAO.qnaInsert(vo);
		System.out.println("인서트");
		return "redirect:../qna/qna_list.do";
	}
	
	
	/* 관리자모드 */
	@RequestMapping("qna/qna_admin_list.do")
	public String qna_admin_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		System.out.println(curpage);
		Map map=new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", 10*curpage);
		
		List<QnaVO> list = QnaDAO.qnaAdminListData(map);
		int count= QnaDAO.qnaAdminRowCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((10*curpage)-10);
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("count", count);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		request.setAttribute("admin_jsp", "../qna/qna_admin_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("qna/qna_admin_insert.do")
	public String qna_admin_insert(HttpServletRequest request, HttpServletResponse response)
	{
		String gi=request.getParameter("gi");

		// => vo읽기
		QnaVO vo=QnaDAO.qnaAdminDetailData(Integer.parseInt(gi));
		System.out.println("vo받아오기");
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../qna/qna_admin_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("qna/qna_admin_insert_ok.do")
	public String qna_admin_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
				
		System.out.println("insert_ok");
		QnaVO vo =new QnaVO();
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String type=request.getParameter("type");
		String type_detail=request.getParameter("type_detail");
		String reserve_no=request.getParameter("reserve_no");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String group_id=request.getParameter("group_id");
		try {
			Part filePart=request.getPart("filename"); //항상 ("")안에는 name 속성값이 들어가야한다
			String filename=filePart.getSubmittedFileName();
			System.out.println(filename);
			if(filename==null || filename.equals(""))//업로드가 안된상태
			{
				System.out.println("파일없을때");
				vo.setFilename("");
				vo.setFilesize(0);
			}
			else
			{
				String uploadDir="c:\\upload";
				File file=new File(uploadDir,filename);
				//오라클 -> 파일 업로드
				//try ~ resource -> 자동으로 input/output 바뀜
				try(InputStream input=filePart.getInputStream();
						FileOutputStream output=new FileOutputStream(file))
				{
					byte[] buffer=new byte[1024];
					int i=0;
					while((i=input.read(buffer,0,1024))!=-1)
					{
						output.write(buffer,0,i);
					}
				}
				System.out.println("파일업로드");
				vo.setFilename(filename);
				vo.setFilesize((int)file.length());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//id와 name는 session에서 받아오기
		HttpSession session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		String name=(String)session.getAttribute("name");
		
		vo.setUser_id(user_id);
		vo.setName(name);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setType(type);
		vo.setType_detail(type_detail);
		vo.setReserve_no(reserve_no);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setGroup_id(Integer.parseInt(group_id));
		
		QnaDAO.qnaAdminInsert(vo);
		
		return "redirect:../qna/qna_admin_list.do";
	}

}
