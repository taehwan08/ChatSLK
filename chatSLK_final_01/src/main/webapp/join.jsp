<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:if test="${empty memberId}">
	<title>SoundPlay | 회원가입</title>
</c:if>
<c:if test="${not empty memberId}">
	<title>SoundPlay | 정보변경</title>
</c:if>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico" />

<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/templatemo.css" />
<link rel="stylesheet" href="assets/css/custom.css" />
<link rel="stylesheet" href="assets/css/join.css" />

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />

<!-- Load map styles -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A==" crossorigin="" />

</head>

<body>
	<!-- Start Top Nav -->
	<slk:nav />
	<!-- Close Top Nav -->

	<!-- Header -->
	<slk:header />
	<!-- Close Header -->

	<!-- Modal -->
	<slk:modal />
	
	<!-- 챗봇 -->
	<slk:chatBot />

	<!-- Start Content Page -->
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<c:if test="${empty memberId}">
				<h1 class="h1 text-white">회원가입</h1>
			</c:if>
			<c:if test="${not empty memberId}">
				<h1 class="h1 text-white">정보변경</h1>
			</c:if>
		</div>
	</div>

	<!-- Start Contact -->
	<div class="container py-5">
		<div class="row py-5">
			<!-- join tag-->
			<slk:join />
			<!-- join tag-->
		</div>
	</div>

	<!-- Start Footer -->
	<slk:footer />
	<!-- End Footer -->

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/join.js"></script>
	<!-- End Script -->
</body>
</html>
