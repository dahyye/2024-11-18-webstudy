<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JQuery : 자바스크립트 라이브러리 => 소스를 통일화
	--------
	
	=> 태그, 속성을 제어하는 프로그램 : 문서 객체
	=> 태그를 제어하기 위해 태그를 가지고 온다
	   document.querySelector("")
							 ---- CSS선택자
					|
				$(CSS선택자).제어
	=> Jquery 사용시 => 라이브러리 import 시키고 사용
		<script src="http://code.jquery.com/jquery.js">
		***버전이 충돌되는 오류 발생 => 충돌하면 화면이 안나온다(하얀화면)
			=> 메인에 모아두고 사용
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="">
</script>
<script type="text/javascript">
$(function(){
	
	let h1=document.quereySelector("#h1")
	h1.style.color="red"
	/* $('#h1').css("color","red") */
	
	
	let h2=document.quereySelector(".h2")
	h1.style.color="blue"
	/* $('.h1').css("color","red") */
		
	let h2=document.quereySelector("h2")
	h1.style.color="green"
	/* $('h1').css("color","red") */
	
	
}}
</script>
</head>
<body>
 <h1 id="h1">hello Jquery1</h1>
 <h1 class="h2">hello Jquery2</h1>
 <h1>hello Jquery3</h1>
</body>
</html>