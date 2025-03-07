<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%// wow는 main에서만 적용 %>
    <nav class="navbar navbar-expand-lg fixed-top py-lg-0 px-lg-4 ${title eq '메인'?'wow':'bg-white' }">
        <a href="main.do" class="navbar-brand ms-4 ms-lg-0">
            <h1 class="m-0 home">Festeller</h1>
        </a>
        <div class="d-flex justify-content-end flex-fill">
	        <div class="collapse navbar-collapse" id="navbarCollapse">
	            <div class="navbar-nav mx-auto p-4 p-lg-0">
	                <a href="../hotel/hotel_list.do" class="nav-item nav-link">숙소</a>
	                <a href="index.html" class="nav-item nav-link">행사</a>
	                <a href="index.html" class="nav-item nav-link">축제</a>
	                <a href="about.html" class="nav-item nav-link">지도보기</a>
	                <div class="nav-item">
	                    <a href="#" class="nav-link" data-bs-toggle="collapse" data-bs-target="#subnav1">게시판</a>
	                    <div class="collapse subnav mb-lg-4" id="subnav1">
	                        <a href="team.html" class="nav-item nav-link">공지사항</a>
	                        <a href="testimonial.html" class="nav-item nav-link">Q&A</a>
	                        <a href="404.html" class="nav-item nav-link">404 Page</a>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="d-flex justify-content-end align-self-baseline">
	        	<div class="d-flex" id="nav-wide-right">
	            	<div class="flex-shrink-0 btn-square rounded-circle srhbtn">
						<svg class="srhbtn" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"/></svg>
	                </div>
	                <div class="flex-shrink-0 btn-square rounded-circle">
	                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512l388.6 0c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304l-91.4 0z"/></svg>
	                </div>
	            </div>
	            <button type="button" class="navbar-toggler me-4 ms-3" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
		            <!-- <span class="navbar-toggler-icon"></span>-->
		            <div class="menu-icon">
			            <div class="i_menu"></div>
		            </div>
		        </button>
	        </div>
        </div>
    </nav>
    <div class="search-wrap">
    	<div class="search-inner">
    		<div class="search-header">
    			<button type="button" class="closebtn"></button>
    		</div>
    		<div class="center_wrap">
    			<form action="" method="post" name="main-search">
	    			<div class="sch_wrap">
						<select name="option">
							<option value="1">숙박</option>
							<option value="2">행사</option>
						</select>
						<input type="text" name="key" placeholder="검색어를 입력하세요.">
						<input type="submit" value="검색">
					</div>
    			</form>
				<div class="latest_wrap">
					<div class="latest">
						<span>검색어</span>
						<button type="button" name="del"></button>
					</div>
				</div>
    		</div>
    	</div>
    </div>
    <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(){
    	const inputWrap = document.querySelector(".sch_wrap");
    	const inputkey = document.querySelector("input[name='key']");
    	const sel = document.querySelector("select[name='option']");
    	inputkey.onfocus = function() {
    		inputWrap.classList.add('focus');
    	};
    	inputkey.onblur = function() {
    		inputWrap.classList.remove('focus');
    	};
    });
    $(document).on("click",".srhbtn", function(){
    	$('body').addClass('on');
    	$('.search-wrap').fadeIn(100);
    	$('input[name="key"]').focus();
    });
    $(document).on("click",".closebtn", function(){
    	$('.search-wrap').fadeOut(100);
    	$('body').removeClass('on');
    });
    </script>
</body>
</html>