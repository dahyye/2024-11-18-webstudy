<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
	1. HTML태그 읽기
		document:문서저장 공간
		document.getElementById("ID")
		document.getElementByTagName("태그명")
		document.getElementByClassName("class명")
		document.querySelector("CSS")
							  ---------
							  ID => #아이디명
							  Class => .클래스명
							  태그명 => 태그명
							  선택자 => 이벤트처리가 가능
							  
	2. let 변수명=[]
		=> List list =new ArrayList()
	    
	   let 변수명={} : 객체
	   			 ----JSON
	   	=> MovieVO
	   	
	   	사용법(***핵심***)
	   	let sawon={sabun:1, name:"홍길동", sex:"남자", dept:"개발부"}
	   	
	   	class Sawon
	   	{
	   		int sabun;
	   		String name, sex, dept;
	   	} 
	   	
	   	Sawon sa= new Sawon()
	   	sa.subun=1;
	   	sa.name="홍길동";
	    sa.sex="남자";
	    sa.dept="개발부";
 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
 margin-top: 50px;
}
.row{
  margin: opx auto;
  width: 900px;
}
</style>
<script type="text/javascript">
//
//let a={} => a는 객체
const sawon=[
	{sabun:1, name:"홍길동1",dept:"개발부", job:"대리", pay:3500},
	{sabun:2, name:"홍길동2",dept:"개발부", job:"대리", pay:3500},
	{sabun:3, name:"홍길동3",dept:"개발부", job:"대리", pay:3500},
	{sabun:4, name:"홍길동4",dept:"개발부", job:"대리", pay:3500},
	{sabun:5, name:"홍길동5",dept:"개발부", job:"대리", pay:3500}
	
]
window.onload=function(){
	let html="";
	sawon.map(function(sawon){
		html+='<li>'+(sawon.sabun+" "+sawon.name+" "+sawon.dept)+'</li>'
		
	})
	document.querySelector('ul').innerHTML=html;
	//어떤 태그에 첨부 => 객체 모델
}

</script>
</head>
<body>
 <ul>
  
 </ul>
</body>
</html>