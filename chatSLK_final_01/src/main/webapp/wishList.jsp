<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SoundPlay | 위시리스트</title>
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
			<h1 class="h1 text-white">위시리스트</h1>

		</div>
	</div>

	<div class="container py-5">
		<div class="row py-5" style="padding-bottom: 20rem !important;">
			<!-- Header -->
			<div id="wrapper">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link text-dark " href="mypagePage.do">주문목록</a></li>
					<li class="nav-item"><a class="nav-link active text-light" aria-current="page" href="wishlistPage.do">위시리스트</a></li>
					<li class="nav-item"><a class="nav-link text-dark" href="signupPage.do">개인정보변경</a></li>
				</ul>
				<!-- Shopping Cart Section Begin -->
				<form action="wishToCart.do" method="post">
					<div class="shopping__cart__table">
						<table class="table">
							<thead class="table-dark">
								<tr>
									<th></th>
									<th></th>
									<th>상품</th>
									<th>가격</th>
									<th>삭제</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<slk:wishList />
							</tbody>
						</table>
						<c:if test="${empty wdatas}">
							<div class="row" style="text-align: right;">
								<div class="continue__btn">
									<a href="shopPage.do" class="btn btn-success btn-lg px-3">쇼핑하러 가기</a>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty wdatas}">
							<div class="row" style="text-align: right;">
								<div class="continue__btn">
									<button type="submit" class="btn btn-success btn-lg px-3" id="cartbtn" disabled="disabled">장바구니 추가</button>
								</div>
							</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>



	<slk:footer />


	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	    // 체크박스 상태 변경 이벤트 핸들러
	    $("input[type='checkbox']").change(function() {
	        var checkboxes = $("input[type='checkbox']");
	        var checkedCheckboxes = checkboxes.filter(":checked");
	        var cartButton = $("#cartbtn");

	        if (checkedCheckboxes.length > 0) {
	            // 최소 하나 이상의 체크박스가 선택된 경우 버튼 활성화
	            cartButton.prop("disabled", false);
	        } else {
	            // 모든 체크박스가 선택 해제된 경우 버튼 비활성화
	            cartButton.prop("disabled", true);
	        }
	    });
	});
	</script>

	<!-- End Script -->
</body>

</html>