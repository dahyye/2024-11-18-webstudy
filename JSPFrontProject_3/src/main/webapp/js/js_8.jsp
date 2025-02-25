<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let sawon={
		name:"홍길동",
		getName:function(){
			document.write("이름 : "+this.name)
		},
		setName:function(name){
			this.name=name
		}
}
window.onload=function(){
	
	sawon.setName("심청이")
	sawon.getName()
}
</script>
</head>
<body>

</body>
</html>