<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 	이벤트
 		=> 함수 호출
 		-------------------
 		let btn=document.querySelector('#id')
 		btn.onclick=function(){}
 		==> 고전이벤트처리
 		=> 이벤트 리스너
 		let btn=document.querySelector("id")
 		btn.addEventListener('click', function(){처리})
 		--- $('#id명').on('click',function(){})
 		------------------ 		
 
 */
 $(function(){
 	$('#count').change(function(){
 		//change-> 선택이 됐다면
 		let count=$(this).val()
 		//alert(count)
 		let price=$('#price').text()
 		/*
 		let price=$('#price').text()
 		<a href=""> <img src=""> => attr()
 		val("admin")     val()
 	     -------------  ------
 		값을 넣어준다	값을 가져온다
 	     
 	     */
 	     price=price.replace(',','');
 		 price=price.replace('원','');
 		 let total=count*price
 		 //alert(total)
 		 $('#total').text(total.toLocaleString()+"원")
 		/* 
 	    	글자조작
 	    	---------
 	    	val() / text() / html() / attr() / append()
 	    	 
 	    	val() : value값으로 채우는 태그
 	    			<input> <select> <textarea>
 	    	text() : 모든태그 <태그>값<태그> => 값을 가져온다
 	    			
 					<span>
 	    				<h1>Hello</h1>
 	    			</span>
 	    			 => $('span h1')
 	    			<p>
 	    				<h1>Hello2</h1>
 	    			</p>
 	    			 => $('p h1')
 	    				
 	    				
 	    				
 	    				
 	    				
 		*/
 	})
 })
</script>
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
</head>
<body>
 <div class="container">
  <div class="row">
   <span id="price">30,000원</span>
   <select class="input-sm" id="count">
    <option value="1">1개</option>
    <option value="2">2개</option>
    <option value="3">3개</option>
    <option value="4">4개</option>
    <option value="5">5개</option>
   </select>
   <br>
   총 금액: <span id="total"></span>
  </div>
 </div>
 </body>
</html>