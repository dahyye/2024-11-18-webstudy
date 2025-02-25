<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	call-back함수
		: 매개변수로 전송되는 함수
		  ----------------------
		  	Redux 
		  	-----
		  	
		  	JSP / MVC (Model2) 
		  	(Model1)
	
	FRONT-	React -> MVC(Redux)
			Vue   -> MVC(vuex)
 	
 	//매개변수 안에 함수가 들어가는 형태가 전부 call-back함수
 	=> map(function(){})
 	=> forEach(function(){})
 	---------------------------------
 	=> then(function(response){})
 	=> success:function(response){}
 	---------------------------------- 사용자 요청 / 서버 응답
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function func(callback)
{
	//반복문 / ajax / axios
	//시스템에 의해 자동 호출되는 함수
	/*
		_jspInit()
		_jspDestory()
		_jspService()
		
		main()
		doGet doPost
		
		getConnection disConnection
		
	*/
	for(let i=1; i<=5; i++)
	{
		callback() //setTimer(함수명, 시간), setInterval(함수명, 시간)
	}			   // 시간이 되면 함수호출		시간마다 함수호출
}
let callback=function(){
	
	document.write("함수호출... <br>")
}
window.onload=function()
{
	func(callback)
}

</script>
</head>
<body>

</body>
</html>