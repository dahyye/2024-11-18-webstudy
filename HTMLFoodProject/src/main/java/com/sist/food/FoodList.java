package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie; // 

import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;


@WebServlet("/FoodList")
public class FoodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저로 전송 -> HTML을 전송하겠다
		// HTML, XML, JSON -> response (cookic전송도 가능)
		response.setContentType("text/html; charset=UTF-8");
		//2. 브러우저 연결
		PrintWriter out=response.getWriter();
		//3. 출력 전 오라클 데이터 읽기
		
		FoodDAO dao = FoodDAO.newInstance();
		//4. 사용자가 요청한 데이터 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		//현재 페이지에 대한 데이터 얻기
		List<FoodVO> list =dao.foodListData(curpage);
		//총페이지 읽기
		int totalpage=dao.foodTotalPage();
		
		//블럭별 페이지
		final int BLOCK=10;
		/*
		 	1~10 -> startpage는 1, end는 10을 유지해야해
		 	11~20 -> startpage는 11, end는 20을 유지해야해
		 
		 */
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		/*
		 
		 	response는 기능을 한 개만 수행할 수 있다
		 	---------------------------------
		 	1. 쿠키전송
		 	2. HTML전송
		 */
		for(FoodVO vo : list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"FoodBeforeDetail?fno="+vo.getFno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:150px;\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getName()+"</p>");
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
			out.println("<li><a href=\"FoodList?page="+1+"\">&lt;&lt;</a></li>"); // < 화살표
			out.println("<li><a href=\"FoodList?page="+(startPage-1)+"\">&lt;</a></li>"); // < 화살표
			
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
			if(i==curpage)
				out.println("<li class=active><a href=\"FoodList?page="+i+"\">"+i+"</a></li>");
			else
				out.println("<li><a href=\"FoodList?page="+i+"\">"+i+"</a></li>");
		}
		
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"FoodList?page="+(endPage+1)+"\">&gt;</a></li>"); // > 화살표
			out.println("<li><a href=\"FoodList?page="+totalpage+"\">&gt;&gt;</a></li>"); // >> 화살표
		}

		
		
		out.println("</ul>");
		out.println("</div>");
		out.println("<div class=row>");
		out.println("<h3>최신 방문 맛집</h3>");
		out.println("<hr>");
		List<FoodVO> cList=new ArrayList<FoodVO>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
			//최신순
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("food_"))
				{
					String fno=cookies[i].getValue();
					FoodVO vo=dao.foodCookieData(Integer.parseInt(fno));
					cList.add(vo);
					
				}
			}
		}
		for(int i=0;i<cList.size();i++)
		{
			FoodVO cvo=cList.get(i);
			if(i>8) break;
			out.println("<a href=FoodDetail?fno="+cvo.getFno()+">");
			out.println("<img src="+cvo.getPoster()+" style=\"width:100px; height:100px\" class=img-rounded title="+"'"+cvo.getName()+"'"+" mar>");
			out.println("</a>");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
