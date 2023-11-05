<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SoundPlay | 비밀번호찾기</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico" />
<!-- Google API를 사용하기 위한 스크립트 -->

<link rel="stylesheet" href="assets/css/login.css" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/templatemo.css" />
<link rel="stylesheet" href="assets/css/custom.css" />

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />



</head>

<body>
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<h2 class="h2 text-white">비밀번호 찾기</h2>
		</div>
	</div>
	<div class="container py-5">
		<div class="row py-5">
			<form action="pwSearch.do" class="col-md-9 m-auto" method="post" role="form">
				<div class="row">
					<div class="form-group col-md-6 mb-3">
						<label for="inputname">아이디</label>
						<input type="text" class="form-control mt-1" id="memberId" name="memberId" placeholder="아이디">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6 mb-3">
						<label for="inputname">이름</label>
						<input type="text" class="form-control mt-1" id="memberName" name="memberName" placeholder="이름">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6 mb-3">
						<label for="inputemail">핸드폰 번호</label>
						<input type="text" class="form-control mt-1" id="phonenumber" name="phonenumber" placeholder="핸드폰번호">
					</div>
				</div>
				<div class="row">
					<div class="col text-end mt-2">
						<button type="submit" class="btn btn-success btn-lg px-3">찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<slk:footer />

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/javascript.js"></script>
	<!-- End Script -->
</body>
</html>
