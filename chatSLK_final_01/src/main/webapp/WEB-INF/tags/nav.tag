<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block" id="templatemo_nav_top">
	<div class="container text-light">
		<div class="w-100 d-flex justify-content-between">
			<div>
				<i class="fa fa-envelope mx-2"></i> <a class="navbar-sm-brand text-light text-decoration-none" href="mailto:taehwan08@naver.com">taehwan08@naver.com</a> <i class="fa fa-phone mx-2"></i> <a class="navbar-sm-brand text-light text-decoration-none" href="tel:010-3179-4058">010-3179-4058</a>
			</div>
		</div>
		<div class="w-100 d-flex justify-content-end">
			<div>
				<c:if test="${empty memberId}">
					<a class="navbar-sm-brand text-light text-decoration-none" href="loginPage.do"><i class="fa fa-user-circle mx-2"></i>로그인 후 사용가능 합니다.</a>
				</c:if>
				<c:if test="${not empty memberId}">
					<a class="navbar-sm-brand text-light text-decoration-none" href="logout.do"><i class="fa fa-user-circle mx-2"></i>${memberId} 님, 로그아웃</a>
					<c:if test="${not empty role}">
						<a class="navbar-sm-brand text-light text-decoration-none" href="adminMain.do"> | 관리자 페이지</a>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
</nav>