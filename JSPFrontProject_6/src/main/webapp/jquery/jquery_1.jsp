<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	Jquery / Vue(Vuex) / React (React-Query,Redux)
		=> 문서 객체(태그를 제어) => 태그를 선택 후에 처리
		=> 태그에 대한 이벤트 처리
		=> DOMScript
		=> 단점 : 데이터가 없다(데이터=>서버)
									  ----오라클
		=> 출력(MVC => View)
		=> 출력속도가 빠르다
	1. CSS 선택자
		$('선택자'), Vue => <button ref="">
					React => e.target
			선택자
			------
			1) 태그 => $('태그명').함수()
								  ----- 값 설정
								  			val("")
								  			text("")
								  			html("")
								  			attr("") : 속성값 읽을 때
								  			append("") : 누적해서 여러개 올리고 싶을 때
								  			
								  		값 읽기
								  			val()  / text() / html() / attr()
								  		이벤트처리
								  		----------> 모든 태그에 이벤트 가능
								  			$(선택자).click(function(){})
								  			$(선택자).on('click',function(){})
								  			 => mouseup / mousedown
								  			 => keyup / keydown
								  			 => hover
								  			 => change
								  			 
			2) ID(속성값) => $('#아이디명')
			3) CLASS(속성값) => $('.클래스명')
			4) 자손일 때 -> $('태그명(id,class) > 태그명(id,class)')
			   후손일 때 -> $('태그명(id,class) 태그명(id,class)')
			   => 목록출력할 때 많이 사용
		   			<table>, <ul>, <dl> => id,class를 부여하지 않는다
			   
				<div>
				 <h1></h1>  --> 자손
				 <div>
				  <h1></h1>  --> 후손
				 </div>
				</div>
			5) 속성선택자 -> $('태그명[속성=값]') => a, img, input
								=equals
							$('태그명[속성$=값]')
								=endsWith
							$('태그명[속성^=값]')
								=startsWith
							$('태그명[속성*=값]')
								=contains
			
			6) 가상선택자 -> 
				ntn-child => 1 / eq => 0
				$('태그명:ntn-child(odd)')
				$('태그명:ntn-child(even)')
				$('태그명:ntn-child(2n_1)')
				$('태그명:eq(0)')
								
								
			
	2. 자바스크립트 기본
		
		데이터형
			number : 숫자(정수, 실수)
			string  : 문자, 문자열
			boolean : true / false
					  숫자 : 0, 0.0외에는 true
					  문자 : null 외에는 true
			object
				=> 배열 [] => list와 호환
				=> 객체 {} => VO와 호환
			undefined : 데이터 값이 없는 경우
			function : 데이터형으로 취급
				=> 매개변수로 사용이 가능(callback)
				=> 이벤트 / react-query / redux
				map(function(){})
				forEach(function(){})
				
				map(function(){})
				
				function a(){}
				map(a)
				=> 이런 방식으로 사용할 수도 있다
				
		연산자
			산술연산자 : / (정수/정수=실수)
			비교연산자 : ==	
						10=='10 => true
						=== (엄격하다)
						10=='10 => false
			형변환 : Number, parseInt => 문자열을 정수형 변경
					=> 데이터 읽기 : 문자열로 읽어 온다(HTML)
					문자열 변환 => String(10) => "10"
					논리형 변경	=> Boolean(1) => true
								   Boolean(0) => false
								   Boolean("aa") => true
								   Boolean(null) => false
					
				
		제어문
			for / for-each
			=> 출력
			일반 for
			for(let i=0;i<=10;i++)
			{
				처리문장
			}
			
			
			for(let i in 배열) -> i는 인덱스번호
				[1,2,3,4] => i=0,1,2,3
			for(let key in 객체) -> key는 key값
				{name:"", sex:""} key=name,sex
			
			for(let vo of 배열)
					-- 배열의 실제값
					
			**배열.map(function(배열값){})
			배열.forEach(function(배열값){})
						
			
		함수 : 필수(요청처리)
			function func_name(매개변수){}
				=> return형을 사용하지 않는다, 매개변수는 변수명만 사용
			=> 리턴형이 없는 경우
			function func()
			{	
				...
				...
			}
			function func()
			{
				...
				return
			}
			
			익명의 함수
			-----------------------------
			let 함수명=function(){}
			let 함수명=()=>{}
			----------------------------- 함수안에 함수추가
			----------------------------- vue / React => 이벤트 처리
			
			1. 자바스크립트의 main
				window.onload=function(){}
				=> window.onload=()=>{}
			
			2. jquery
				${function(){}}
				$(document).readry(function(){})
				
			3. vue
				mounted:function(){}
				
			4. react 
				componentDidMount()
				=> useEffect()
			
		내장함수 : String / Array / Number / Date / Math 
			:string 
				substring() / substr() ->  문자열자르기
				indexOf
				Numbe
				Date
				Math
				
		
		브라우저 : window / document / location / history
			window : open / close / scrollbar 조절
			document : 문서관리(태그관리)
						write / querySelector
			location : 이동정보 
						href
			history : 기록정보
						go / forward / back
			
								(Asynchronous JavaScript and XML)
								빠르게 동작하는 동적인 웹 페이지를 만들기 위한 개발 기법
	3. 서버연결 => 데이터 읽기 => Ajax -> 서버연결해서 데이터를 가지고와서 사용
										화면이 바뀌지 않은 상태에서 요청처리를 해줌
	-------------------------------
		axios는 데이터만 받을 수 있는데 Ajax는 파일을 통째로 받을 수 있다
	
		Ajax를 많이 사용하는 곳 : 로그인 / 회원가입(아이디찾기/비밀번호찾기/아이디중복체크)
								 검색
								 추천
								 예약
								 채팅
								 => 입력된 데이터를 유지해야하는 곳에서 사용
								  jsp -> 서버 -> jsp초기화(jsp새로생성)
								  login.jsp -> 서버 -> login.jsp
								  	 |					  |
								  	 -----------------------
								  	 			|
								  	 		메모리 주소가 다르다
		
		Jquery(클라이언용) -> 서버를 연결하는 부분이 아님 -> Ajax, axiois가 서버연결
			=> 값 읽기 / 값 쓰기
			=> 이벤트 => 언제 데이터 읽기 / 언제 데이터 쓰기 => 시기를 정하는게 중요						  	 		
								 
			<input> <select> <textarea>
			checkbox
			목록제어
			스크롤제어
			서버연결
			
	
	
 --%>
