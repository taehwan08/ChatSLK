<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SoundPlay | 상품목록</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<style>
div[id*="card"]{
display:none;
}
</style>
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


	<!-- Start Content -->
	<c:if test="${not empty recentProductList}">
		<div class="quickmenu d-flex text-right" style="position: absolute; width: 100px; flex-direction: column; top: 250px; right: 50px; color: #fff;">
			<ul class="list-unstyled text-center">
				<li>
					<p class="text-dark">
						<strong>최근 본 상품</strong>
					</p>
				</li>
			</ul>
			<c:forEach var="v" items="${recentProductList}">
				<div class="card mb-2 product-wap rounded-0">
					<div class="card mb-0 rounded-0">
						<a href="shopSinglePage.do?productNum=${v.productNum}"> <img class="card-img rounded-0 img-fluid w-100" alt="recentProduct${v.productNum}" src="assets/productimg/${v.path}">
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<div class="container py-5">
		<div class="row">
			<div class="col-lg-3">
				<h1 class="h2 pb-4">Categories</h1>
				<ul class="list-unstyled templatemo-accordion">
					<slk:category />
				</ul>
			</div>

			<div class="col-lg-9">
				<!-- 상품리스트 -->
				<div class="row">
					<slk:productList />
				</div>
				<!-- 페이지네이션 하기 -->
				<div div="row">
					<button type="button" class="btn btn-success btn-lg px-3" id="loadMoreButton">더 보기</button>
				</div>
			</div>

		</div>
	</div>
	<!-- End Content -->


	<slk:footer />

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
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
	
	
	$(document).ready(function(){
		  var currentPosition = parseInt($(".quickmenu").css("top"));
		  $(window).scroll(function() {
		    var position = $(window).scrollTop(); 
		    $(".quickmenu").stop().animate({"top":position+currentPosition+"px"},1000);
		  });
		});
	
	document.addEventListener("DOMContentLoaded", function () {
		  // 초기에 보여줄 카드의 개수
		  var initialDisplayCount = 9;
		  
		  var cards = document.querySelectorAll(".col-md-4.productlist");
		  var loadMoreButton = document.getElementById("loadMoreButton");
		  var currentIndex = 0;

		  function showNextCards() {
		    for (var i = currentIndex; i < currentIndex + initialDisplayCount; i++) {
		      if (cards[i]) {
		        cards[i].style.display = "block";
		      }
		    }
		    currentIndex += initialDisplayCount;

		    // 모든 카드가 표시되면 더 보기 버튼 숨기기
		    if (currentIndex >= cards.length) {
		      loadMoreButton.style.display = "none";
		    }
		  }

		  loadMoreButton.addEventListener("click", showNextCards);

		  // 초기에 처음 일부 카드를 표시
		  showNextCards();
		});
	
	
	
	</script>


	<!-- End Script -->
</body>

</html>