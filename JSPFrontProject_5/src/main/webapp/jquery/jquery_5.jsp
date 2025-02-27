<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ //$(document).ready(function(){}) => 예전버전
	/*
		$(  )
		 ---- selector(태그선택자)
		 ---- 브라우저 객체
		 		window, document, location
		 		------ 위치
		$(this)
		  ---- 자신의 객체
		 
		
		 		
	*/
	/* 
	$('img').css('width', '200px')
	$('img').css('height', '250px')
	 */
	$('img').css({
		'width':'200px',
		'height':'250px'
	})
	$('img').hover(function(){
		//if문
		$(this).css('cursor','pointer')
		$(this).css('opacity',0.2) // 0.0~1.0
		
	}, function(){
		//else문
		$(this).css('opacity',1.0)
	})
	
})
</script>
</head>
<body>
  <img src="m1.jpg" >
  <img src="m2.jpg">
</body>
</html>