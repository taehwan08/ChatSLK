<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 회원 관리</title>

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

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>


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
								<h5 class="m-b-10">회원 관리</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- prject ,team member start -->
				<div class="col-sm-8">
					<div class="card">
						<div class="card-header">
							<h5>전체 회원</h5>
						</div>
						<div class="card-body table-border-style">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>NO.</th>
											<th>ID</th>
											<th>이름</th>
											<th>이메일</th>
											<th>핸드폰</th>
											<th>권한</th>
											<th>비고</th>
										</tr>
									</thead>
									<!-- [ forEach start ] -->
									<tbody>
										<c:forEach var="i" begin="0" end="${fn:length(mdatas)-1}">
											<tr>
												<td>${i+1}</td>
												<!-- 회원번호 -->
												<td>${mdatas[i].memberId }</td>
												<!-- ID -->
												<td>${mdatas[i].memberName }</td>
												<!-- 이름 -->
												<td>${mdatas[i].email}@${mdatas[i].domain}</td>
												<!-- 이메일 -->
												<td>${mdatas[i].phonenumber}</td>
												<!-- 핸드폰 -->
												<c:if test="${mdatas[i].role eq 'USER'}">
													<td><span class="badge badge-light-secondary">일반회원</span></td>
												</c:if>
												<c:if test="${mdatas[i].role eq 'GOOGLE'}">
													<td><span class="badge badge-light-success">구글회원</span></td>
												</c:if>
												<c:if test="${mdatas[i].role eq 'ADMIN'}">
													<td><span class="badge badge-light-danger">관리자</span></td>
												</c:if>
												<!-- 권한 -->
												<c:set var="memberId" value="${mdatas[i].memberId}" />
												<td><button class="badge  badge-info" onclick="javascript:func('${memberId}')">보기</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="card">
						<div class="card-header">
							<h5>회원 상세</h5>
						</div>
						<div class="card-body table-border-style">
							<div class="table-responsive">
								<form action="updateRole.do" method="post">
									<div class="form-group row">
										<label for="staticID" class="col-sm-3 col-form-label">ID</label>
										<div class="col-sm-9">
											<input type="text" readonly class="form-control-plaintext" name="memberId" id="staticID" value="-">
										</div>
									</div>
									<div class="form-group row">
										<label for="staticName" class="col-sm-3 col-form-label">이름</label>
										<div class="col-sm-9">
											<input type="text" readonly class="form-control-plaintext" id="staticName" value="-">
										</div>
									</div>
									<div class="form-group row">
										<label for="staticEmail" class="col-sm-3 col-form-label">Email</label>
										<div class="col-sm-9">
											<input type="text" readonly class="form-control-plaintext" id="staticEmail" value="-@-.-">
										</div>
									</div>
									<div class="form-group row">
										<label for="staticPhone" class="col-sm-3 col-form-label">핸드폰</label>
										<div class="col-sm-9">
											<input type="text" readonly class="form-control-plaintext" id="staticPhone" value="-">
										</div>
									</div>
									<div class="form-group row">
										<label for="staticRole" class="col-sm-3 col-form-label">권한</label>
										<div class="col-sm-9">
											<div class="input-group">
												<select class="custom-select" name="role" id="inputGroupSelect04">
													<option value="USER">일반회원</option>
													<option value="GOOGLE">구글회원</option>
													<option value="ADMIN">관리자</option>
												</select>
												<div class="input-group-append">
													<button class="btn  btn-primary" type="submit">변경</button>
												</div>
											</div>
										</div>
									</div>
								</form>
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

	<script>
	function func(id) {
		console.log(id);
		var data = { memberId:id };
		$.ajax({
			type: 'POST',
			url: 'memberdetail.do',
			data: data,
			dataType: 'json',
			success: function(response) {
				console.log(response);
				$('#staticID').val(response.memberId);
				$('#staticName').val(response.memberName);
				$('#staticEmail').val(response.email);
				$('#staticPhone').val(response.phonenumber);
				$('#inputGroupSelect04').val(response.role);
			},
			error: function(xhr, status, error) {
				console.log(error);
			}
		});
	};
	</script>


	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>


	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>
</body>

</html>
