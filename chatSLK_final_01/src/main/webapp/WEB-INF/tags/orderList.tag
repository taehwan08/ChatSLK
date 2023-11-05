<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<!-- Shopping 주문내역 제품 싱글  -->
<c:if test="${empty cdatas}">
	<tr>
		<td width=100% colspan="5" style="text-align: center; line-height: 300px; font-size: 30px;"><b>구매한 목록이 없습니다.</b></td>
	</tr>
</c:if>

<c:if test="${not empty cdatas}">
	<c:forEach var="v" items="${cdatas}">
		<tr>
			<td class="product__cart__item">
				<div class="product__cart__item__pic">
					<img src="${v.path}" width="100px" height="100px">

				</div>
			</td>
			<td class="product__cart__item">
				<div class="product__cart__item__text">
					<p class="text-light">
						<a href="shopSinglePage.do?productNum=${v.productNum}" style="color: #212529 !important; text-decoration: none !important;">${v.company}</a>
					</p>
					<p class="text-light">
						<a href="shopSinglePage.do?productNum=${v.productNum}" style="color: #212529 !important; text-decoration: none !important;">${v.productName}</a>
					</p>
				</div>
			</td>
			<td class="quantity__item">
				<div class="quantity">
					<div class="col-auto">
						<ul class="list-inline pb-3">
							<li class="list-inline-item text-right"><input type="hidden" name="product-quanity" id="product-quanity" value="1"></li>
							<li class="list-inline-item"><span class="badge bg-secondary" id="var-value">${v.cartCount}</span></li>
						</ul>
					</div>
				</div>
			</td>
			<fmt:formatNumber value="${v.productPrice * v.cartCount}" var="formProductPrice" />
			<td class="cart__price ${v.productNum}">${formProductPrice}&nbsp;원</td>
		</tr>
	</c:forEach>
</c:if>