<!DOCTYPE html>
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
  width: 350px;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//이벤트 리스너 이용
	//고전이벤트
	//$('#login').click(function(){})
	/*
		change : 선택이 변경 => <select>
		mousedown / mouseup
		hover
		keyup / keydown
		
		=> $().함수()
			  ------
			  1. 디자인 변경 : css()
			  2. 속성 변경 : attr()
			  3. 값을 읽기 : val(), text(), html()
			  4. 값 저장 : val(""), text(""), html("")
		
		
	*/
	$('#login').on('click',function(){
		//1. id읽기
		let id=$('#id').val()
		if(id.trim()==="")
		{
			$('#print').html('<font color="red">아이디를 입력하세요</font>')
			$('#id').focus()
			return
		}
		else
		{
			$('#print').text("")	
		}
		let pwd=$('#pwd').val()
		if(pwd.trim()==="")
		{
			$('#print').html('<font color="red">비밀번호를 입력하세요</font>')
			$('#pwd').focus()
			return
		}
		else
		{
			$('#print').text("")	
		}
		alert("프로그램종료")
	})
	
	$('#find').click(function(){
		let fd=$('#keyword').val()
		if(fd.trim()==="")
		{
			$('#keyword').focus()
			return
		}
		alert("검색어는 "+fd)
		$('keyword').var("")
	})
	
	$('#keyword').keydown(function(){
		if(e.keyCode===13)
		{	
			let fd=$('keyword').val()
			if(fd.trim()==="")
			{
				$('#keyword').focus()
				return
			}
		}	
		
		
	})
})
</script>
</head>
<body>
 <div class="container">
  <div class="row">
   <h3 class="text-center">Login(val()/text())</h3>
   <table class="table">
    <tr>
     <th class="text-center" width=20%>ID</th>
     <td width=80%>
      <input type=text id="id" size=15 class="input-sm">
     </td>
    </tr>
     <tr>
     <th class="text-center" width=20%>PWD</th>
     <td width=80%>
      <input type=text id="pwd" size=15 class="input-sm">
     </td>
    </tr>
    <tr>
     <td colspan="2" class="text-center">
      <input type=button class="btn-sm btn-success" id="login" value="로그인">
     </td>
    </tr>
    <tr>
     <td colspan="2">
      <span id="print"></span>
     </td>
    </tr>
   </table>
   <h3 class="text-center">key이벤트</h3>
   <input type=text id="keyword" size=20>
   <input type="button" id="find" value="검색" class="btn btn-sm btn-warning">
  </div>
 </div>

</body>
</html>