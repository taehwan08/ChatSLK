<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 카테고리 관리</title>
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
								<h5 class="m-b-10">카테고리 관리</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- prject ,team member start -->
				<div class="col-sm-2">
					<div class="card">
						<div class="card-header">
							<h5>기본 카테고리 등록</h5>
						</div>
						<div class="card-body">
							<form action="addCategory.do" method="post">
								<div class="row">
									<div class="col-sm-12">
										<label for="categorySelect" aria-describedby="categoryHelp">기본 카테고리 입력<code>&nbsp;*</code></label>
										<div class="input-group">
											<input type="text" name="categoryName" class="form-control" aria-label="category" aria-describedby="category" placeholder="10자 이내 입력" required>
											<div class="input-group-append">
												<button class="btn  btn-primary" type="submit">등록</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="card">
						<div class="card-header">
							<h5>세부 카테고리 등록</h5>
						</div>
						<div class="card-body">
							<form action="addCategoryDetail.do" method="post">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label for="categorySelect" aria-describedby="categoryHelp">기본 카테고리 선택<code>&nbsp;*</code></label>
											<select class="form-control" id="categorySelect" name="categoryNum">
												<!-- [ forEach start ]-->
												<c:forEach var="v" items="${ctdatas}">
												<option value="${v.categoryNum}">${v.categoryNum} - ${v.categoryName}</option>
												</c:forEach>
												<!-- [ forEach end ]-->
											</select>
										</div>
										<label for="categorySelect" aria-describedby="categoryHelp">세부 카테고리 입력<code>&nbsp;*</code></label>
										<div class="input-group">
											<input type="text" name="categoryDetailName" class="form-control" aria-label="category" aria-describedby="category" placeholder="10자 이내 입력" required>
											<div class="input-group-append">
												<button class="btn  btn-primary" type="submit">등록</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-sm-10">
					<div class="card">
						<div class="card-header">
							<h5>전체 카테고리</h5>
						</div>
						<div class="card-body table-border-style">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>NO.</th>
											<th>기본 카테고리</th>
											<th>제품 수</th>
											<th class="text-center">비고</th>
											<th></th>
											<th>NO.</th>
											<th>세부 카테고리</th>
											<th>제품 수</th>
											<th class="text-center">비고</th>
										</tr>
									</thead>
									<!-- [ forEach start ] -->
									<c:forEach var="v" items="${cddatas}">
										<tbody>
											<tr>
												<td>${v.categoryNum}</td>
												<!-- NO. -->
												<td>${v.categoryName}</td>
												<!-- 기본 카테고리 -->
												<td>${v.categoryCnt}</td>
												<td class="text-center">
													<button type="button" class="btn  btn-info btn-sm" data-toggle="modal" data-target="#editcategorymodal${v.categoryNum}">수정</button>
													<c:if test="${v.categoryCnt eq 0}">
														<button type="button" class="btn  btn-danger btn-sm" data-toggle="modal" data-target="#deletecategorymodal${v.categoryNum}">삭제</button>
													</c:if>
												</td>
												<td></td>
												<td>${v.categoryDetailNum}</td>
												<td>${v.categoryDetailName}</td>
												<td>${v.categoryDetailCnt}</td>
												<!-- 세부 카테고리 -->
												<td class="text-center">
													<button type="button" class="btn  btn-info btn-sm" data-toggle="modal" data-target="#editcategorydetailmodal${v.categoryDetailNum}">수정</button>
													<c:if test="${v.categoryDetailCnt eq 0}">
														<button type="button" class="btn  btn-danger btn-sm" data-toggle="modal" data-target="#deletecategorydetailmodal${v.categoryDetailNum}">삭제</button>
													</c:if>
												</td>
												<!-- <td class="text-right"><label class="badge badge-light-danger">Low</label></td> -->
											</tr>
										</tbody>
										<div id="editcategorymodal${v.categoryNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">카테고리 수정</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<form action="updateCategory.do" method="post">
														<div class="modal-body">
															<div class="form-group">
																<label for="recipient-name" class="col-form-label">카테고리명</label>
																<input type="text" class="form-control" id="recipient-name" name="categoryName" value="${v.categoryName}" />
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
															<input type="hidden" name="categoryNum" value="${v.categoryNum}" />
															<button type="submit" class="btn  btn-primary">수정</button>
														</div>
													</form>
												</div>
											</div>
										</div>
										<div id="editcategorydetailmodal${v.categoryDetailNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">세부 카테고리 수정</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<form action="updateCategoryDetail.do" method="post">
														<div class="modal-body">
															<div class="form-group">
																<label for="recipient-name" class="col-form-label">세부 카테고리명</label>
																<input type="text" class="form-control" id="recipient-name" name="categoryDetailName" value="${v.categoryDetailName}" />
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
															<input type="hidden" name="categoryDetailNum" value="${v.categoryDetailNum}" />
															<button type="submit" class="btn  btn-primary">수정</button>
														</div>
													</form>
												</div>
											</div>
										</div>
										<div id="deletecategorymodal${v.categoryNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLiveLabel">카테고리 삭제</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<div class="modal-body">
														<p class="mb-0">정말로 삭제하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
														<form action="deleteCategory.do" method="post">
															<input type="hidden" name="categoryNum" value="${v.categoryNum}" />
															<button type="submit" class="btn  btn-danger">삭제</button>
														</form>
													</div>
												</div>
											</div>
										</div>
										<div id="deletecategorydetailmodal${v.categoryDetailNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLiveLabel">상세 카테고리 삭제</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<div class="modal-body">
														<p class="mb-0">정말로 삭제하시겠습니까?</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
														<form action="deleteCategoryDetail.do" method="post">
															<input type="hidden" name="categoryNum" value="${v.categoryNum}" />
															<input type="hidden" name="categoryDetailNum" value="${v.categoryDetailNum}" />
															<button type="submit" class="btn  btn-danger">삭제</button>
														</form>
													</div>
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
	<!-- [ Main Content ] end -->

	<!-- Required Js -->
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>

	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>


	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>

	<script>
		$('#editmodal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget)
			var recipient = button.data('whatever')
			var modal = $(this)
			modal.find('.modal-body input').val(recipient)
		})
	</script>


</body>
</html>