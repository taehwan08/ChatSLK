<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SoundPlay | 마이페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="assets/css/fontawesome.css">


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
			<h1 class="h1 text-white">마이페이지</h1>
		</div>
	</div>

	<div class="container py-5">
		<div class="row py-5" style="padding-bottom: 20rem !important;">


			<!-- Wrapper -->
			<div id="wrapper">
				<ul class="nav nav-tabs">
					<li class="nav-item "><a class="nav-link active text-light" aria-current="page" href="mypagePage.do">주문목록</a></li>
					<li class="nav-item"><a class="nav-link text-dark" href="wishlistPage.do">위시리스트</a></li>
					<li class="nav-item"><a class="nav-link text-dark" href="signupPage.do">개인정보변경</a></li>
				</ul>

				<!-- Shopping Cart Section Begin -->
				<div class="shopping__cart__table">
					<table class="table">
						<thead class="table-dark">
							<tr>
								<th>
									<!-- 이미지 -->
								</th>
								<th>상품</th>
								<th>수량</th>
								<th>가격</th>
								<th>배송상태</th>
								<th>
									<!-- 리뷰 -->
								</th>
								<th></th>
							</tr>
						</thead>
						<tbody style="vertical-align: middle;">
							<slk:payDoneList />
						</tbody>

					</table>
				</div>

			</div>
		</div>
	</div>

	<slk:footer />



	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<!-- End Script -->
</body>

</html>