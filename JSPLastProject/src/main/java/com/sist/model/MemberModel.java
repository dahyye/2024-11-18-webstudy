package com.sist.model;

import java.io.PrintWriter;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberModel {
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response)
	{
		//include
		request.setAttribute("main_jsp", "../member/join.jsp");
		return "../main/main.jsp";
	}
	
	
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request, HttpServletResponse response)
	{
		
		return "../member/idcheck.jsp";
	}
	
	@RequestMapping("member/idcheck_ok.do")
	public void member_idcheck_ok(HttpServletRequest request, HttpServletResponse response)
	{
		//void => 일반데이터 (String, int)
		// 데이터가 많을 때는 JSON
		//data:{"id":id.trim()} ?id=aaa
		String id=request.getParameter("id");
		int count=MemberDAO.memberIdcheck(id);
		
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(count));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("member/join_ok.do!");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String email =request.getParameter("email");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String phone1=request.getParameter("phone1");
		String phone2=request.getParameter("phone2");
		String content=request.getParameter("content");
		System.out.println("vo가져오기");
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(phone1+"-"+phone2);
		vo.setContent(content);
		System.out.println(vo.getId());
		System.out.println("Insert가져오기");
		MemberDAO.memberInsert(vo);
		System.out.println("Insert완료");
		return "redirect:../main/main.do";
	}
	
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("member/login.do");
		return "../member/login.jsp";
	}
	//로그인
	@RequestMapping("member/login_ok.do")
	public void member_login_ok(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("member/login_ok.do");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		MemberVO vo = MemberDAO.memberLogin(id, pwd);
		System.out.println("모델"+vo.getMsg());
		if(vo.getMsg().equals("OK"))
		{
			System.out.println("로그인성공");
			HttpSession session=request.getSession();
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("sex", vo.getSex());
			session.setAttribute("admin", vo.getAdmin());
		}
		try {
			response.setContentType("text/html;charset=UTF-8");			
			PrintWriter out = response.getWriter();
			out.write(vo.getMsg());
		} catch (Exception e) {
			// TODO: handle exception
		}
		//return "redirect:../main/main.do";
	}
	//로그아웃
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:../main/main.do";
	}
}
