package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
 
 	1. 사용자의 전송방식
 		GET / POST 
 		| <a>, sendRedurect() => URL?데이터전송
 		| <form>, ajax, axios.post()
 		
 		검색어 입력 / 찾는 컬럼 => 두개를 동시에 전송해야해 -> <form> POST방식 사용
 		
 		처음 출력은 GET방식
 		
 		Get => doGet() ==> @GetMapping()
 		Post => doPost() ==> @PostMapping()
 		Get/Post => service() ==> @RequestMapping()    ==> 특별한 경우가 아니면 service 사용안함 -> 속도가 느려짐
 									=> Spring 6.0 : 권장
 	
 		JSP => doGet / doPost가 없다
 			   
 		
 
 */
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.*;
import com.sist.dao.*;

@WebServlet("/FoodTypeFind")
public class FoodTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		//2. 브러우저 연결
		PrintWriter out=response.getWriter();
		//3. 출력 전 오라클 데이터 읽기
		
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curpage=Integer.parseInt(strPage);
		
		String type=request.getParameter("type");
		if(type==null)
			type="한식";
		
		FoodDAO dao = FoodDAO.newInstance();
		List<FoodVO> list = dao.foodTypeFind(curpage, type);
		int totalpage=dao.foodTypeTotalPage(type);
		
		final int BLOCK=10;
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
		out.println("<div class=\"\row text-center\">");
		out.println("<a href=FoodTypeFind?type=한식 class=\"btn btn-sm btn-danger\">한식</a>");
		out.println("<a href=FoodTypeFind?type=양식 class=\"btn btn-sm btn-success\">양식</a>");
		out.println("<a href=FoodTypeFind?type=중식 class=\"btn btn-sm btn-info\">중식</a>");
		out.println("<a href=FoodTypeFind?type=일식 class=\"btn btn-sm btn-primary\">일식</a>");
		out.println("<a href=FoodTypeFind?type=카페 class=\"btn btn-sm btn-warning\">카페</a>");
		out.println("<a href=FoodTypeFind?type=기타 class=\"btn btn-sm btn-default\">기타</a>");
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
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
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+1+"\">&lt;&lt;</a></li>"); // < 화살표
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+(startPage-1)+"\">&lt;</a></li>"); // < 화살표
			
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
			if(i==curpage)
				out.println("<li class=active><a href=\"FoodTypeFind?page="+i+"\">"+i+"</a></li>");
			else
				out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+i+"\">"+i+"</a></li>");
		}
		
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+(endPage+1)+"\">&gt;</a></li>"); // > 화살표
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+totalpage+"\">&gt;&gt;</a></li>"); // >> 화살표
		}

		/*
		 http://localhost:8080/HTMLFoodProject/FoodTypeFind?type=%EC%96%91%EC%8B%9D
		 get방식은 자동으로 변환됨
		*/
		
		out.println("</ul>");
		
		out.println("</div>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
	

}
