<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="kr">

<head>
<title>SoundPlay - 리뷰 내역</title>
<!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->
<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="">
<meta name="author" content="Phoenixcoded" />
<!-- Favicon icon -->
<link rel="icon" href="assets/img/logo-no-background.ico" type="image/x-icon">
<!-- vendor css -->
<link rel="stylesheet" href="assets/css/adminstyle.css">




</head>
<body class="">
	<!-- [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<!-- [ Pre-loader ] End -->
	<!-- [ navigation menu ] start -->
	<slk:admin-nav />
	<!-- [ navigation menu ] end -->

	<!-- [ Main Content ] start -->
	<div class="pcoded-main-container">
		<div class="pcoded-content">
			<!-- [ breadcrumb ] start -->
			<div class="page-header">
				<div class="page-block">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="page-header-title">
								<h5 class="m-b-10">리뷰 관리</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- Latest Customers start -->
				<div class="col-lg-12 col-md-12">
					<div class="card table-card review-card">
						<div class="card-header borderless ">
							<h5>리뷰 내역</h5>
						</div>
						<div class="card-body pb-0">
							<c:forEach var="v" items="${rdatas}">
								<div class="review-block">
									<div class="row">
										<div class="col-sm-auto p-r-0">
											<img src="assets/images/user/avatar-2.jpg" alt="product image" class="wid-50 m-b-15">
										</div>
										<div class="col">
											<h6 class="m-b-15">상품명 : ${v.productName}<span class="float-right f-13 text-muted">${v.time}</span></h6>
											<div class="reviewstar${v.star}"></div>
											<h6 class="m-t-15 m-b-15">제목 : ${v.title}</h6>
											<p class="m-t-15 m-b-15 text-muted">내용 : ${v.content}</p>
											<c:if test="${empty v.reply}">
												<button type="button" class="btn  btn-primary btn-sm ${v.reviewNum}" data-toggle="modal" data-target="#reviewmodal${v.reviewNum}">답글 달기</button>
											</c:if>
											<c:if test="${not empty v.reply}">
												<p class="m-t-15 m-b-15 m-l-15 text-muted">답글 : ${v.reply}</p>
												<button type="button" class="btn  btn-outline-primary btn-sm ${v.reviewNum}" data-toggle="modal" data-target="#replymodal${v.reviewNum}">답글 수정</button>
											</c:if>
										</div>
									</div>
								</div>
								<c:if test="${empty v.reply}">
									<div id="reviewmodal${v.reviewNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLiveLabel">답글 작성</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">×</span>
													</button>
												</div>
												<form action="updateReply.do">
													<div class="modal-body">
														<div class="form-group">
															<input type="hidden" name="reviewNum" value="${v.reviewNum}"> <label for="productexplainTextarea">${v.title}</label>
															<textarea name="reply" class="form-control" id="productexplainTextarea" rows="3" placeholder="답글을 작성해주세요."></textarea>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
														<button type="submit" class="btn  btn-primary">작성</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${not empty v.reply}">
									<div id="replymodal${v.reviewNum}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLiveLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLiveLabel">답글 수정</h5>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">×</span>
													</button>
												</div>
												<form action="updateReply.do">
													<div class="modal-body">
														<div class="form-group">
															<input type="hidden" name="reviewNum" value="${v.reviewNum}"> <label for="productexplainTextarea">${v.title}</label>
															<textarea name="reply" class="form-control" id="productexplainTextarea" rows="3" placeholder="답글을 작성해주세요.">${v.reply}</textarea>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn  btn-secondary" data-dismiss="modal">닫기</button>
														<button type="submit" class="btn  btn-primary">수정</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<!-- Latest Customers end -->
				</div>
				<!-- [ Main Content ] end -->
			</div>
		</div>
	</div>
	<!-- [ Main Content ] end -->

	<!-- Required Js -->
	<script src="assets/js/vendor-all.min.js"></script>
	<script src="assets/js/plugins/bootstrap.min.js"></script>
	<script src="assets/js/pcoded.min.js"></script>
	<script>
    // 페이지 로드 후 실행
    document.addEventListener("DOMContentLoaded", function() {
        // 각 별점에 대한 처리
        const reviewStars = document.querySelectorAll('[class^="reviewstar"]');
        reviewStars.forEach(starContainer => {
            const starCount = parseInt(starContainer.classList[0].replace("reviewstar", ""));
            for (let i = 0; i < 5; i++) {
                const starIcon = document.createElement("i");
                starIcon.classList.add("feather", "icon-star-on", "f-18", "text-c-yellow");
                if (i >= starCount) {
                    starIcon.classList.remove("icon-star-on");
                    starIcon.classList.add("icon-star", "f-18", "text-muted");
                }
                starContainer.appendChild(document.createTextNode("\n")); // 줄바꿈
                starContainer.appendChild(starIcon);
            }
        });
    });
</script>

	<!-- Apex Chart -->
	<script src="assets/js/plugins/apexcharts.min.js"></script>


	<!-- custom-chart js -->
	<script src="assets/js/pages/dashboard-main.js"></script>
</body>

</html>
