<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//$('span:nth-child(1)').text("Hello JQuery") //1번부터 시작
	$('span:eq(0)').text("Hello JQuery") //0부터 시작
	$('span:eq(1)').html('<font color=red>Hello</font>')
})
</script>
</head>
<body>
 <span>
  
 </span><br>
 <span>
 
 </span>
</body>
</html>