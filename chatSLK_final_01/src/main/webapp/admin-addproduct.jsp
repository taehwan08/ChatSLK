<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 제품 등록</title>
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
								<h5 class="m-b-10">제품 등록</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<div class="col-sm-12">
					<div class="card">
						<div class="card-header">
							<h5 class="h5">제품 등록</h5>
						</div>
						<div class="card-body">
							<h5>기본정보</h5>
							<small><code>* </code>는 필수항목 입니다.</small>
							<hr>
							<form action="addproduct.do" method="post" enctype="multipart/form-data">
								<div class="row">
									<div class="col-sm-8">
										<div class="form-group">
											<label class="floating-label" for="productName" aria-describedby="productNameHelp">제품명<code>&nbsp;*</code></label>
											<!-- 제품명 -->
											<input type="text" name="productName" class="form-control" id="productName" required> <small id="productNameHelp" class="form-text text-muted">50자 이내로 작성해주세요.</small>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<label class="floating-label" for="brand">브랜드<code>&nbsp;*</code></label>
											<!-- 브랜드 -->
											<input type="text" name="company" class="form-control" id="brand" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group">
											<label class="floating-label" for="productPrice" aria-describedby="productPriceHelp">금액<code>&nbsp;*</code></label>
											<!-- 금액 -->
											<input type="text" name="productPrice" class="form-control" id="productPrice" required> <small id="productPriceHelp" class="form-text text-muted">숫자만 입력해주세요.</small>
										</div>
									</div>
									<div class="col-sm-2">
										<div class="form-group">
											<label class="floating-label" for="productCnt" aria-describedby="productCntHelp">재고</label>
											<!-- 재고 -->
											<input type="text" name="productCnt" class="form-control" id="productCnt" required>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label for="categorySelect" aria-describedby="categoryHelp">카테고리<code>&nbsp;*</code></label>
											<!-- 카테고리 -->
											<select name="categoryDetailNum" class="form-control" id="categorySelect">
												<option disabled>카테고리 선택</option>
												<!-- [ forEach start ]-->
												<c:forEach var="v" items="${cddatas}">
													<option value="${v.categoryDetailNum}">${v.categoryDetailNum} - ${v.categoryDetailName}</option>
												</c:forEach>
												<!-- [ forEach end ]-->
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label for="productexplainTextarea">제품 설명</label>
											<!-- 제품설명 -->
											<textarea name="productExplain" class="form-control" id="productexplainTextarea" rows="3" placeholder="간단한 설명을 작성해주세요."></textarea>
										</div>
									</div>
								</div>
								<br>
								<h5 class="h5">상세정보</h5>
								<hr>
								<div class="row" id="addfile">
									<div class="col-sm-12">
										<div class="input-group cust-file-button">
											<div class="custom-file">
												<!-- 이미지 -->
												<input type="file" name="file" id="inputFile" multiple required> <label class="custom-file-label" for="inputFile">사진 선택</label>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6" id="selectedFile"></div>
								</div>
								<br>
								<hr>
								<div class="row">
									<div class="col-sm-12">
										<button class="btn  btn-primary" type="submit">상품 등록</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- [ Main Content ] end -->
		</div>
	</div>
	<!-- [ Main Content ] end -->

	<!-- Required Js -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>
	
	<script>
        $(document).ready(function () {
            $('#inputFile').on('change', function () {
                $('#selectedFile').html('');

                const files = this.files;
                for (let i = 0; i < files.length; i++) {
                    const file = files[i];
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        const img = $('<img>').attr('src', e.target.result).addClass('wid-100 m-t-15 m-r-15');
                        $('#selectedFile').append(img);
                    };

                    reader.readAsDataURL(file);
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
