<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	
	1. 데이터형
		자동 데이터형 변경
		-----------------
		let / const
		=> number(숫자형) -> int / double
		=> string(문자형) -> '',""
							--- 여러 글자
		=> object => 배열, 객체형
					 [] 	{} => JSON
		=> function => 매개변수로 사용
					   ------- callback
					   
	2. 연산자
		+ : 문자열 결합 / 덧셈
		/ : 0으로 나누는 경우 : infinity
			정수 / 정수 = 실수
		비교연산자
		 => 5 =='5' => true
		 	   --- parseInt('5')
		 => 5 === '5' => false
		          ---- 변경이 안된다(엄격한 연산자)
		 형변환 연산자
		 ------------
		 정수변환 : Number('10'), parseInt('10)
		 논리변환 : Boolean(1), Boolean(0)
		 			=> 0, 0.0이 아닌 수는 모두 true
		 			=> 문자일 경우 null값이 아닌 경우 모두 true
		 문자변환 : String(10)
		 ***HTML에서 값을 가지고 오는 경우
		 	=> 모든 데이터형이 String이다
	
	3. 제어문
	4. 함수 => 명령문의 집합 -> 한가지일만 수행
		선언적 함수
		**리턴형 선언
		**매개변수의 데이터형을 사용하지 않는다
		=> 국어, 영어, 수학 점수를 받는다
		  function gesan(kor, eng, math)
		  
		  function 함수명(매개변수) => 선언부
		  {
		  	구현부
		  }
		 익명의 함수
		 let 함수명=function(매개변수){}
		 let 함수명=(매개변수)=>{}
		 				    ---- function / return 제거
		 				    	 이 방식을 권장
		 
		 class A
		 {
		 	test:function(){
		 		axios.get('URL',{데이터전송}
		 	}
	
	5. 태그선택(객체모델)
	
	6. 객체(배열/JSON)
	
	7. 이벤트처리
	
	8. 내장 객체(브라우저 객체, 일반 객체)
							  --------- String / Date / Number	
	9. Jquery 이용해서 처리
	   ------ 자바스크립트 기반
		 
		 
		 
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
let login=funciton(){
	
}
window.onload=function(){
	document.write("login="+typeof login) //브라우저 출력
	
}
</script>
</head>
<body>

</body>
</html>