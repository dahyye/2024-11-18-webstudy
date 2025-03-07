<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Festeller</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<!-- Icon Font Stylesheet -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
	
	<!-- Libraries Stylesheet -->
	<link href="${pageContext.request.contextPath }/assets/plugin/animate/animate.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/assets/plugin/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/assets/plugin/swiper/swiper-bundle.css" rel="stylesheet">
	
	<!-- Customized Bootstrap Stylesheet -->
	<link href="${pageContext.request.contextPath }/assets/css/bootstrapEdit.css" rel="stylesheet">
	
	<!-- Template Stylesheet -->
	<link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body class="${wide eq y?'wide':''} ${title eq '메인'?'main':'' }">
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="${main_jsp }"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- JavaScript Libraries -->
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/plugin/wow/wow.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/plugin/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/plugin/waypoints/waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/plugin/counterup/counterup.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/plugin/owlcarousel/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/plugin/swiper/swiper-bundle.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/main.js"></script>
</body>
</html>