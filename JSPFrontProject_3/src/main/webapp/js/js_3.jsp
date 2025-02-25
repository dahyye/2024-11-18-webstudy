<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function btnClick(msg)
{
	alert(msg)//데이터형은 대입하는 데이터에 따라 달라진다 
}
</script>
</head>
<body>
 <input type="button" value="실행" onclick="btnClick('Hello JavaScript')">
</body>
</html>