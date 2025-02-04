package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/FoodDetail")
public class FoodDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		//브라우저로 전송
		PrintWriter out=response.getWriter();
		/*
		 	request -> 클라이언트의 정보를 가지고 있다(ip, port)
		 			-> 사용자 요청 정보를 가지고 있다(사용자가 보낸 값)
		 				=> String getParameter()
		 				=> String[] getParameterValues() => 다중값 => checkbox
		 				=> encoding(bype[])로 전송하기 때문에 -> decoding(원상복귀)가 필요하다
							=> setCharaterEncoding("UTF-8")
		 	
		 	response -> 서버에서 전송하는 정보(HTML, cookie)
		 								 -----	------- addCookie
		 								 setContentType
		 								 
		 			 -> 화면 이동 정보를 가지고 있다
		 			 	---------- sendRedirect()
		 */
		//출력 전 사용자가 보낸 데이터 받기
		String fno=request.getParameter("fno");
		//데이터베이스연동
		FoodDAO dao=FoodDAO.newInstance();
		FoodVO vo= dao.foodDetailData(Integer.parseInt(fno));
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
		StringTokenizer st= new StringTokenizer(vo.getImages(),",");
		int i=0;
		int count=st.countTokens();
		while(st.hasMoreTokens())
		{
			if(i==7) break;
			out.println("<td class=text-center>");
			out.println("<img src=https://www.menupan.com"
			       +st.nextToken()+" style=\"width:130px;height:100px\">");
			out.println("</td>");
			i++;
		}
		out.println("</tr>");
		out.println("</table>");
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td width=30% class=text-center rowspan=8>");
		out.println("<img src="+vo.getPoster()+" style=\"width:270px; height:300px\">");
		out.println("</td>");
		out.println("<td colspan=2>");
		out.println("<h3>"+vo.getName()+"&nbsp; <span style=\"color:orange\">"+vo.getScore()+"</span></h3>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">업종</td>");
		out.println("<td width=60%>"+vo.getType()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">주소</td>");
		out.println("<td width=60%>"+vo.getAddress()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">전화</td>");
		out.println("<td width=60%>"+vo.getPhone()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">영업시간</td>");
		out.println("<td width=60%>"+vo.getTime()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">주차</td>");
		out.println("<td width=60%>"+vo.getParking()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">가격대</td>");
		out.println("<td width=60%>"+vo.getPrice()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">테마</td>");
		out.println("<td width=60%>"+vo.getTheme()+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getContent());
		out.println("</td>");
		out.println("</tr>");
		
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
