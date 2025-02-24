<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//이항연산자
/*
 	1. 산술연산자
 		+, -, *, /, %
 		+ : 덧셈, 문자열 결함 => NaN(연산이 안된 경우)
		let a=10+10 => 20
		let a="10"+"10" => 1010
		
		/ : 0으로 나눌 수 없다
			정수/정수 = 실수
		% : 왼쪽 부호를 따라간다
			+ % - => +
			- % + => -
			+ % + => +
			- % - => -
			
			
 	2. 비교연산자
 	
 	3. 논리연산자
 	
 	4. 대입연산자
 
 */
 window.onload=function(){
	//$(function(){}), $(document).ready(function(){}) => Vuejs
	//mounted(), componentDidMount(), useEffecdt() = react
	//시작점
	let a=5;
	let b=2;
	console.log("a+b="+(a+b))
	console.log("a-b="+(a-b))
	console.log("a*b="+(a*b))
	console.log("a/b="+(a/b))
	console.log("a%b="+(a%b))
	//장바구니 계산 -> select => 숫자(x) 문자열 => *하면NaN
	let c="A"
	let f=20000
	console.log(c+f)
	console.log(c*f) //연산불가
	
	
	
}
</script>
</head>
<body>

</body>
</html>