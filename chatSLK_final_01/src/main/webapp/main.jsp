<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SoundPlay - 사운드플레이, 스피커의 모든 것</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- <link rel="stylesheet" href="assets/css/swiper.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
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



	<!-- Start Banner Hero -->
	<!-- 여기에 slick slider 들어가면 됩니다 -->
	<section class="container py-5">
		<div class="swiper mySwiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img alt="이미지1" src="./assets/img/test/contrast-Standard.jpg" width=100%>
				</div>
				<div class="swiper-slide">
					<img alt="이미지2" src="./assets/img/test/kj6h5rg.jpg" width=100%>
				</div>
				<div class="swiper-slide">
					<img alt="이미지3" src="./assets/img/test/cms105.jpg" width=100%>
				</div>

			</div>
		</div>
		<div class="swiper-button-prev" style="color: #eeeeee !important; padding-left: 50px;"></div>
		<div class="swiper-button-next" style="color: #eeeeee !important; padding-right: 50px;"></div>
	</section>


	<!-- Start Categories of The Month -->
	<section class="container py-5">
		<div class="row text-center pt-3">
			<div class="col-lg-6 m-auto">
				<h1 class="h1">추천 카테고리</h1>
				<p>&nbsp;</p>
			</div>
		</div>
		<div class="row">
			<slk:recCategory />
		</div>
	</section>
	<!-- End Categories of The Month -->


	<!-- Start Featured Product -->
	<section class="bg-light" style="background-color: #eeeeee !important;">
		<div class="container py-5">
			<div class="row text-center py-3">
				<div class="col-lg-6 m-auto">
					<h1 class="h1">추천 상품</h1>
					<p>&nbsp;</p>
				</div>
			</div>
			<div class="row">
				<slk:recProduct />
			</div>
		</div>
	</section>
	<!-- End Featured Product -->


	<slk:footer />

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
	<script src="assets/js/swiper.js"></script>
	<script>
	//페이지 로드 후 실행
	document.addEventListener("DOMContentLoaded", function() {
	    // 각 별점에 대한 처리
	    const productStars = document.querySelectorAll('[class^="productstar"]');
	    productStars.forEach(starContainer => {
	        const starCount = parseInt(starContainer.classList[0].replace("productstar", ""));
	        for (let i = 0; i < 5; i++) {
	            const starIcon = document.createElement("i");
	            starIcon.classList.add("text-warning", "fa", "fa-star");
	            if (i >= starCount) {
	                starIcon.classList.remove("text-warning");
	                starIcon.classList.add("text-muted", "fa", "fa-star");
	            }
	            starContainer.appendChild(document.createTextNode("\n")); // 줄바꿈
	            starContainer.appendChild(starIcon);
	        }
	    });
	});
	</script>
	<!-- End Script -->
</body>

</html>