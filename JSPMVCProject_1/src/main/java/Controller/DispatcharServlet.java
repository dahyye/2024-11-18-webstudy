package Controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.Elements;
import javax.xml.parsers.*;
import org.w3c.dom.*; // DOM / SAX 두 종류가 있다

import com.sist.model.Model;
/*
 	DOM => XML을 트리형태로 저장 후 데이터 읽기(CRUD)
 	SAX => 태그를 한개씩 읽어서 데이터 추출
 	문제점
 	AWS에 호스팅 -> 리눅스 (우분트) => 경로명 문제
 
 
 */

@WebServlet("*.do")
public class DispatcharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();
	//XML읽어서 => 클래스 확인
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			URL url=this.getClass().getClassLoader().getResource("."); //.은 현재폴더를 의미
			//파일형태로 바꾸기
			File file=new File(url.toURI());
			//System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", file.separator);
			//윈도우 -> \\ , 리눅스 -> / ==> 자동변환(separator)
			path=path.substring(0,path.lastIndexOf(file.separator));
			//System.out.println(path);
			path=path+File.separator+"application.xml";
			//System.out.println(path);
			//xml 경로 -> 모든 운영체제 => 모든 컴퓨터 호환
			//xml =>  파싱 (xml에 있는 데이터 추출)
			//DocumentBuilder
			
			//1. XML 파서기 생성
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			//2. 파서기
			DocumentBuilder db = dbf.newDocumentBuilder();
			//3. XML을 읽어서 트리형태로 메모리에 저장
			//		 트리형테로 저장되는 공간을 Document라고 한다
			Document doc=db.parse(new File(path));
			System.out.println("doc생성");
			//4. root태그 읽기 (beans태그 밑 bean 안에 들어있다)
			Element beans=doc.getDocumentElement();
			System.out.println(beans.getTagName());
			
			//5. 같은 이름의 태그를 모아서 처리
			//NodeList->같은 이름 태그를 모아주는 역할
			NodeList list = beans.getElementsByTagName("bean");
			//6. bean에 있는 id.class 값을 추출
			// => MVC구조에서는 Controller를 유지보수 -> 공사중
			
			System.out.println(list.toString());
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("id=" +id+", class="+cls);
				
				Class clsName=Class.forName(cls);
				//클래스 정보 읽기 => 메모리할당 / 메소드 호출 / 멤버변수
				//클래스 정보를 읽기 위해서는 반드시 패키지.클래스명
				//스프링 고정 -> 추가 / 수정 / 삭제 => 문서(XML)
				//메모리 할당
				Object obj=clsName.getDeclaredConstructor().newInstance();
				
				clsMap.put(id, obj);
				System.out.println("id="+id+", obj="+obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//사용자 요청을 받는다 : GET/POST => 동시에 처리
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI();
		uri=uri.substring(uri.lastIndexOf("/")+1);
		
		System.out.println(uri);
		
		Model model=(Model)clsMap.get(uri);
		String jsp=model.handlerRequest(request);
		if(jsp.startsWith("redirect:"))
		{
			// 이미 만들어진 JSP이동 => sendRedirect
			jsp=jsp.substring(jsp.indexOf(":")+1);
			// return "redirect:list.do"
			response.sendRedirect(jsp);
		}
		else
		{
			// request에 담긴값을 출력 => forward
			RequestDispatcher rd=
					request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
		
		
	}
}

