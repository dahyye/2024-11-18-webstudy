package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public interface Model {
	//요청처리하는 메소드
	//실제 스프링에 존재하는 메소드
	public String handlerRequest(HttpServletRequest request);
	
}
