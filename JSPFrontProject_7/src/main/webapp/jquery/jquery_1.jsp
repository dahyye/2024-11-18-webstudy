<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--

	서버에서 데이터 전송을 받는 경우 => Ajax
	검색 : List => []
	VO : {키:값, 키:값} => JSON
	
	객체모델 / 문서 모델 => 태그제어
	자바스크립트 : document.querySelector(CSS)
	Jquery : $(CSS) => selector
	
	
	자바스크립트에서 지우너하는 라이브러리
	Array / String / Number / Date / Math
	
	Array : push 데이터 추가 
			slice 잘라서 새로운 배열
			pop epdlxj tkrwp, 
			
	Math : ceil()
		   round()
		   
		   
	브라우저 객체
	window() => open(), close(), scrollbar()
	location() => href : 화면이동
	history() => back, go(-1)
	document => write(), querySelector()
	---------------------------------------
	Jquery에서 자바스크립트 이용
	
	
	1) selector
		태그명 : $('태그명') => 멀티
		아이디명 : $('#아이디명') => 싱글
		클래스명 : $('.class') => 멀티
		--------------------------- 해당 태그 $(this)
		
		=> CSS
		 후손 / 자손 => $('태그명 > 태그명')		$('x태그명 태그명')
		 						자손						후손
		 속성 => $('태그명[속성=값]')
		 가상선택자 		
		 
		<td>aaa</td> => $('td').teext() => 읽기 => aaa
		<td></td>   => $('td').text("aaa") => 쓰기 
		--------> 자바스크립트 td.textContent=""
		<td><span>aaa</span></td> => $('td').html()
										<span>aaa</span>
		=> html => td.innerHTML=html
		
		=태그의 속성값 읽기 쓰기
			<태그 속성=값> => $('태그').attr(속성명) : 읽기
							 $('태그').attr(속성명,값) : 쓰기
			value => input / select / textarea
				<input type=text> => $('input').val()
									$('input').val(값)
			append() => 추가를 계속할 때 사용
 	
 	3) 읽기 / 쓰기 시점 => 이벤트
 		버튼 / 이미지 => click
 			=> $('태그').click(function(){}) => 고전적 이벤트
 			   $('태그').on('click',function(){}) => 리스너 이용
 		
 		onchange : select
 		onkeydown / onkeyup
 		onmouse / onmouseover
 		------------------------- hover
 		
 	4) 애니메이션
 	
 	

 --%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let httpRequest=null
function getXMLHttpRequest() //브라우저에서 XMLHttpRequest()객체읽기
{
	//브라우저마다 객체명이 다르다
	if(window.XMLHttpRequest)//null이 아니면
	{
		return new XMLHttpRequest()
		//XMLHttpRequest : 서버에 요청 => 결과값을 읽어오는 역할
		//$.ajax({})
	}
	else
	{
		return null;
	}
	
}
//전송 => 수신
function sendRequest(url, params, callback, method)
{
	//callback => 시스템에 의해 자동 호출되는 함수 
	/*
		$.ajax({
			url: ,
			type: ,
			date: {},
			success:function()
			{
				//데이터 받아서 출력
			}
		})
		//Vue / React
		axios.get(url,{params}).then(function()){})
		axios.post(url,{params}).then(function()){})
	
	*/
	//1. 객체 생성
	httpRequest=getXMLHttpRequest()
	//<form action=""> GET <form method="POST">
	
	//2. method방식 지정
	let httpMethod=method?method:'GET'
	if(httpMethod!='GET' && httpMethod!='POST')
	{
		httpMethod='GET'
	}
	//param 처리 =>    ?id=admin
	let httpParams=(params===null||params==="")?null:params
	let httpUrl=url;
	if(httpMethod!='GET' && httpParams!=null) //전송할 값이 있다면
	{
		httpUrl=httpUrl+"?"+httpParams
	}
	//서버연결 => open함수는 이미 제작됨
	httpRequest.open(httpMethod, httpUrl,true)
	//true => 비동기, false => 동기
	//한글처리
	httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded')
	//자동호출되는 함수 지정
	httpRequest.onreadystatechange=callback
	//데이터 전송
	httpRequest.send(httpMethod==='POST'?httpParams:null)
	//데이터 읽기 : responseText => 실행된 결과만 읽어온다 / responseXML(JSON) 

}
function send()
{
	sendRequest('sub.jsp', null, ok,'POST')
}
function ok()
{
	/*
		0 => 서버연결준비
		1 => 서버연결 open
		2 => 서버연결 완료
		3 => 데이터 전송준비 => send()
		4 => 데이터 전송 완료
		
	
	*/
	if(httpRequest.readyState==4)
	{
		if(httpRequest.status===200)
		{
			let div=document.querySelector("#print")
			div.innerHTML=httpRequest.responseText
		}
	}
}


</script>
</head>
<body>
 <button onclick="send()">전송</button>
 <div id="print"></div>
</body>
</html> 