<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>



<!-- <div class="col-md-4">
		<div class="card mb-4 product-wap rounded-0">
			<div class="card rounded-0"> -->

<c:forEach var="pdatas" items="${pdatas}">
	<div class="col-md-3">
		<div class="card mb-3 product-wap rounded-0">
			<div class="card rounded-0">
				<img class="card-img rounded-0 img-fluid" src="assets/productimg/${pdatas.path}">
				<div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
					<ul class="list-unstyled">
						<c:if test="${empty memberId}">
							<li><a class="btn btn-success text-white mt-2" href="error.do"><i class="far fa-heart"></i></a></li>
							<li><a class="btn btn-success text-white mt-2" href="shopSinglePage.do?productNum=${pdatas.productNum}"><i class="far fa-eye"></i></a></li>
							<li><a class="btn btn-success text-white mt-2" href="error.do"><i class="fas fa-cart-plus"></i></a></li>
						</c:if>
						<c:if test="${not empty memberId}">
							<c:if test="${pdatas.check eq true}">
								<li class="addwishlist"><a class="btn btn-success text-white mt-2 ${pdatas.productNum}"><i class="fas fa-heart"></i></a></li>
							</c:if>
							<c:if test="${empty pdatas.check or pdatas.check ne true}">
								<li class="addwishlist"><a class="btn btn-success text-white mt-2 ${pdatas.productNum}"><i class="far fa-heart"></i></a></li>
							</c:if>
							<li><a class="btn btn-success text-white mt-2" href="shopSinglePage.do?productNum=${pdatas.productNum}"> <i class="far fa-eye"></i></a></li>
							<!-- 장바구니에 1개 바로 추가 -->
							<li class="addcartlist"><a class="btn btn-success text-white mt-2 ${pdatas.productNum}"> <i class="fas fa-cart-plus"></i>
							</a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<div class="card-body">
				<a href="shopSinglePage.do?productNum=${pdatas.productNum}" class="h3 text-decoration-none">${pdatas.productName}</a>
				<ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
					<li>${pdatas.company}</li>
				</ul>
				<p class="text-left mb-0">
					<fmt:formatNumber value="${pdatas.productPrice}" />
					&nbsp;원
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(".addwishlist > a.btn.btn-success.text-white.mt-2."+${pdatas.productNum}).on("click",function(){
	   $.ajax({
	      url: 'addWishlist.do?productNum='+${pdatas.productNum},
	      type:'POST',
	      success:function(result){
	         console.log(result);
	         if(result=='insert'){
	            console.log('위시리스트 추가 성공');
	            $('.addwishlist > a.btn.btn-success.text-white.mt-2.'+${pdatas.productNum}+' > i').attr('class', 'fas fa-heart');
	         }else{
	            console.log('위시리스트 삭제 성공');
	            $('.addwishlist > a.btn.btn-success.text-white.mt-2.'+${pdatas.productNum}+' > i').attr('class', 'far fa-heart');
	         }
	      },
	      error:function(error){
	         console.log(error);
	      }
	   });
	});
	
	$(".addcartlist > a.btn.btn-success.text-white.mt-2."+${pdatas.productNum}).on("click",function(){
	   $.ajax({
	      url: 'addCartlist.do?productNum='+${pdatas.productNum}+'&cartCount=1',
	      type:'POST',
	      success:function(result){
	         console.log(result);
	         if(result=='insert'){
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
	</script>
</c:forEach>