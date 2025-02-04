package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String mno=request.getParameter("mno");
		MusicDAO dao=MusicDAO.newInstance();
		MusicVO vo=dao.musicDetailData(Integer.parseInt(mno));

		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td width=30% class=text-center rowspan=4>");
		out.println("<img src="+vo.getPoster()+" style=\"width:270px; height:300px\">");
		out.println("</td>");
		out.println("<td colspan=2>");
		if(vo.getState().equals("유지"))
		{
			out.println("<h3>"+vo.getTitle()+"&nbsp; <span style=\"color:orange\">"+vo.getState()+"</span></h3>");
		}
		else if(vo.getState().equals("상승"))
		{
			out.println("<h3>"+vo.getTitle()+"&nbsp; <span style=\"color:orange\">"+"▲"+vo.getIncrement()+"</span></h3>");
		}
		else
		{
			out.println("<h3>"+vo.getTitle()+"&nbsp; <span style=\"color:orange\">"+"▼"+vo.getIncrement()+"</span></h3>");
		}
		
		out.println("</td>");
		out.println("</tr>");
		/*
		 TITLE     NOT NULL VARCHAR2(1000) 
		SINGER    NOT NULL VARCHAR2(500)  
		ALBUM     NOT NULL VARCHAR2(500)  
		POSTER             VARCHAR2(260)  
		IDCREMENT          NUMBER         
		STATE              VARCHAR2(30)   
		KEY                VARCHAR2(200)  
		HIT                NUMBER     
		 */
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">가수</td>");
		out.println("<td width=60%>"+vo.getSinger()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">앨범</td>");
		out.println("<td width=60%>"+vo.getAlbum()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">조회수</td>");
		out.println("<td width=60%>"+vo.getHit()+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("<table class=table>");
		
		out.println("<tr>");
		out.println("<td class=text-right>");
		out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		out.println("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
		out.println("<a href=# class=\"btn btn-xs btn-info\">예약하기</a>");
		out.println("<a href=FoodList class=\"btn btn-xs btn-primary\">목록</a>");
		out.println("<a href=MainServlet class=\"btn btn-xs btn-warning\">홈</a>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
