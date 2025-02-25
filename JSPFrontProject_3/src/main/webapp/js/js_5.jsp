<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	331page 객체
	=> 여러개의 데이터를 묶어서 관리
	=> 배열 / 객체
	    []     {}=> JSON(JavaScript Object Nontation)
	    					자바스크립트 객체 표현법
		
		[] => 배열(array) : 혼합이 가능하다 -> let은 자동으로 형변환이 되기때문에 사용하기 편하다
							자바에서도 Object[] obj={}  Object형식으로 만들 때 사용가능
	
				let sawon=[1, "홍길동", ...]
		=> 지원하는 함수
			1. 추가 : sawon[0]=2
					  push()
			2. 삭제 : pop()
			3. 갯수 : length
			4. 자르기 : slice() -> arraycopy
			5. 찾기 : find
			6. 특정요소 삭제 : delete 
			
			
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload=function(){
	let names=["홍길동", "심청이", "박문수"]
	console.log(names)
	console.log("인원:"+names.length)
	//데이터 추가
	names.push("이순신")
	console.log(names)
	console.log("인원:"+names.length)
	//데이터 삭제
	names.pop()
	//마지막데이터가 삭제
	console.log(names)
	console.log("인원:"+names.length)
	
	//특정값삭제
	/* delete names[1]
	console.log(names)
	console.log("인원:"+names.length) */
	
	//첫번째값 제거 
	names.shift()
	console.log(names)
	console.log("인원:"+names.length)
	
	//첫번째값 추가
	names.unshift("홍길동")
	console.log(names)
	console.log("인원:"+names.length)
	
	console.log(names.slice(2)) //names[2]출력
	console.log(names.slice(1,2))   //names[1]출력
	
	let names2=[...names,"춘향이"];
	console.log(names2)
	// ...은 복사
	
}
</script>
</head>
<body>

</body>
</html>