<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 리뷰 등록</title>
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
								<h5 class="m-b-10">리뷰 관리</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- Latest Customers start -->
				<div class="col-lg-12 col-md-12 col-sm-auto">
					<div class="card">
						<div class="card-header">
							<h5>리뷰 등록</h5>
						</div>
						<div class="card-body">
							<form>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="proudctSelect" aria-describedby="proudctHelp">제품선택</label> <select class="form-control" id="proudctSelect">
												<option>선택없음</option>
												<!-- [ forEach start ]-->
												<option>카테고리이름</option>
												<!-- [ forEach end ]-->
											</select>
										</div>
									</div>
									<div class="col-sm-auto">
										<div class="form-group">
											<label for="reviewTitle" aria-describedby="reviewCntHelp">별점</label> <select class="form-control" id="proudctSelect">
												<option>5</option>
												<option>4</option>
												<option>3</option>
												<option>2</option>
												<option>1</option>
											</select>
										</div>
									</div>
									<div class="col-sm-auto">
										<div class="form-group">
											<label for="reviewDate" aria-describedby="reviewDateHelp">날짜</label> <input type="datetime-local" class="form-control" id="reviewTitle" required> <small id="reviewDateHelp" class="form-text text-muted">오늘날짜 이전으로 선택하세요.</small>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label class="floating-label" for="reviewTitle" aria-describedby="reviewTitleHelp">제목</label> <input type="text" class="form-control" id="reviewTitle" required>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label for="productexplainTextarea">내용</label>
											<textarea class="form-control" id="productexplainTextarea" rows="5" placeholder="리뷰 내용을 작성해주세요."></textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-auto">
										<button class="btn  btn-primary" type="submit">리뷰 등록</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Latest Customers end -->
			</div>
			<!-- [ Main Content ] end -->
		</div>
	</div>
	<!-- [ Main Content ] end -->
	<!-- Warning Section start -->
	<!-- Older IE warning message -->
	<!--[if lt IE 11]>
        <div class="ie-warning">
            <h1>Warning!!</h1>
            <p>You are using an outdated version of Internet Explorer, please upgrade
               <br/>to any of the following web browsers to access this website.
            </p>
            <div class="iew-container">
                <ul class="iew-download">
                    <li>
                        <a href="http://www.google.com/chrome/">
                            <img src="assets/images/browser/chrome.png" alt="Chrome">
                            <div>Chrome</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.mozilla.org/en-US/firefox/new/">
                            <img src="assets/images/browser/firefox.png" alt="Firefox">
                            <div>Firefox</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.opera.com">
                            <img src="assets/images/browser/opera.png" alt="Opera">
                            <div>Opera</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.apple.com/safari/">
                            <img src="assets/images/browser/safari.png" alt="Safari">
                            <div>Safari</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                            <img src="assets/images/browser/ie.png" alt="">
                            <div>IE (11 & above)</div>
                        </a>
                    </li>
                </ul>
            </div>
            <p>Sorry for the inconvenience!</p>
        </div>
    <![endif]-->
	<!-- Warning Section Ends -->

	<!-- Required Js -->
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>

	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>


	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>
</body>

</html>