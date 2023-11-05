<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<c:forEach var="v" items="${paydatas}">
	<tr>
		<td class="product__cart__item">
			<div class="product__cart__item__pic">
				<img src="assets/productimg/${v.path}" width="100px" height="100px">

			</div>
		</td>
		<td class="product__cart__item">
			<div class="product__cart__item__text">
				<h6>${v.company}</h6>
				<h6>${v.productName}</h6>
			</div>
		</td>
		<td class="quantity__item">
			<div class="quantity">
				<div class="col-auto">
					<ul class="list-inline pb-3">
						<li class="list-inline-item text-right"><input type="hidden" name="product-quanity" id="product-quanity" value="1"></li>
						<li class="list-inline-item"><span class="badge bg-secondary" id="var-value">${v.payCount}</span></li>
					</ul>
				</div>
			</div>
		</td>
		<fmt:formatNumber value="${v.productPrice * v.payCount}" var="formProductPrice" />
		<td class="cart__price ${v.productNum}">${formProductPrice}&nbsp;원</td>
	</tr>
</c:forEach>
