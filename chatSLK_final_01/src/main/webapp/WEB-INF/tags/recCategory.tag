<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="v" items="${cddatas}">
	<div class="col-12 col-md-4 p-5 mt-3">
		<a href="shopPage.do?categoryDetailNum=${v.categoryDetailNum}"><img src="./assets/img/CATEGORYDETAIL${v.categoryDetailNum}.jpeg" class="rounded-circle img-fluid border"></a>
		<h5 class="text-center mt-3 mb-3">${v.categoryDetailName}</h5>
		<p class="text-center">
			<a class="btn btn-success" href="shopPage.do?categoryDetailNum=${v.categoryDetailNum}">바로가기</a>
		</p>
		<!-- Go Shop 누르면 바로 해당 카테고리 연결되게끔 -->
	</div>
</c:forEach>
