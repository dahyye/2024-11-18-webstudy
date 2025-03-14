<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>레시피 상세보기</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">chef-list Page</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row" style="width:900px; margin:0px auto ">
			  <table class="table">
			  
			   <tr>
			    <td colspan="3" class="text-center">
			     <img src=${vo.poster } style="width:100%">
			    </td>
			   </tr>
			   
			   <tr>
			    <td colspan="3" class="text-center">
			     <h3>${vo.title }</h3>
			    </td>
			   </tr>
			   
			   <tr>
			    <td colspan="3" class="text-center">
			     <span style="color: gray;">${vo.content }</span>
			    </td>
			   </tr>
			   
			   <tr>
			    <td class="text-center"><img src="../recipe/icon/a1.png"></td>
			    <td class="text-center"><img src="../recipe/icon/a2.png"></td>
			    <td class="text-center"><img src="../recipe/icon/a3.png"></td>
			   </tr>
			   
			   <tr>
			    <td class="text-center">${vo.info1 }</td>
			    <td class="text-center">${vo.info2 }</td>
			    <td class="text-center">${vo.info3 }</td>
			   </tr>
			  </table>
			  <table class="table">
			   <tr>
				<td><caption><h3>재료</h3></caption></td>
			   </tr>
			   <tr>
			    <td>
			     <ul style="list-style: none;">
			      <c:forTokens items="${vo.data }" delims="," var="d">
			       <li><b>${d }</b></li>
			      </c:forTokens>
			     </ul>
			    </td>
			   </tr>
			  </table>
			  
			  <table class="table">
			   <tr>
			    <td><h3>조리방법</h3></td>
			   </tr>
			   <tr>
			    <td>
			     <c:forEach var="make" items="${mList }" varStatus="s">
			      <table class="table">
			       <tr>
			        <td width=85%>${make }</td>
			        <td width=15%>
			         <img src="${iList[s.index] }" style="width: 100%">
			         </td>
			       </tr>
			      </table>
			     </c:forEach>
			    </td>
			   </tr>
			  </table>
			  <table class="table">
			   <tr> 
			    <td colspan="2"><h3>레시피 작성자</h3></td>
			   </tr>
			   <tr>
			    <td width=20% class="text-center" rowspan="2">
			     <img src="${vo.chef_poster }" style="width:100px;height:100px;" class="rounded-circle">
			    </td>
			    <td width=80%>
			     <h3>${vo.chef }</h3>
			    </td>
			   </tr>
			   <tr>
			    <td width=80%>
			    	${vo.chef_profile }
			    </td>
			   </tr>
			  </table>
			  


            </div>
        </div>
    </section>
    <!-- ****** Archive Area End ****** -->
    <!-- 댓글 부분 붙여넣기 -->
    <%-- <div class="comment_area section_padding_50 clearfix">
                                <h4 class="mb-30">댓글${count }</h4>

                                <ol>
                                   <c:forEach var="rvo" items="${rList }">
                                    <li class="single_comment_area">
                                        <div class="comment-wrapper d-flex">
                                            <!-- Comment Meta -->
                                            <div class="comment-author">
                                                <img src="${rvo.sex}=='남자' ? '../img/images/man.png','../img/images/woman.png' " alt="">
                                            </div>
                                            <!-- Comment Content -->
                                            <div class="comment-content">
                                                <span class="comment-date text-muted">${rvo.dbday }</span>
                                                <h5>${rvo.name }</h5>
                                                <p>${rvo.msg }</p>
                                                <c:if test="${sessionScope.id!=null }">
                                               		 <a href="#" class="btn btn-outline-danger">좋아요</a>
                                               		 <a class="btn btn-outline-danger" href="#">댓글</a>
                                               		 <c:if test="${sessionScope.id==rvo.id ">
                                               		  <a href="#" class="active">수정</a>
                                               		  <a href="#" class="active">삭제</a>
                                               		 </c:if>
                                                </c:if>
                                                
                                            </div>
                                        </div>
                                    </li>
                                    </c:forEach>
                                </ol>
                            </div>

                            <!-- Leave A Comment -->
                            <c:if test="${sessionScope.id!=null"> <!-- 로그인 할 때만 댓글쓸 수 있다 -->
                            <div class="leave-comment-area section_padding_50 clearfix">
                                <div class="comment-form">
                                   <!-- Comment Form -->
                                    <form action="../reply/reply_insert.do" method="post">
                                        <div class="form-group">
                                            <textarea name="msg" id="msg" cols="78" rows="4" placeholder="Message" style="float: left;"></textarea>
                                            <input type="hidden" name="type" value=1> <!-- value=1 => 맛집 , 2=레시피 .... 임의지정-->
                                            <input type="hidden" name="rno" value="${vo.fno}">
                                        	<button type="submit" class="btn btn-primary" style="width: 100px;height:95px;float: left;">댓글쓰기</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            </c:if>
 --%>

</body>
</html>