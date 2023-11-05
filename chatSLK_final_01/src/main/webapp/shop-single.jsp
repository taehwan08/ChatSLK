<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>${pdata.productName}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- <link rel="stylesheet" href="assets/css/adminstyle.css"> -->

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico">

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/templatemo.css">
<link rel="stylesheet" href="assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="assets/css/slick.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/slick-theme.css">
<!--
    
TemplateMo 559 Zay Shop

https://templatemo.com/tm-559-zay-shop

-->
<style>
#heartbtn.fas.fa-heart {
	color: red;
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

	<!-- Open Content -->
	<section class="bg-light" style="background-color: #eeeeee !important;">
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
		<div class="container pb-5">
			<div class="row">
				<div class="col-lg-5 mt-5">
					<!--  <div class="card mb-3">
                        <img class="card-img img-fluid" src="assets/img/product_single_10.jpg" alt="Card image cap" id="product-detail">
                    </div> -->
					<div class="row">
						<!--Start Controls-->
						<div class="col-1 align-self-center">
							<a href="#multi-item-example" role="button" data-bs-slide="prev"> <i class="text-dark fas fa-chevron-left"></i> <span class="sr-only">Previous</span>
							</a>
						</div>
						<!--End Controls-->
						<!--Start Carousel Wrapper-->
						<div id="multi-item-example" class="col-10 carousel slide carousel-multi-item" data-bs-ride="carousel">
							<!--Start Slides-->
							<div class="carousel-inner product-links-wap" role="listbox">
								<!--First slide-->
								<div class="carousel-item active">
									<div class="row">
										<div class="mb-3">
											<a href="${pdata.path}"><img class="card-img img-fluid" src="assets/productimg/${pdata.path}" alt="Product Image"></a>
										</div>
									</div>
								</div>
								<!--First slide-->
								<!--Second slide-->
								<c:forEach var="i" begin="1" end="${fn:length(idatas)-1}">
									<div class="carousel-item">
										<div class="row">
											<div class="mb-3">
												<a href="${idatas[i].path}"><img class="card-img img-fluid" src="assets/productimg/${idatas[i].path}" alt="Product Images"> </a>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<!--End Slides-->
						</div>
						<div class="col-1 align-self-center">
							<a href="#multi-item-example" role="button" data-bs-slide="next"> <i class="text-dark fas fa-chevron-right"></i> <span class="sr-only">Next</span>
							</a>
						</div>
						<!--End Controls-->
					</div>
				</div>
				<!--End Carousel Wrapper-->
				<!--Start Controls-->

				<!-- col end -->
				<div class="col-lg-7 mt-5">
					<div class="card">
						<div class="card-body">
							<h1 class="h3">${pdata.productName}</h1>
							<div class="list-unstyled d-flex justify-content-between mb-2">
								<h6 class="h6 mt-3">
									<fmt:formatNumber value="${pdata.productPrice}" />
									&nbsp;원
								</h6>
								<ul class="list-inline justify-content-between text-right">
									<li class="productstar${pdata.star}"><c:if test="${empty memberId}">
											<a class="btn " href="error.do"> <i id="heartbtn" class="far fa-heart"></i></a>
										</c:if> <c:if test="${not empty memberId}">
											<c:if test="${pdata.check eq false}">
												<a id="wishbtn" class="btn ${pdata.productNum}"> <i id="heartbtn" class="far fa-heart"></i>
												</a>
											</c:if>
											<c:if test="${pdata.check eq true}">
												<a id="wishbtn" class="btn ${pdata.productNum}"> <i id="heartbtn" class="fas fa-heart"></i>
												</a>
											</c:if>
										</c:if></li>
								</ul>
							</div>

							<ul class="list-inline">
								<li class="list-inline-item">
									<h6>Brand</h6>
								</li>
								<li class="list-inline-item">
									<p class="text-dark">
										<strong>${pdata.company}</strong>
									</p>
								</li>
							</ul>

							<ul class="list-inline">
								<li class="list-inline-item">
									<h6>Description</h6>
								</li>
								<li class="list-inline-item">
									<p class="text-dark">
										<strong>${pdata.productExplain}</strong>
									</p>
								</li>
							</ul>

							<form action="addCart.do" method="post">
								<input type="hidden" name="productNum" value="${pdata.productNum}">
								<div class="row">
									<div class="col-auto">
										<ul class="list-inline pb-3">
											<li class="list-inline-item text-right">
												<h6>Quantity</h6> <input type="hidden" name="productCnt" id="productCnt" value="${pdata.productCnt}"> <input type="hidden" name="cartCount" id="product-quanity" value="1">
											</li>
											<li class="list-inline-item"><span class="btn btn-success" id="btn-minus">-</span></li>
											<li class="list-inline-item"><span class="badge bg-secondary" id="var-value">1</span></li>
											<li class="list-inline-item"><span class="btn btn-success" id="btn-plus">+</span></li>
										</ul>
									</div>
								</div>
								<c:if test="${empty memberId}">
									<div class="row pb-3">
										<!--<div class="col d-grid">
											<a href="loginPage.do" class="btn btn-success btn-lg px-3">Buy</a>
										</div> -->
										<div class="col d-grid">
											<a href="error.do" class="btn btn-success btn-lg px-3">Add to Cart</a>
										</div>
									</div>
								</c:if>
								<c:if test="${not empty memberId}">
									<div class="row pb-3">
										<div class="col d-grid">
											<button type="submit" class="submit btn btn-success btn-lg px-3">Buy</button>
										</div>
										<div class="col d-grid">
											<a href="#!" id="cartbtn" class="btn btn-success btn-lg px-3 ${pdata.productNum}">Add to Cart</a>
										</div>
									</div>
								</c:if>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Close Content -->

	<!-- Start 리뷰 -->
	<c:if test="${not empty rdatas}">
		<section class="py-5">
			<div class="container">
				<div class="row text-left p-2 pb-3">
					<h4>상품 후기</h4>
				</div>
				<!--Start Carousel Wrapper-->
				<div class="review__table">
					<table class="table">
						<thead class="table-dark">
							<tr>
								<th>평점</th>
								<th>제목</th>
								<th>내용</th>
								<th>작성시간</th>
								<th>작성자</th>
							</tr>
						</thead>
						<tbody style="vertical-align: middle;">
							<c:forEach var="v" items="${rdatas}">
								<tr>
									<td>
										<ul class="list-unstyled">
											<li class="reviewstar${v.star}"></li>
										</ul> <!-- 별점 -->
									</td>
									<td><h6>${v.title}</h6></td>
									<td><h6>${v.content}</h6></td>
									<td><h6>${v.time}</h6></td>
									<td><h6>${v.memberId}</h6></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</c:if>
	<!-- End 리뷰 -->
	<!-- Start 추천상품 -->
	<section class="py-5">
		<div class="container">
			<div class="row text-left p-2 pb-3">
				<h4>추천 상품</h4>
			</div>
			<!--Start Carousel Wrapper-->
			<div class="row">
				<slk:product />
			</div>
		</div>
		<hr>
	</section>
	<!-- End 추천상품 -->


	<slk:footer />

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- End Script -->

	<!-- Start Slider Script -->
	<script src="assets/js/slick.min.js"></script>
	<script>
	$(document).ready(function() {
	    // 초기 값 설정
	    var productCnt = parseInt($("#productCnt").val()); // 상품 수량 제한
	    var cartCountInput = $("#product-quanity");
	    var varValueSpan = $("#var-value");

	    // "+" 버튼 클릭 이벤트 핸들러
	    $("#btn-plus").click(function() {
	        var currentCount = parseInt(cartCountInput.val());
	        
	        // 현재 수량이 상품 수량 제한 이하인 경우만 증가
	        if (currentCount > productCnt) {
	            currentCount--;
	            cartCountInput.val(currentCount);
	            varValueSpan.text(currentCount);
	        }
	    });
	});   
	
        $("#cartbtn").on("click",function(){
        	var a = $("#var-value").text();
        	
        	console.log(a);
        	
        	$.ajax({
        	      url: 'addCartlist.do?productNum='+${pdata.productNum}+'&cartCount='+a,
        	      type:'POST',
        	      success:function(result){
        	         console.log(result);
        	         if(result=='insert'||result=='update'){
        	            console.log('장바구니 추가 성공');
        	         }else{
        	            console.log('장바구니 추가 실패');
        	         }
        	      },
        	      error:function(error){
        	         console.log(error);
        	      }
        	   });
        });
        
        $("#wishbtn").on("click",function(){
        	   $.ajax({
        	      url: 'addWishlist.do?productNum='+${pdata.productNum},
        	      type:'POST',
        	      success:function(result){
        	         console.log(result);
        	         if(result=='insert'){
        	            console.log('위시리스트 추가 성공');
        	            $('.btn.'+${pdata.productNum}+' > i').attr('class', 'fas fa-heart');
        	         }else{
        	            console.log('위시리스트 삭제 성공');
        	            $('.btn.'+${pdata.productNum}+' > i').attr('class', 'far fa-heart');
        	         }
        	      },
        	      error:function(error){
        	         console.log(error);
        	      }
        	   });
        	});
        
    	//페이지 로드 후 실행
    	document.addEventListener("DOMContentLoaded", function() {
    	    // 상품 별점 평균에 대한 처리
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
    	
    	document.addEventListener("DOMContentLoaded", function() {
    	    // 각 리뷰 별점에 대한 처리
    	    const productStars = document.querySelectorAll('[class^="reviewstar"]');
    	    productStars.forEach(starContainer => {
    	        const starCount = parseInt(starContainer.classList[0].replace("reviewstar", ""));
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
    </script>
	<!-- End Slider Script -->

</body>

</html>