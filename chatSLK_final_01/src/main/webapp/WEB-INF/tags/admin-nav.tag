<%@ tag language="java" pageEncoding="UTF-8"%>

<nav class="pcoded-navbar  ">
	<div class="navbar-wrapper  ">
		<div class="navbar-content scroll-div ">

			<div class="">
				<div class="main-menu-header">
					<img class="img-radius" src="assets/images/user/avatar-default.png" alt="User-Profile-Image">
					<div class="user-details">
						<span>${memberName}</span>
						<div id="more-details">${role}<i class="fa fa-chevron-down m-l-5"></i>
						</div>
					</div>
				</div>
				<div class="collapse" id="nav-user-link">
					<ul class="list-unstyled">
						<li class="list-group-item"><a href="logout.do"><i class="feather icon-log-out m-r-5"></i>Logout</a></li>
					</ul>
				</div>
			</div>

			<ul class="nav pcoded-inner-navbar ">
				<li class="nav-item pcoded-menu-caption"><label>요약</label></li>
				<li class="nav-item"><a href="adminMain.do" class="nav-link "><span class="pcoded-micon"><i class="feather icon-home"></i></span><span class="pcoded-mtext">대시보드</span></a></li>
				<li class="nav-item pcoded-menu-caption"><label>상품</label></li>
				<li class="nav-item pcoded-hasmenu"><a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-box"></i></span><span class="pcoded-mtext">제품 관리</span></a>
					<ul class="pcoded-submenu">
						<li><a href="productListPage.do">제품 목록</a></li>
						<li><a href="addProductPage.do">제품 등록</a></li>
						<li><a href="categoryListPage.do">카테고리 관리</a></li>
						<li><a href="addImageDataPage.do">더미데이터 사진 추가</a></li>
					</ul></li>
				<li class="nav-item pcoded-menu-caption"><label>주문</label></li>
				<li class="nav-item"><a href="orderListPage.do" class="nav-link "><span class="pcoded-micon"><i class="feather icon-pie-chart"></i></span><span class="pcoded-mtext">주문 내역</span></a></li>
				<li class="nav-item pcoded-hasmenu"><a href="#!" class="nav-link "><span class="pcoded-micon"><i class="feather icon-edit"></i></span><span class="pcoded-mtext">리뷰 관리</span></a>
					<ul class="pcoded-submenu">
						<li><a href="reviewPage.do">리뷰 내역</a></li>
						<li><a href="addreviewPage.do">리뷰 등록</a></li>
					</ul></li>
				<li class="nav-item pcoded-menu-caption"><label>회원</label></li>
				<li class="nav-item"><a href="memberListPage.do" class="nav-link "><span class="pcoded-micon"><i class="feather icon-user"></i></span><span class="pcoded-mtext">회원 관리</span></a></li>
			</ul>

		</div>
	</div>
</nav>
<!-- [ Header ] start -->
<header class="navbar pcoded-header navbar-expand-lg navbar-light header-dark">
	<div class="m-header">
		<a class="mobile-menu" id="mobile-collapse" href="#!"><span></span></a>
		<a href="adminMain.do" class="b-brand">
			<img class="wid-20" src="assets/images/logo-no-background.png" alt="" class="logo">
			<span class="m-l-10">&nbsp;SoundPlay</span>
		</a> <a href="#!" class="mob-toggler"> <i class="feather icon-more-vertical"></i>
		</a>
	</div>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a href="#!" class="pop-search"><i class="feather icon-search"></i></a>
				<div class="search-bar">
					<input type="text" class="form-control border-0 shadow-none" placeholder="Search hear">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div></li>
		</ul>
	</div>
</header>
<!-- [ Header ] end -->