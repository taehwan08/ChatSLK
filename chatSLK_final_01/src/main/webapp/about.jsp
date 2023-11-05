<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay | 회사소개</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/logo-no-background.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">
<link rel="stylesheet" href="assets/css/map.css">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">

</head>

<body>
	<!-- Start Top Nav -->
	<slk:nav />

	<!-- Header -->
	<slk:header />
	<!-- Close Header -->

	<!-- Modal -->
	<slk:modal />
	
	<!-- 챗봇 -->
	<slk:chatBot />

	<!-- Start Section -->

	<section>
		<div>
			<img alt="About Hero" src="assets/img/test/221122_221780_1950.jpg" width=100%>
		</div>
	</section>
	<section>
		<div>
			<img alt="About Hero" src="assets/img/test/beolab01-1.jpg" width=100%>
		</div>
	</section>
	<section>
		<div>
			<img alt="About Hero" src="assets/img/1.jpeg" width=100%>
		</div>
	</section>


	<br>

	<!-- 지도 부분 -->
	<section class="container py-5">
		<div class="container my-4">
			<!-- 유튜브 embed 부분 -->
			<style>
.embed-container {
	position: relative;
	padding-bottom: 56.25%;
	height: 0;
	overflow: hidden;
	max-width: 100%;
}

.embed-container iframe, .embed-container object, .embed-container embed
	{
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}
</style>
			<div class='embed-container'>
				<iframe src='https://www.youtube.com/embed/D-h6MoF7HLA' frameborder='0' allowfullscreen></iframe>
			</div>
			<br>
			<div class="col-lg-6 m-auto" id="map" style="width: 700px; height: 700px;"></div>
		</div>
	</section>
	<!-- End Section -->

	<!-- Start Brands -->
	<section class="container py-5">
		<div class="container my-4">
			<div class="row text-center py-3">
				<div class="col-lg-9 m-auto tempaltemo-carousel">
					<div class="row d-flex flex-row">
						<!--Controls-->
						<div class="col-1 align-self-center">
							<a class="h1" href="#templatemo-slide-brand" role="button" data-bs-slide="prev"> <i class="text-light fas fa-chevron-left"></i>
							</a>
						</div>
						<!--End Controls-->

						<!--Carousel Wrapper-->
						<div class="col">
							<div class="carousel slide carousel-multi-item pt-2 pt-md-0" id="templatemo-slide-brand" data-bs-ride="carousel">
								<!--Slides-->
								<div class="carousel-inner product-links-wap" role="listbox">

									<!--First slide-->
									<div class="carousel-item active">
										<div class="row">
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid banner-img" src="assets/img/test/dol.png" alt="banner Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img" src="assets/img/test/rever.png" alt="banner Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img" src="assets/img/test/mark.png" alt="banner Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img" src="assets/img/test/ms.png" alt="banner Logo"></a>
											</div>
										</div>
									</div>
									<!--End First slide-->

									<!--Second slide-->
									<div class="carousel-item">
										<div class="row">
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid banner-img" src="assets/img/test/sony.png" alt="banner Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img" src="assets/img/test/oc.png" alt="banner Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img" src="assets/img/test/bose.png" alt="banner Logo"></a>
											</div>
											<div class="col-3 p-md-5">
												<a href="#"><img class="img-fluid brand-img" src="assets/img/test/senn.png" alt="banner Logo"></a>
											</div>
										</div>
									</div>
									<!--End Second slide-->
								</div>
								<!--End Slides-->
							</div>
						</div>
						<!--End Carousel Wrapper-->

						<!--Controls-->
						<div class="col-1 align-self-center">
							<a class="h1" href="#templatemo-slide-brand" role="button" data-bs-slide="next"> <i class="text-light fas fa-chevron-right"></i>
							</a>
						</div>
						<!--End Controls-->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--End Brands-->


	<!-- Start Footer -->
	<slk:footer />
	<!-- End Footer -->

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	4e0f594d6cf711abc609295ba752a2b2"></script>
	<script src="assets/js/map.js"></script>
	<!-- End Script -->
</body>

</html>