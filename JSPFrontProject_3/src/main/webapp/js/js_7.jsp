<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let emp=[{""}]
const sawonList=()=>{
	console.log(emp)
	
}
const sawonDetail=(empno)=>{
	/* for(let e of emp)
	{
		if(e.empno===empno)
		{
			console.log(e)
			break;
		}
	} */
	let sawon=emp.find(sa=>sa.empno===empno)
	console(sawon)
}
const sawonInsert=()=>{
	emp.push({"ename":"홍길동.."})
}



</script>
</head>
<body>

</body>
</html>