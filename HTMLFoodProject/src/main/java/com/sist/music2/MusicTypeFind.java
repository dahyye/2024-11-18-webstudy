package com.sist.music2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicTypeFind")
public class MusicTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		//2. 브러우저 연결
		PrintWriter out=response.getWriter();
		//3. 출력 전 오라클 데이터 읽기
		
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curpage=Integer.parseInt(strPage);
		
		String cno=request.getParameter("cno");
		if(cno==null)
			cno="2";
		
		MusicDAO dao = MusicDAO.newInstance();
		List<MusicVO> list = dao.musicTypeFind(curpage, Integer.parseInt(cno));
		int totalpage=dao.foodTypeTotalPage(Integer.parseInt(cno));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/Music.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=\"\row text-center\">");
		out.println("<a href=MusicTypeFind?cno=2 class=\"btn btn-sm btn-danger\">top50</a>");
		out.println("<a href=MusicTypeFind?cno=1 class=\"btn btn-sm btn-success\">가요</a>");
		out.println("<a href=MusicTypeFind?cno=3 class=\"btn btn-sm btn-info\">pop</a>");
		out.println("<a href=MusicTypeFind?cno=4 class=\"btn btn-sm btn-primary\">ost</a>");
		out.println("<a href=MusicTypeFind?cno=5 class=\"btn btn-sm btn-warning\">트로트</a>");
		out.println("<a href=MusicTypeFind?cno=6 class=\"btn btn-sm btn-success\">jazz</a>");
		out.println("<a href=MusicTypeFind?cno=7 class=\"btn btn-sm btn-info\">classic</a>");
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
	
		for(MusicVO vo : list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"MusicBeforeDetail?mno="+vo.getMno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:180px;height:150px;\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getTitle()+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
			
		}
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		
		if(startPage>1)
		{
			out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+1+"\">&lt;&lt;</a></li>"); // < 화살표
			out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+(startPage-1)+"\">&lt;</a></li>"); // < 화살표
			
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
			if(i==curpage)
				out.println("<li class=active><a href=\"MusicList?page="+i+"\">"+i+"</a></li>");
			else
				out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+i+"\">"+i+"</a></li>");
		}
		
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+(endPage+1)+"\">&gt;</a></li>"); // > 화살표
			out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+totalpage+"\">&gt;&gt;</a></li>"); // >> 화살표
		}

		
		
		out.println("</ul>");
		

		out.println("</div>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
	}

}
