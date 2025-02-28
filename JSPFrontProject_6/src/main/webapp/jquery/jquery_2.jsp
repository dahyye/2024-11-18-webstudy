<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	$('li').css('cursor','pointer') //css => 전체적으로 CSS적용
	$('li').click(function(){
		let data=$(this).text()
		alert("선택한 과목:"+data)
		//=> 데이터 주입
		$(this).text(data+"과정")
	})
})
</script>
</head>
<body>
 <ul class="container">
  <li>aaaaa</li>
  <li>bbbbbbbbbb</li>
  <li>cccc</li>
  <li>dddd</li>
  <li>ffffff</li>
  <li>eeeeee</li>
  <li>qqqqq</li>
  <li>www</li>
 </ul>
</body>
</html>