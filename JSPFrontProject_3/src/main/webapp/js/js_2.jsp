<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	자바스크립트 사용법
	1. 내부스크립트 : 파일 한 개 제어
		<script type="text/javascript">
		 함수
		</script>
	2. 외부스크립트 : 여러개의 파일을 제어(재사용)
		<script type="text/javascript" src="파일명|경로명|URL">
		</script>
		=import
	3. 인라인스크립트 : 태그 한개를 제어
		<a href="javascript:history.back()">
				 -----------**
				 
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
	Vue/React/Next
	<script type="text/babel">
		소스코딩
	</script>
	ES6버전에서는 자동으로 타입을 인식해서 들어간다
	<script>
		소스코딩
	</script>
--%>
<script type="text/javascript">
//메인
window.onload=function()
{
	//함수호출을 위해 main사용
	//이벤트로 호출할 땐 main필요없음
	let a=10;
	let b=20;
	//;는 자유
	
	let p1=plus1(a,b)
	let p2=plus2(a,b)
	let p3=plus3(a,b)
	let p4=plus4(a,b)
	
	//브라우저 출럭
	document.write("p1="+p1+"<br>")
	document.write("p2="+p2+"<br>")
	document.write("p3="+p3+"<br>")
	document.write("p4="+p4+"<br>")
}
//선언적 함수 선언
 function plus1(a,b)
 {
	return a+b	 
 }
 //익명의 함수
 let plus2=function(a,b)
 {
	 return a+b
 }
 //가장 많이 사용하는 형태
 let plus3=(a,b)=>{
	return a+b	 
 }
 //리턴형도 생략가능
 //가독성이 떨어진다
 let plus4=(a,b)=>a+b
</script>
</head>
<body>

</body>
</html>