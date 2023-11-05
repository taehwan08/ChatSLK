<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 주문 내역</title>
<!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->
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
								<h5 class="m-b-10">주문 내역</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- prject ,team member start -->
				<div class="col-sm-12">
					<div class="card">
						<div class="card-header">
							<h5>전체 주문</h5>
							<div class="card-header-right">
								<button type="button" class="btn  btn-primary btn-sm" id="downloadtoexcel">엑셀 다운</button>
							</div>
						</div>
						<div class="card-body table-border-style">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th></th>
											<th>주문번호</th>
											<th>상품정보</th>
											<th>주문자ID</th>
											<th>주문일시</th>
											<th>결제방법</th>
											<th>우편번호</th>
											<th>주소</th>
											<th>상세주소</th>
											<th>배송상태</th>
											<th class="text-center">비고</th>
										</tr>
									</thead>
									<!-- [ forEach start ] -->
									<c:forEach var="v" items="${paydatas}">
										<tbody>
											<tr>
												<td>
																									
												</td>
												<!-- 주문번호 -->
												<td>${v.payNum}</td>
												<!-- 상품정보 -->
												<c:if test="${v.payCount eq 0}">
													<td>${v.productName}</td>
												</c:if>
												<c:if test="${v.payCount ne 0}">
													<td>${v.productName} 외 ${v.payCount}건</td>
												</c:if>
												<!-- 주문자ID -->
												<td>${v.memberId}</td>
												<!-- 주문일시 -->
												<td>${v.time}</td>
												<!-- 결제방법 -->
												<td>${v.payMethod}</td>
												<!-- 우편번호 -->
												<td>${v.zipcode}</td>
												<!-- 주소 -->
												<td>${v.address}</td>
												<!-- 상세주소 -->
												<td>${v.addressDetail}</td>
												<c:if test="${v.status eq 'BEFORE'}">
													<td><span class="badge badge-light-secondary">배송 전</span></td>
													<td class="text-center">
														<button type="button" class="btn  btn-primary btn-sm 상품번호" data-toggle="modal" data-target="#ordermodal${v.payNum}">배송</button>
													</td>

												</c:if>
												<c:if test="${v.status eq 'ING'}">
													<td><span class="badge badge-light-success">배송 중</span></td>
													<td></td>
												</c:if>
												<c:if test="${v.status eq 'DONE'}">
													<td><span class="badge badge-light-info">배송 완료</span></td>
													<td></td>
												</c:if>
											</tr>
										</tbody>
										<div id="ordermodal${v.payNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLiveLabel">제품 발송</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<div class="modal-body">
														<c:if test="${v.payCount eq 0}">
															<p class="mb-1">${v.productName}</p>
														</c:if>
														<c:if test="${v.payCount ne 0}">
															<p class="mb-1">${v.productName}외${v.payCount}건</p>
														</c:if>
														<p class="mb-0">발송하시겠습니까?</p>
													</div>
													<form action="updatepay.do" method="post">
														<div class="modal-footer">
															<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
															<input type="hidden" name="payNum" value="${v.payNum}" />
															<button type="submit" class="btn  btn-primary">발송</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</c:forEach>
									<!-- [ forEach end ] -->
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ Main Content ] end -->
		</div>
	</div>

	<!-- Required Js -->
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>

	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>
	
	<script>
	$('#downloadtoexcel').click(function(){
		location.href = 'excelDown.do';
	});	
	</script>


	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>
</body>

</html>
