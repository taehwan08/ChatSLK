<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="v" items="${pdatas}">
	<div class="col-12 col-md-4 mb-4">
		<div class="card h-100">
			<a href="shopSinglePage.do?productNum=${v.productNum}"> <!-- 썸네일 수정 필요 --> <img src="assets/productimg/${v.path}" class="card-img-top" alt="product(${v.productNum})">
			</a>
			<div class="card-body">
				<ul class="list-unstyled d-flex justify-content-between">
					<li class="productstar${v.star}">
					</li>
				</ul>
				<ul class="list-unstyled d-flex justify-content-between">
					<li class="text-muted text-right">${v.company}</li>
				</ul>
				<a href="shopSinglePage.do?productNum=${v.productNum}" class="h2 text-decoration-none text-dark" style="font-size: 20px !important;">${v.productName}</a>
				<p class="card-text mt-1" style="font-size: 16px !important;">${v.productExplain}</p>
				<!--리뷰 <p class="text-muted">Reviews (48)</p> -->
			</div>
		</div>
	</div>
</c:forEach>