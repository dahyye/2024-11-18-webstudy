<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	
	반복문
	 while / do~while / for
	 
	 //반복횟수가 없는 경우 -> 자바스크립트에서는 사용빈도가 거의 없다
	 초기값 ------- 1
	 while(조건문) --2 -> false면 종료
	 {
	 	반복수행문장 --3
	 	증가식 --4
	 }
	 
	 do~while : 반드시 한 번이상 반복 수행 => 사용 빈도는 거의 없다
	 초기값		-----1
	 do	
	 {
	 	반복수행문장 --2
	 	증가식     ----3
	 }while(조건식) ---4
	
	반복제어문
	 break / continue
	
	for
	웹 프로그램 -> 한 눈에 모든 데이터가 보이게 만든다
				  ------ 이미지 : 12~15
				  		 게시물(테이블) : 20
				  		 페이지나누기
				  		 => 출력횟수는 지정되어 있다 
				  		 
	1. 일반 for
		for(초기값;조건식;증가식)
		{
			반복 수행문장 -> 자바와 동일
		}
		for(let i=0;i<10;i++)
			--- let/var -> const는 사용할 수 없다
							(상수는 변경이 불가능하기 때문에)
							-> const는 자바에서 데이터를 받을 때 사용할 수 있다
	2. for-each : 배열 / JSON
		= for in
			for(변수 in 배열)
				-----------
				배열의 인덱스번호 읽기(0부터)
				-> 배열 여러개를 동시에 출력할 때
				
		= for of
		  for(변수 of 배열)
		      --- 배열의 값을 1개씩 읽어올 때
		      
		= forEach
		  배열.forEach(function(변수){})
		  						--- 배열의 값을 1개씩 읽어온다
		= map
		 배열.map(function(변수){})
		 				   --- 배열의 값을 1개씩 읽어온다
		 
		 배열.map((변수)=>{})
		 			  ---- function을 제거(화살표함수 => 람다식)
		 			  
	
	3. 반복제어문
	
		break / continue 
		
		 			  
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* window.onload=function()
{
		
} */

//많이 사용된다
window.onload=()=>{
	const names=["홍길동", "심청이", "박문수", "이순신", "춘향이"];
	const sexs=["남자", "여자", "남자", "남자", "여자"];
	
	document.write("<h3>일반for문</h3>")
	
	for(let i=0;i<names.length;i++)
	{
		document.write(names[i]+"<br>");
		
	}
	document.write("<hr>")
	document.write("<h3>일반for-in문</h3>")
	for(let index in names)
	{
		document.write(names[index]+"("+sexs[index]+")"+"<br>")
	}
	document.write("<hr>")
	document.write("<h3>for-of(for-each)</h3>")
	//배열에서 데이터를 한 개씩 읽어오는 
	
	
}
</script>
</head>
<body>

</body>
</html>











