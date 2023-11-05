<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 관리자</title>
<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="">
<meta name="author" content="Phoenixcoded" />
<!-- Favicon icon -->
<link rel="icon" href="assets/img/logo-no-background.ico" type="image/x-icon">
<!-- vendor css -->
<link rel="stylesheet" href="assets/css/adminstyle.css">



</head>
<body class="">
	<!-- [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<!-- [ Pre-loader ] End -->
	<!-- [ navigation menu ] start -->
	<slk:admin-nav />
	<!-- [ navigation menu ] end -->

	<!-- [ Main Content ] start -->
	<div class="pcoded-main-container">
		<div class="pcoded-content">
			<!-- [ breadcrumb ] start -->
			<div class="page-header">
				<div class="page-block">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="page-header-title">
								<h5 class="m-b-10">대시보드</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- table card-1 start -->
				<div class="col-md-12 col-xl-4">
					<div class="card flat-card">
						<div class="row-table">
							<div class="col-sm-6 card-body br">
								<div class="row">
									<div class="col-sm-4">
										<i class="icon feather icon-shopping-cart text-c-green mb-1 d-block"></i>
									</div>
									<div class="col-sm-8 text-md-center">
										<h5>${paydata.payCount}</h5>
										<span>최근 주문건수</span>
									</div>
								</div>
							</div>
							<div class="col-sm-6 card-body">
								<div class="row">
									<div class="col-sm-4">
										<i class="icon feather icon-star text-c-yellow mb-1 d-block"></i>
									</div>
									<div class="col-sm-8 text-md-center">
										<h5>${rdata.reviewCount}</h5>
										<span>최근 리뷰건수</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- widget primary card start -->
					<%-- <div class="card flat-card widget-primary-card">
						<div class="row-table">
							<div class="col-sm-3 card-body">
								<h4 class="m-t-5 m-b-5">매출액</h4>
							</div>
							<div class="col-sm-9">
								<h4 class="m-t-5 m-b-5"><fmt:formatNumber value="${pddata.payTotal}" /> 원
								</h4>
							</div>
						</div>
					</div> --%>
					<div class="card flat-card">
						<div class="row-table">
							<div class="col-sm-4 card-body">
								<h6 class="text-muted m-b-0">매출액</h6>
							</div>						
							<div class="col-sm-10 card-body">
								<h3><fmt:formatNumber value="${pddata.payTotal}" /></h3>
							</div>
							<div class="col-sm-2 card-body">
								<h6 class="text-muted m-b-0">원</h6>
							</div>
							<!-- <div class="col-sm-6">
								<div id="seo-chart1" class="d-flex align-items-end"></div>
							</div> -->
						</div>
					</div>
					<!-- widget primary card end -->
				</div>
				<!-- table card-1 end -->


				<!-- prject ,team member start -->
				<c:if test="${not empty paydatas}">
					<div class="col-md-12 col-xl-8">
						<div class="card table-card">
							<div class="card-header">
								<h5>
									<a href="orderListPage.do" class="text-dark">새로운 주문 <span class="badge badge-danger">${fn:length(paydatas)} 건</span>
									</a>
								</h5>
							</div>
							<div class="card-body p-0">
								<div class="table-responsive">
									<table class="table table-hover mb-0">
										<thead>
											<tr>
												<th>상품명</th>
												<th>주문자명</th>
												<th>주문일시</th>
												<th>결제방법</th>
												<th>배송지</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="v" items="${paydatas}">
												<tr>
													<td>${v.productName}</td>
													<td>${v.memberId}</td>
													<td>${v.time}</td>
													<td>${v.payMethod }</td>
													<td>${v.address}</td>
													<!-- <td class="text-right"><label class="badge badge-light-danger">Low</label></td> -->
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!-- prject ,team member start -->

				<!-- Latest Customers start -->
				<c:if test="${not empty rdatas}">
					<div class="col-lg-12 col-md-12">
						<div class="card table-card review-card">
							<div class="card-header borderless ">
								<h5>
									<a href="reviewPage.do" class="text-dark">새로운 리뷰 <span class="badge badge-danger">${fn:length(rdatas)} 건</span></a>
								</h5>
							</div>
							<div class="card-body pb-0">
								<div class="review-block">
									<c:forEach var="v" items="${rdatas}">
										<div class="row">
											<div class="col">
												<h6 class="m-b-15">
													${v.title}<span class="float-right f-13 text-muted">${v.time}</span>
												</h6>
												<div class="reviewstar${v.star}"></div>
												<p class="m-t-15 m-b-15 text-muted">${v.content}</p>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!-- Latest Customers end -->
			</div>
			<!-- [ Main Content ] end -->
		</div>
	</div>
	<!-- [ Main Content ] end -->


	<!-- Required Js -->
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>
	<script>
    // 페이지 로드 후 실행
    document.addEventListener("DOMContentLoaded", function() {
        // 각 별점에 대한 처리
        const reviewStars = document.querySelectorAll('[class^="reviewstar"]');
        reviewStars.forEach(starContainer => {
            const starCount = parseInt(starContainer.classList[0].replace("reviewstar", ""));
            for (let i = 0; i < 5; i++) {
                const starIcon = document.createElement("i");
                starIcon.classList.add("feather", "icon-star-on", "f-18", "text-c-yellow");
                if (i >= starCount) {
                    starIcon.classList.remove("icon-star-on");
                    starIcon.classList.add("icon-star", "f-18", "text-muted");
                }
                starContainer.appendChild(document.createTextNode("\n")); // 줄바꿈
                starContainer.appendChild(starIcon);
            }
        });
    });
</script>

	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>

	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>
</body>

</html>
