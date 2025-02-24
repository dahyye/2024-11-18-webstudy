package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.model.*;
/*
 	1 . MVC동작 구조(.jsp는 작동하지 않는다)
 	
 	JSP ============================ Controller ========== 위임
 	<a href="list.do">										|
 	<form action="insert.do"							  요청처리
 	2. M(Model)
 	3. V(Veiw)
 	4. C(Controller)
 	
 	Model : 사용자 요청을 처리해주는 클래스 집합
 			-------------
 			~VO / ~DAO / ~Service / ~Manager => Model
 			Controller로부터 요청처리 -> 처리된 결과를 request에 담아준다
 			=> request를 받을 jsp를 지정
 							--------- return형으로 사용
 			=> 일반 자바
 	View : JSP로 제작 => request를 받아서 출력
 			=> 사용자 요청
 			<a> <form> ajax, location.href=""
 
 	Controller : 사용자의 요청을 받는다
 				 ----------
					처리하는 Model찾기
					model에서 담아준 (request/sisseon)을 JSP로 전송
 */
		
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//클래스 저장
	private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			String xml_path="C:\\webDev\\webStudy\\JSPMiddleProject_4\\src\\main\\webapp\\WEB-INF\\application.xml";
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			//파서기 생성 => xml / wml / hdml / vml/...
			DocumentBuilder db=dbf.newDocumentBuilder();
			//xml파서기
			
			Document doc=db.parse(new File(xml_path));
			//System.out.println(doc.toString());
			Element root = doc.getDocumentElement();
			//System.out.println(root.getTagName());
			NodeList list = root.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				
				//클래스 메모리 할당 new ListModel()
				//이름으로 메모리 할당
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(id+":"+cls);
				
				clsMap.put(id, obj);
			}
			//DOM Parsing : 데이터를 메모리에 트리형태로 저장 관리
			//실제 데이터를 출력 관리
			//=> 공공포털 / 네이버 카페 -> xml, json
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Controller Call....");
		//http://localhost/JSPMiddleProject_4/list.do?page=1
		//request uri는 ?전까지만 해당된다
		
		//사용자 요청을 받는다
		String uri=request.getRequestURI();
		//System.out.println(uri);
		uri=uri.substring(request.getContextPath().length()+1);
		//System.out.println(uri);
		Model model=(Model)clsMap.get(uri);
		String jsp=model.handlerRequest(request);
		
		/*
		 	원래 jsp가 null값일 때를 처리해줘야하는데
		 	json, 자바스크립트를 아직 안배워서 못함
		 
		 */
		if(jsp.startsWith("redirect:"))
		{
			jsp=jsp.substring(jsp.indexOf(":")+1);
			response.sendRedirect(jsp);
			// _ok.do => 화면이동 (list, detail)
			//insert, update, delete => 
		}
		else
		{
			//화면출력해야할  경우 출력할 데이터 전송
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response); // forward는 데이터를 잃어버리지 않아
			//
		}
	}

}
