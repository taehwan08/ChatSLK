<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 이미지 더미데이터 등록</title>
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
							<form action="addImageData.do" method="post" enctype="multipart/form-data">
								<h5 class="h5">상세정보</h5>
								<hr>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="productSelect" aria-describedby="categoryHelp">제품 선택</label>
											<!-- 카테고리 -->
											<select name="productNum" class="form-control" id="productSelect">
												<option disabled>제품 선택</option>
												<!-- [ forEach start ]-->
												<c:forEach var="v" items="${pdatas}">
													<option value="${v.productNum}">${v.productNum}- ${v.productName}</option>
												</c:forEach>
												<!-- [ forEach end ]-->
											</select>
										</div>
									</div>
								</div>
								<div class="row" id="addfile">
									<div class="col-sm-6">
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
										<button class="btn  btn-primary" type="submit">이미지 등록</button>
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
