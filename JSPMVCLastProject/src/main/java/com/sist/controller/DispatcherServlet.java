package com.sist.controller;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.ann.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> clsList = new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//클래스만 가지고 오기 => xml
		try {
			URL url=this.getClass().getClassLoader().getResource(".");
			//WEB-INF/classes
			//파일로 변경
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", File.separator);
			
			path=path.substring(0,path.lastIndexOf(File.separator));
			path=path+File.separator+"application.xml";
			
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path));
			// 최상위 태그 => 데이터베이스 (테이블 역할)
			Element beans=doc.getDocumentElement();
			// 같은 이름의 태그를 묶어서 관리 
			NodeList list=beans.getElementsByTagName("context:component-scan");
			String pack="";
			for(int i=0;i<list.getLength();i++)
			{
				Element elem=(Element)list.item(i);
				pack=elem.getAttribute("basePackage");
			}
			System.out.println(pack);
			com.sist.ann.FileReader.componentSacn(file.getPath(), pack);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uri=request.getRequestURI();
		uri=uri.substring(request.getContextPath().length()+1);
		System.out.println(uri);
		try {
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				Object obj=clsName.getDeclaredConstructor().newInstance();
				//해당 메소드를 찾기 시작
				Method[] methods=clsName.getDeclaredMethods();
				
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(uri))
					{
						String jsp=(String)m.invoke(obj, request);
						
						if(jsp==null)
						{
							return; //ajax
						}
						else if(jsp.startsWith("redirect:"))
						{
							//sendRedirect -> _ok
							jsp=jsp.substring(jsp.indexOf(":")+1);
							response.sendRedirect(jsp);
							
						}
						else
						{
							//forward => request를 JSP로 전송
							RequestDispatcher rd= request.getRequestDispatcher(jsp);
							rd.forward(request, response);
							
						}
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
