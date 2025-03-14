<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-js"></script>
<script type="text/javascript">.
/* ajax 사용하면 화면이 안없어져? 
	예를 들어 로그인을 할 때 비밀번호가 틀렸다
		=> 그 위에 오류창이 뜨기때문에(한 창 안에서 동작) 제어하기가 쉬워
		=> ajax를 사용하지 않으면 로그인창이 사라지고 오류창이 뜬다. 그러면 다시 로그인창을 띄우고 정보를 다시 입력해야하는
			번거로움이 있다
			
		
*/

		
$((function){
$('#updateBtn').click(function(){
		let no=$('#no').val()
		//데이터전송(수정요청_)
		$.ajax({
			type:'post'
			url:"../qna/qna_update_ok.do"
			data:${'#frm'}.serialize(), //한 번에 데이터 넘기기
			success:function(result)
			{
				location.href="../qna/qna_detail.do?no="+no //이동
			},
			error:function(err)
			{
				alert(err)
			}
			
		})
		
	})
})

</script>
</head>
<body>

</body>
</html>