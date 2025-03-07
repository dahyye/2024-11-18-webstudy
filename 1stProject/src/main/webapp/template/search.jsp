<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="container-xxl py-3">
    	<div class="container">
    		<form action="" method="post" name="page-search">
	    		<div class="sch_wrap page">
					<input type="text" name="key" placeholder="검색어를 입력하세요.">
					<input type="submit" value="검색">
				</div>	
    		</form>
    		<div class="filter-wrap">filter section</div>
    	</div>
    </div>
	<div class="container-xxl py-3">
		<div class="container">
    		<h4><span>제주도</span>에 대한 총 <span>1,324</span> 건의 검색 결과</h4>
			<ul class="content-ul event">
				<c:forEach begin="0" end="9">
					<li>
						<a href="#" class="d-flex">
							<div class="thumb-wrap" style="background-image:url(../assets/img/hotel1.jpg)"></div>
							<div class="d-flex right">
								<div class="title-wrap">
									<p class="content-name">강원관광두레 팝업스토어</p>
									<p class="location">서울 성동구 뚝섬로</p>
									<p class="enddate">~2025.12.13</p>
								</div>
								<div class="price-wrap">
									<p class="price">7,000원</p>
								</div>
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
</html>