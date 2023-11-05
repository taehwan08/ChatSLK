<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SoundPlay | 아이디찾기</title>
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
<link rel="stylesheet" href="assets/css/join.css" />

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />




</head>

<body>
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<h2 class="h2 text-white">아이디 찾기</h2>
		</div>
	</div>
	<div class="container py-5">
		<div class="row py-5">
			<form action="idSearch.do" class="col-md-9 m-auto" method="post" role="form">
				<div class="row">
					<div class="form-group col-md-6 mb-3">
						<label for="inputname">이름</label> <input type="text" class="form-control mt-1" id="name" name="memberName" placeholder="이름">
					</div>
				</div>
				<div class="input-group mb-3">
					<label for="inputemail">Email</label> <span class="input-group-text"> <i class="fa fa-fw fa-envelope text-dark"></i>
					</span> <input type="text" name="email" class="form-control" placeholder="email" aria-label="Email" required /> <span class="input-group-text">@</span> <input class="form-control" name="domain" id="domain-txt" type="text" required /> <select class="form-select" id="domain-list">
						<option value="type">직접 입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="kakao.com">kakao.com</option>
					</select>
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
