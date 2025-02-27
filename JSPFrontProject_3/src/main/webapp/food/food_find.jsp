<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
 margin-top: 50px;
}
.row{
  margin: opx auto;
  width: 100%;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

img:hover, a:hover{
	cursor: pointer;
}

</style>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="food.js"></script>
<!-- <script type="text/javascript">
let food_list=[]
let startPage=0
let endPage=0
let curpage=0
let totalpage=0
//시작과 동시에 값을 읽어 온다
window.onload=function(){
	let html='';
	axios.get('http://localhost/JSPFrontProject_3/food/find_js.do')
	//읽어오는 함수 then
	.then((response)=>{
			console.log(response.data)
			food_list=response.data
			curpage=response.data[0].curpage
			totalpage=response.data[0].totalpage
			startPage=response.data[0].startPage
			endPage=response.data[0].endPage
			
			console.log("curpage="+curpage)
			console.log("totalpage="+totalpage)
			console.log("startPage="+startPage)
			console.log("endPage="+endPage)
			
			food_list.map(function(vo){
				html+='<div class="col-sm-4">'
					+'<div class="thumbnail">'
					+'<img src="'+vo.poster+'" style="width:100%" onclick="detail('+vo.fno+')">'
					+'<p>'+vo.name+'</p>'
					+'</div>'
					+'</div>'
				
			})
			//id="poster"로 되어 있어서 #poster로 불러
			let main=document.querySelector("#poster")
			//css selector
			main.innerHTML=html
			
	})
}
/*

  ==> 문서객체 선택	351page
  
 document.querySelector("#fd") id
	=> document.getElementByID("fd")
	=> $('#fd')
 document.querySelector(".fd") class
	 => document.getElementByClassName("fd")
	 => $('.fd')
 document.querySelector("div") 태그명
 	=> document.getElementByTagName("div")
 	=> $('div')
 
 
 */
function foodFind(){
	let addr=document.querySelector("#fd").value; // fd의 값을 가져와라
	if(addr.trim()=="")
	{
		alert("지역을 입력하세요")
		document.querySelector("#fd").focus()
		return
	}
	
	let html='';
	// .do?page=1&address=서대문
				//request.getParameter("page")
				//request.getParameter("fd")
	/*
		ajax({
			url:'',
			type:'POST',
			data:{page:1, fd:addr},
			success:function(response)
			{
				
			}
		})
		=> ajax (jquery)
	
	*/
	//axios-> vue, react
	axios.get('http://localhost/JSPFrontProject_3/food/find_js.do',{
		params:{
			page:1,
			fd:addr,
		}
	})
	//읽어오는 함수 then
	.then((response)=>{
			console.log(response.data)
			food_list=response.data
			curpage=response.data[0].curpage
			totalpage=response.data[0].totalpage
			startPage=response.data[0].startPage
			endPage=response.data[0].endPage
			
			console.log("curpage="+curpage)
			console.log("totalpage="+totalpage)
			console.log("startPage="+startPage)
			console.log("endPage="+endPage)
			
			food_list.map(function(vo){
				html+='<div class="col-sm-4">'
					+'<div class="thumbnail">'
					+'<img src="'+vo.poster+'" style="width:100%">'
					+'<p>'+vo.name+'</p>'
					+'</div>'
					+'</div>'
				
			})
			//id="poster"로 되어 있어서 #poster로 불러
			let main=document.querySelector("#poster")
			//css selector
			main.innerHTML=html
			
	})
	

}
//익명이 함수 형식
let detail=(fno)=> {
	axios.get('http://localhost/JSPFrontProject_3/food/detail_js.do',{
		params:{
			fno:fno
		}
	})
	.then((res)=>{
		console.log(res.data)
	})
}

</script> -->
</head> 
<body>
 <div class="container-fluid">
  <div class="row">
   <input type=text id="fd" size=20 class="input-sm">
   <input type="button" id="btn" value="검색" class="btn-sm btn-success" onclick="foodFind()">
  </div>
  <div class="row" style="margin-top: 20px">
   <div class="col-sm-6" id="poster">
    
   </div>
   <div class="col-sm-6" id="detail" style="display: none;">
    <table class="table">
     <tr>
      <td width=30% class="text-center" rowspan="8" id="poster1">
      </td>
      <td colspan="2">
       <h3><span id="title"></span>&nbsp<span id="score" style="color:orange"></span></h3>
      </td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">음식종류</td>
       <td width="55%" id="type"></td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">주소</td>
       <td width="55%" id="address"></td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">전화</td>
       <td width="55%" id="phone"></td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">가격대</td>
       <td width="55%" id="price"></td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">주차</td>
       <td width="55%" id="parking"></td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">영업시간</td>
       <td width="55%" id="time"></td>
     </tr>
     <tr>
      <td width="15%" style="color: gray">테마</td>
       <td width="55%" id="theme"></td>
     </tr>
    </table>
    <table class="table">
     <tr>
      <td id="content"></td>
     </tr>
    </table>
   </div>
  </div>
  <div class="row" style="margin-top: 10px">
   <div class="text-center" id="pages">
    <!-- 값을 자바스크립트로 채우기 -->
   </div>
  </div>
 </div>
</body>
</html>