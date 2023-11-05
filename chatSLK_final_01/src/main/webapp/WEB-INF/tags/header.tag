<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar navbar-expand-lg navbar-light shadow">
	<div class="container d-flex justify-content-between align-items-center">

		<a class="navbar-brand text-success logo h1 align-self-center" href="main.do"> SoundPlay <!--  로고명 -->
		</a>

		<button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="templatemo_main_nav">
			<div class="flex-fill">
				<ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
					<li class="nav-item"><a class="nav-link" href="main.do">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="shopPage.do">Shop</a></li>
					<li class="nav-item"><a class="nav-link" href="aboutPage.do">About</a></li>
				</ul>
			</div>
			<div class="navbar align-self-center d-flex">
				<div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
					<div class="input-group">
						<input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">
						<div class="input-group-text">
							<i class="fa fa-fw fa-search"></i>
						</div>
					</div>
				</div>
				<a class="nav-icon d-none d-lg-inline" href="#" data-bs-toggle="modal" data-bs-target="#templatemo_search"> <i class="fa fa-fw fa-search text-dark mr-2"></i>
				</a>

				<c:if test="${empty memberId}">
					<a class="nav-icon position-relative text-decoration-none" href="error.do"> <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
					</a>
					<a class="nav-icon position-relative text-decoration-none" href="loginPage.do"> <i class="fa fa-fw fa-user text-light mr-3"></i>
					</a>
				</c:if>
				<c:if test="${not empty memberId}">
					<a class="nav-icon position-relative text-decoration-none" href="cartPage.do">
					<i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i> <!-- <span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">2</span> --> <!-- 요기 장바구니 담긴 갯수 -->
					</a>
					<a class="nav-icon position-relative text-decoration-none" href="mypagePage.do"> <i class="fa fa-fw fa-user text-dark mr-3"></i> <!-- <span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-dark text-light">2</span> 요기 주문완료한 갯수 -->
					</a>
				</c:if>
			</div>
		</div>

	</div>
</nav>