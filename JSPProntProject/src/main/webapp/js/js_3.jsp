<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//연산자 처리
/*
 
 	연산자
 	1. 단항연산자
 		= 증감연산자 (++, --)
 		= 형변환연산자
 			"10" => NUMBER("10") => 10
 				 => parseInt("10") => 10
 			 10  => String(10) => "10"
 			 Boolean => Boolean(1), Boolean(0) -> 0이나 0.0이면 false
 			 			----------
 			 				0이 아닌 숫자가 들어가면 true
 	2. 이항연산자
 		= 산술연산자(+, - , * , /, %)
 			=> + 는 문자열 결합이 가능
 		= 비교연산자(==,!=,<,<=,>,>=)
 		= 논리연산자(&&, || !)
 		= 대입연산자(=, +=, -=)
 	3. 삼항연산자
 		(조건)? 값1:값2
 		조건이 true면 값1
 			  false면 값2
 		------------------ 문법 : base
 		
 */
 //증감연산자
 window.onload=function(){
 	let a=10;
 	console.log("a="+a)
 	a++;
 	console.log("a="+a)
 	++a;
 	console.log("a="+a)
 	
 	let b=10;
 	console.log("b="+b)
 	b--
 	console.log("b="+b)
 	--b
 	console.log("b="+b)
 	
 	
 	let c=10;
 	console.log("c="+c)
 	let d=c++; //대입먼저하고 나중에 증가
 	console.log("c="+c)
 	console.log("d="+d)
 	
 	let k=true
 	console.log("k="+k)
 	k=!k
 	console.log("k="+k)
 	
 	let m=1
 	console.log("m="+m)
 	m=!m
 	console.log("m="+m) //boolean변경
 	//0, 0.0을 제외한 모든 숫자는 true
 	//!를 사용하면 => 데이터형은 boolean
 	
 	//형변환 연산자
 	let p=1;
 	document.write("변경전 p="+p+","+typeof p+"<br>")
 	p=!p;
 	document.write("변경후 p="+p+","+typeof p+"<br>")
 	//number은 필요시에 boolean변경이 가능, 문자열
 	//데이터형을 사용하지 않는다 : let/const
 	
 	let n="10" //중요
 	//HTML에서 값을 읽으면 다 문자열
 	
 	document.write("변경전 n="+n+","+typeof n+"<br>")
 	document.write("변경후 n="+n+","+typeof Number(n)+"<br>")
 	document.write("변경후 n="+n+","+typeof parseInt(n)+"<br>")
 	document.write("변경후 n="+n+","+typeof Boolean(n)+"<br>")
 	
 	let h=10;
 	document.write("변경전 h="+h+","+typeof h+"<br>")
 	document.write("변경후 h="+h+","+typeof String(h)+"<br>")
 	document.write("변경후 h="+h+","+typeof Boolean(h)+"<br>")
 
 	/*
 		형변환연산자 => 브라우저에서 값을 읽어오는 경우 => 문자열로 읽어 온다
 						=> 필요시에 숫자형으로 변경해서 사용할 때
 			
 		1. 정수형 => Number(), parseInt()
 		2. 문자형 => String()
 		3. 논리형 => Boolean()
 			------------------ 숫자 : 0이나 0.0이면 false 아니면 true
 							   문자열 : null이면 false 아니면 true 
 		
 	
 	*/
 	let name="홍길동"
 	document.write("name="+name+", 타입 = "+typeof name+"<br>")
 	document.write("type="+Boolean(name)+", 타입 = "+typeof Boolean(name)+"<br>")
 	
 	
 	
 }
</script>
</head>
<body>

</body>
</html>