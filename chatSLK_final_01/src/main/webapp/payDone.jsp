<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SoundPlay | 결제완료</title>
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
<link rel="stylesheet" href="assets/css/fontawesome.min.css">



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
			<h1 class="h1 text-white">결제 완료 페이지</h1>
		</div>
	</div>

	<div class="container py-5">
		<div class="row py-5">
			<!-- Wrapper -->
			<div id="wrapper">
				<!-- Shopping Cart Section Begin -->

				<div class="shopping__cart__table">
					<table class="table">
						<thead class="table-dark">
							<tr>
								<th></th>
								<th>상품</th>
								<th>수량</th>
								<th>가격</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<slk:payDone />
						</tbody>
					</table>
				</div>

			</div>
		</div>
		<div class="row" style="text-align: right;">
			<div class="continue__btn">
				<a href="main.do" class="btn btn-success btn-lg px-3">메인으로 가기</a> <a href="shopPage.do" class="btn btn-success btn-lg px-3">쇼핑 더 하기</a>
			</div>
		</div>
	</div>
	s


	<slk:footer />


	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- End Script -->
</body>

</html>