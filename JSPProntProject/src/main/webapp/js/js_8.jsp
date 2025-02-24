<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	논리연산자
	&& ,  ||
	
	&& : 직렬연산자 => 두개의 조건이 true일 때 
	|| : 병렬연산자 => 두개의 조건 중 하나가 true일 떄
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	/* let i=(6<7) && (6===7)
	console.log("i="+i)
	let i=(6<7) || (6===7)
	console.log("i="+i) */
	
	let k=10
	k+=10;
	console.log("k="+k)
	k-=10
	console.log("k="+k)
	
	let n=10/0
	console.log("n="+n)
}
</script>
</head>
<body>

</body>
</html>