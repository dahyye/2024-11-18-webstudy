<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	내장 객체
	--------
	window : 브라우저 자체 제어
			open
			close
	document : write
			   querySelector() 
	history : back, go()
	location : href="이동할 파일명"
	
	데이터형
	--------
		Number -> 형변환 / parseInt()
		----- toLocaleString()
		String 
		  length() = 문자 갯수 : 비밀번호 유효성 검사
		  indexOf / lastIndexOf : 문자위치 찾기
		  replace() : 문자변경
		  split() : 문자 분리 -=> 배열ㄹ 저장
		  subString() : 문자 자르기
		  **substr(number,number)
		  	   (시작인덱스번호,갯수)
		 trin() : 좌우 공백 제거 
		Array
		  push() : 데이터첨부
		  pop() : 데이터삭제
		  slice() : 원하는 위치의 데이터를 잘라서 새로운 배열 생성
		  length : 데이터갯수
		Date : 날짜/시간 관리
		  let today=new Date()
		  year => today.getFullYear()
		  month => today.getMonth() => 0부터 나오기 떄문에 오늘날짜의 달을 가지고 오고 싶으면 +1 해야한다
		  date(일) => today.getDate 
		  day(요일) => today.getDay()
		Math
		  round() => 반올림
	
		
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let today=new Date()
	let year=today.getFullYear()
	let month=today.getMonth()+1
	let date=today.getDate()
	let day=today.getDay()
	let strWeek=["일","월","화", "수", "목", "금","토"]
	document.write("오늘은 "+year+"년 "+month+"월 "+date+"일 "+strWeek[day]+"요일입니다")
	
		
	
	
	
}
</script>
</head>
<body>

</body>
</html>