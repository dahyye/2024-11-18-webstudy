package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

//인터페이스는 클래스들 통합시 사용함
//모델은 사용자 요청에 따라 처리하고 처리결과를 전송해줘야해 (어떤 jsp로 전송할 지 설정필요)
/*
 	사용자 요청(주문)
 	Controller : 요청을 받아서 Model 연결 -> 결과값 전송(서빙)
 	Model : 요청처리(주방)
 			application.xml -> (메뉴판을 알고 있어야 한다)
 	View : (식탁)

 		
 	실행 순서
 	
 						request(주문)
 		JSP(사용자) ====================> Controller(서빙)
 										   |
 										 model(주문처리)
 										 ----- 
 										 -> 해당 모델을 찾는다
 										 		|
 										 	  요청처리
 										 	    | request
 										 	    | request.setAttribute()
 										 	 Controller로 전송
 										 	    |
 										 	   JSP => JSTL/EL 화면 출력
 		
 		=> 실무에서 사용 : JSP(View), Model
 									|
 								   역할 -> 데이터베이스 연동
 								   		  ------------Mybatis
 
 		관련된 클래스(모델) => 통합 (인터페이스)
 		=> 클래스에서 메소드 여러개 사용 : 어노테이션
 		 							------ 구분자(인덱스)
 		=> 환경 설정 파일 : XML / Properties
 
 */
public interface Model {
	public String handlerRequest(HttpServletRequest request);
	
}
