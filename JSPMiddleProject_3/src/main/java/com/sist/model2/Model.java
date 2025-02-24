package com.sist.model2;

import jakarta.servlet.http.HttpServletRequest;
//클래스를 모아서 관리할 땐 interface
/*
 	
 
 */
public interface Model {
	public String execute(HttpServletRequest request);
}
