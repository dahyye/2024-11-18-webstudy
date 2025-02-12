package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/**
 * Servlet implementation class MusicFind
 */
@WebServlet("/MusicFind")
public class MusicFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String strPage=request.getParameter("page");
		if(strPage==null)
		{
			strPage="1";
		}
		int curpage=Integer.parseInt(strPage);
		
		String column=request.getParameter("column");
		String md =request.getParameter("md");
		
		MusicDAO dao = MusicDAO.newInstance();
		List<MusicVO> list = dao.musicFind(curpage, column, md);
		int totalpage=dao.musicFindTotalPage(column, md);
		
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
		out.println("<div class=\"row\">");
		out.println("<form method=post action=MainServlet?mode=4>");
		out.println("<select name=column class=input-sm>");
		out.println("<option value=title>타이틀</option>");
		out.println("<option value=singer>가수명</option>");
		out.println("<option value=album>앨범</option>");
		out.println("</select>");
		out.println("<input type=text name=fd size=15 class=input-sm>");
		out.println("<button class=\"btn-sm btn-danger\">검색</button>");
		out.println("</form>");
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
		
		
		if(list!=null)
		{
			for(MusicVO vo:list)
			{
				out.println("<div class=\"col-md-3\">");
				out.println("<div class=\"thumbnail\">");
				out.println("<a href=\"MusicBeforeDetail?fno="+vo.getMno()+"\">");
				out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:150px\">");
				out.println("<div class=\"caption\">");
				out.println("<p>"+vo.getTitle()+"</p>");
				out.println("</div>");
				out.println("</a>");
				out.println("</div>");
				out.println("</div>");
			}
		}
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		// startPage = 1 , 11 , 21
		if(startPage>1)
		{
		  out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+md+"&page="+(startPage-1)+"\">&lt;</a></li>");
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		 if(i==curpage)
		  out.println("<li class=active><a href=\"MainServlet?mode=4&column="+column+"&fd="+md+"&page="+i+"\">"+i+"</a></li>");
		 else
		  out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+md+"&page="+i+"\">"+i+"</a></li>");
		}
		
		if(endPage<totalpage)
		{
		  out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+md+"&page="+(endPage+1)+"\">&gt;</a></li>");
		}
		out.println("</ul>");
	  
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

}
 