package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String mno=request.getParameter("fno");
		MusicDAO dao=MusicDAO.newInstance();
		MusicVO vo=dao.musicDetailData(Integer.parseInt(mno));

		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<script src=js/update.js></script>");
		
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
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
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
		// => 지도 
		// => 댓글 
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
		// 화면 분할 
		out.println("<div class=col-sm-8>");
		out.println("<h3>댓글</h3>");
		
		// 댓글 출력 
		ReplyDAO rdao=ReplyDAO.newInstanse();
		List<ReplyVO> list=rdao.replyListData(Integer.parseInt(mno));
					
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		for(ReplyVO rvo:list)
		{
			out.println("<table class=table>");
			out.println("<tr>");
			out.println("<td class=text-left>");
			out.println("◑"+rvo.getName()+"&nbsp;(");
			out.println(rvo.getDbday()+")");
			out.println("</td>");	
			out.println("<td class=text-center>");
			if(rvo.getId().equals(id))
			{
				// <html> => 태그는 사용자 정의가 없다 속성은 사용자 정의가 가능
				 out.println("<span class=\"btn btn-xs btn-success update\" data-rno="+rvo.getRno()+">수정</span>");
				 out.println("<a href=ReplyInsert?fno="+mno+"&rno="+rvo.getRno()+" class=\"btn btn-xs btn-info\">삭제</a>");
			}
			out.println("</td>");	
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan=2>");
			out.println("<pre style=\"white-space:pre-wrap;background-color:white;border:none\">"+rvo.getMsg()+"</pre>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr id=\"m"+rvo.getRno()+"\" class=ups style=\"display:none\">");
			out.println("<td colspan=2>");
						
			out.println("<form method=post action=ReplyUpdate>");
			out.println("<textarea rows=4 cols=45 name=msg style=\"float:left\" required>"+rvo.getMsg()+"</textarea>");
			out.println("<input type=hidden name=fno value="+mno+">");
			out.println("<input type=hidden name=rno value="+rvo.getRno()+">");
			out.println("<input type=submit value=댓글수정 class=\"btn-primary\" style=\"float:left;width:80px;height:97px\">");
			out.println("</form>");
						
			out.println("</td>");
			out.println("</tr>");
						
			out.println("</table>");
		}
		out.println("</td>");		
		out.println("</tr>");
		out.println("</table>");
		/*
		 *   사용자가 전송한 값  "10"
		 *   --------------
		 *   1개 ==> String getParamter()
		 *   여러개 => String[] getParamterValues()
		 *   ------------ servlet / jsp / spring / spring-boot
		 */
		if(id!=null) // 로그인 된 경우에 사용이 가능
		{
			out.println("<form method=post action=ReplyInsert>");
			out.println("<table class=table>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<textarea rows=4 cols=58 name=msg style=\"float:left\" required></textarea>");
			out.println("<input type=hidden name=fno value="+mno+">");
			out.println("<input type=submit value=댓글쓰기 class=\"btn-primary\" style=\"float:left;width:80px;height:97px\">");
			out.println("</td>");		
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
		}
		out.println("</div>");
		out.println("<div class=col-sm-4>");
		out.println("<h3>인기음악</h3>");
		List<MusicVO> mList=dao.musicHitTop10();
		out.println("<table class=\"table table-striped\">");
		out.println("<tr>");
		out.println("<th class=text-center></th>");
		out.println("<th class=text-center>노래제목</th>");
		out.println("<th class=text-center>조회수</th>");
		out.println("</tr>");
		for(MusicVO mvo:mList)
		{
			out.println("<tr>");
			out.println("<td class=text-center><img src="+mvo.getPoster()+" width=30 height=30></td>");
			out.println("<td>"+mvo.getTitle()+"</td>");
			out.println("<td class=text-center>"+mvo.getHit()+"</td>");
			out.println("</tr>");
		}
		
		out.println("</table>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
