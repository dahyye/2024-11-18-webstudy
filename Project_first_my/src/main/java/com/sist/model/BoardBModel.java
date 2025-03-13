package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class BoardBModel {
	@RequestMapping("board/board_list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		System.out.println(page);
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		System.out.println(curpage);
		Map map=new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", 10*curpage);
		System.out.println("list받기 전");
		List<BoardVO> list = BoardDAO.boardListData(map);
		System.out.println(list.size());
		int totalpage= BoardDAO.boardTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		
		request.setAttribute("main_jsp", "../board/board_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("board/board_insert.do")
	public String board_insert(HttpServletRequest request, HttpServletResponse response)
	{
	
		request.setAttribute("main_jsp", "../board/board_insert.jsp");
		return "../main/main.jsp";
	}
	/*
	 </selectKey>
  INSERT INTO qna_board VALUES(
   #{no}, #{name}, #{email}, #{phone}, #{type}, #{type_detail}, {#reserve_no}, #{subject}, #{content}, #{filename}, #{filesize}, #{pwd}, SYSDATE, 0
  )
</insert>
	
	
	 */
	@RequestMapping("board/board_insert_ok.do")
	public String board_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		BoardVO vo = new BoardVO();
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String type=request.getParameter("type");
		String type_detail=request.getParameter("type_detail");
		String reserve_no=request.getParameter("reserve_no");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		System.out.println(name);
		System.out.println(content);
		try {
			Part filePart=request.getPart("filename"); //항상 ("")안에는 name 속성값이 들어가야한다
			String filename=filePart.getSubmittedFileName();
			System.out.println(filename);
			if(filename==null || filename.equals(""))//업로드가 안된상태
			{
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
		/* String filesize=request.getParameter("filesize"); */
		String pwd=request.getParameter("pwd");
		System.out.println("vo에 데이터 넣기 전");
		vo.setName(name);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setType(type);
		vo.setType_detail(type_detail);
		vo.setReserve_no(reserve_no);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		System.out.println("vo에 데이터 넣기 후");
		System.out.println(vo.getFilename());
		System.out.println(vo.getFilesize());
		System.out.println("insert진입");
		BoardDAO.boardInsert(vo);
		return "redirect:../board/board_list.do";
	}
	@RequestMapping("board/board_detail.do")
	public String board_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String page=request.getParameter("page");
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.setAttribute("main_jsp", "../board/board_detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("board/board_pwd_ajax.do")
	public void board_pwd_ajax(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		String db_pwd=BoardDAO.boardGetPassword(Integer.parseInt(no));
		int res=0;
		if(db_pwd.equals(pwd))
		{
			res=1;
		}
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(res));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("board/board_delete_ajax.do")
	public void board_delete_ajax(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		PrintWriter out = null;
		
		try {
			BoardDAO.boardDelete(Integer.parseInt(no));
			response.setContentType("text/html;charset=UTF-8");
			out=response.getWriter();
			out.write("yes");
		} catch (Exception e) {
			// TODO: handle exception
			out.write("no");
		}
	}
	
}
