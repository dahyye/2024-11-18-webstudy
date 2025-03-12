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
		String user_id=request.getParameter("user_id");
		int count=MemberDAO.memberIdcheck(user_id);
		
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
		String user_id=request.getParameter("user_id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String nickname=request.getParameter("nickname");
		String profile_img=request.getParameter("profile_img");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String email =request.getParameter("email");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String phone1=request.getParameter("phone1");
		String phone2=request.getParameter("phone2");
		System.out.println("vo가져오기");
		MemberVO vo=new MemberVO();
		vo.setUser_id(user_id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setNickname(nickname);
		vo.setProfile_img(profile_img);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(phone1+"-"+phone2);
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
		String user_id=request.getParameter("user_id");
		String pwd=request.getParameter("pwd");
		MemberVO vo = MemberDAO.memberLogin(user_id, pwd);
		System.out.println("모델"+vo.getMsg());
		if(vo.getMsg().equals("OK"))
		{
			System.out.println("로그인성공");
			HttpSession session=request.getSession();
			session.setAttribute("user_id", vo.getUser_id());
			session.setAttribute("name", vo.getName());
			session.setAttribute("sex", vo.getSex());
			session.setAttribute("admin", vo.getAdmin());
		}
		System.out.println("if문 종료");
		try {
			System.out.println("try문");
			response.setContentType("text/html;charset=UTF-8");			
			PrintWriter out = response.getWriter();
			out.write(vo.getMsg());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
