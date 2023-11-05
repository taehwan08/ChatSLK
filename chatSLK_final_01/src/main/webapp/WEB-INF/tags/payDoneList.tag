<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<c:if test="${empty paydatas}">
	<tr>
		<td width=100% colspan="5" style="text-align: center; line-height: 300px; font-size: 30px;"><b>구매한 목록이 없습니다.</b></td>
	</tr>
</c:if>

<c:if test="${not empty paydatas}">
	<c:forEach var="v" items="${paydatas}">
		<tr>
			<td>
			</td>
			<td class="product__cart__item">
				<div class="product__cart__item__pic">
					<img src="assets/productimg/${v.path}" width="100px" height="100px">
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
							<li class="list-inline-item"><span class="badge bg-secondary" id="var-value">${v.payCount}</span></li>
						</ul>
					</div>
				</div>
			</td>
			<fmt:formatNumber value="${v.productPrice * v.payCount}" var="formProductPrice" />
			<td class="cart__price ${v.productNum}">${formProductPrice}&nbsp;원</td>
			<c:if test="${v.status eq 'BEFORE'}">
				<td>
					<div class="product__cart__item__text">
						<span class="badge badge-secondary">배송 전</span>
					</div>
				</td>
				<td></td>
			</c:if>
			<c:if test="${v.status eq 'ING'}">
				<td>
					<div class="product__cart__item__text">
						<span class="badge badge-success">배송 중</span>
					</div>
				</td>
				<td></td>
			</c:if>
			<c:if test="${v.status eq 'DONE'}">
				<td>
					<div class="product__cart__item__text">
						<span class="badge badge-info">배송 완료</span>
					</div>
				</td>
				<c:if test="${v.check eq true}">
				<td>
					<div class="continue__btn">
						<button type="button" class="btn btn-secondary px-3" disabled="disabled">작성완료</button>
					</div>
				</td>
				</c:if>
				<c:if test="${v.check ne true}">
				<td>
					<div class="continue__btn">
						<a href="userReviewPage.do?productNum=${v.productNum}" class="btn btn-success px-3" onclick="openNewWindow(event)">리뷰쓰기</a>
					</div>
				</td>
				</c:if>
			</c:if>
			
		</tr>
	</c:forEach>
</c:if>

<script type="text/javascript">
	// --- [ 팝업 ] ---
	function openNewWindow(event) {
	  event.preventDefault(); // 기본 링크 동작을 막습니다.
	  
	  var linkURL = event.currentTarget.href;
	  var windowName = "_blank"; // 새 창 이름 (기본적으로 "_blank"를 사용하면 새 창이 열립니다.)
	  var windowFeatures = "width=600,height=700,top=200"; // 새 창 크기 및 기타 특성
	  
	  var newWindow = window.open(linkURL, windowName, windowFeatures);
	  if (newWindow === null) {
	    alert("팝업 차단이 설정되어 있을 수 있습니다. 팝업 차단을 해제해주세요.");
	  }
	}
</script>