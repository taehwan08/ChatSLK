<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 제품 목록</title>
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
								<h5 class="m-b-10">제품 목록</h5>
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
							<h5>전체상품</h5>
							<div class="card-header-right">
								<button type="button" class="btn  btn-outline-danger btn-sm" data-toggle="modal" data-target="#deleteProductsModal">선택 삭제</button>
							</div>
							<div id="deleteProductsModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLiveLabel">여러 제품 삭제</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">×</span>
											</button>
										</div>
										<form action="deleteProducts.do" method="post">
											<div class="modal-body">
												<div id="numbers"></div>
												<p class="mb-0">정말로 삭제하시겠습니까?</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
												<button type="submit" class="btn  btn-danger">삭제</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="card-body table-border-style">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>&nbsp;&nbsp;</th>
											<th>NO.</th>
											<th>상품</th>
											<th>브랜드</th>
											<th>카테고리</th>
											<th>하위 카테고리</th>
											<th>가격</th>
											<th>재고</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<!-- [ forEach start ] -->
									<c:forEach var="v" items="${pdatas}">
										<tbody>
											<tr>
												<td>
													<!-- 체크박스 -->
													<div class="chk-option">
														<label class="check-task custom-control custom-checkbox d-flex justify-content-center done-task">
														<input type="checkbox" class="custom-control-input" name="number" value="${v.productNum}">
														<span class="custom-control-label"></span>
														</label>
													</div>
												</td>
												<td>${v.productNum}</td>
												<!-- NO. -->
												<td>${v.productName}</td>
												<!-- 상품 -->
												<td>${v.company}</td>
												<!-- 브랜드 -->
												<td>${v.categoryName}</td>
												<!-- 카테고리 -->
												<td>${v.categoryDetailName}</td>
												<!-- 하위 카테고리 -->
												<td><fmt:formatNumber value="${v.productPrice}" />&nbsp;원</td>
												<!-- 가격 -->
												<td><fmt:formatNumber value="${v.productCnt}" /></td>
												<!-- 재고 -->
												<td class="text-center">
													<form action="editProductPage.do" method="post">
														<input type="hidden" name="productNum" value="${v.productNum}">
														<button type="submit" id="productedit" class="btn  btn-info btn-sm mb-1">수정</button>
													</form>
												</td>
												<td>
													<button type="button" class="btn  btn-danger btn-sm" data-toggle="modal" data-target="#deletemodal${v.productNum}">삭제</button>
												</td>
												<!-- <td class="text-right"><label class="badge badge-light-danger">Low</label></td> -->
											</tr>
										</tbody>
										<div id="deletemodal${v.productNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLiveLabel">제품 삭제</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<div class="modal-body">
														<p class="mb-1">${v.productName}</p>
														<p class="mb-0">정말로 삭제하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
														<form action="deleteProduct.do" method="post">
															<input type="hidden" name="productNum" value="${v.productNum}">
															<button type="submit" class="btn  btn-danger">삭제</button>
														</form>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ Main Content ] end -->
		</div>
	</div>
	<!-- [ Main Content ] end -->

	<!-- Required Js -->
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>

	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>


	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>
	<script type="text/javascript">
	
	$(".custom-control-input").on("change", function() {
	 var productNum = $(this).val();  // 선택한 체크박스의 값 (상품 번호)
	 
	 console.log(productNum);
	 
	 if ($(this).prop("checked")) {
		 var inputElement = '<input type="hidden" name="number" value="' + productNum + '">';
		 $('#numbers').append(inputElement);
	 } else {
		 $("#numbers > input[value='" + productNum + "']").remove();
		}
	});
	</script>


</body>

</html>
