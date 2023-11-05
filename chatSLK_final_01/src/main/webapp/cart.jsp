<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SoundPlay | 장바구니</title>
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
			<h1 class="h1 text-white">장바구니</h1>

		</div>
	</div>

	<div class="container py-5">
		<div class="row py-5" style="padding-bottom: 20rem !important;">
			<!-- Shopping Cart Section Begin -->
			<section class="shopping-cart spad">
				<div class="container">
					<div class="row">
						<form method="post">
							<div class="shopping__cart__table">
								<table class="table">
									<thead class="table-dark">
										<tr>
											<th></th>
											<th></th>
											<th></th>
											<th>상품</th>
											<th>수량</th>
											<th>가격</th>
											<th>삭제</th>
											<th></th>
										</tr>
									</thead>
									<tbody style="vertical-align: middle;">
										<slk:inCart />
									</tbody>
								</table>
							</div>
							<c:if test="${empty cdatas}">
								<div class="row" style="text-align: right;">
									<div class="continue__btn">
										<a href="shopPage.do" class="btn btn-success btn-lg px-3">쇼핑 바로가기</a>
									</div>
								</div>
							</c:if>
							<c:if test="${not empty cdatas}">
								<div class="row" style="text-align: right;">
									<div class="continue__btn">
										<button id="delbtn" type="submit" class="btn btn-danger px-3"
										formaction="deleteCartlist.do" disabled="disabled">삭제</button>
										<button id="paybtn" type="submit" class="btn btn-success px-3"
										formaction="payPage.do" disabled="disabled">결제</button>
									</div>
								</div>
							</c:if>
						</form>
					</div>
				</div>
			</section>
		</div>
	</div>
	<!-- Search End -->

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
	        var delButton = $("#delbtn");
	        var payButton = $("#paybtn");

	        if (checkedCheckboxes.length > 0) {
	            // 최소 하나 이상의 체크박스가 선택된 경우 버튼 활성화
	            delButton.prop("disabled", false);
	            payButton.prop("disabled", false);
	        } else {
	            // 모든 체크박스가 선택 해제된 경우 버튼 비활성화
	            delButton.prop("disabled", true);
	            payButton.prop("disabled", true);
	        }
	    });
	});
	</script>
	<!-- End Script -->
</body>

</html>