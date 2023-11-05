<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SoundPlay | 리뷰작성</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico" />
<!-- Google API를 사용하기 위한 스크립트 -->

<link rel="stylesheet" href="assets/css/login.css" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/templatemo.css" />
<link rel="stylesheet" href="assets/css/custom.css" />

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />

<style>
        /* 라디오 버튼 숨기기 */
        input[type="radio"] {
            display: none;
        }

        /* 별 아이콘 스타일 */
        .star-icon {
            font-size: 24px; /* 아이콘 크기 조절 */
            color: #808080; /* 초기 상태는 회색 별로 설정 */
        }

        /* 라디오 버튼이 선택된 경우 선택한 별과 그 이전 별을 노란색으로 설정 */
        .star-container {
            display: inline-block;
        }

        .star-container input[type="radio"]:checked ~ .fa-star {
            color: #FFD700; /* 노란색으로 설정 */
        }
    </style>

</head>

<body>
	<div class="container py-5">
		<div class="row py-5">
			<div class="col-lg-7 mt-5">
				<div class="card">
					<div class="card-body">
						<form action="addUserReview.do" method="post">
							<ul class="list-inline">
								<li class="list-inline-item">
								<img class="wid-100 align-top m-r-15" src="assets/productimg/${pdata.path}" alt="product img">
								</li>
								<li class="list-inline-item">
									<h6 class="h6">${pdata.productName}| ${pdata.company}</h6>
								</li>
							</ul>
								<div class="star-container">
							        <input type="radio" id="star1" name="star" value="1">
							        <label for="star1"><i class="fa fa-star star-icon"></i></label>
							    </div>
							    <div class="star-container">
							        <input type="radio" id="star2" name="star" value="2">
							        <label for="star2"><i class="fa fa-star star-icon"></i></label>
							    </div>
							    <div class="star-container">
							        <input type="radio" id="star3" name="star" value="3">
							        <label for="star3"><i class="fa fa-star star-icon"></i></label>
							    </div>
							    <div class="star-container">
							        <input type="radio" id="star4" name="star" value="4">
							        <label for="star4"><i class="fa fa-star star-icon"></i></label>
							    </div>
							    <div class="star-container">
							        <input type="radio" id="star5" name="star" value="5" checked>
							        <label for="star5"><i class="fa fa-star star-icon"></i></label>
							    </div>
							<ul class="list-unstyled">
								<li><label for="title" class="mb-10">제목</label>
								<input type="text" class="form-control m-b-20" id="title" name="title"></li>
								<li><label for="review" class="mb-10">내용</label>
								<textarea class="form-control m-b-20" id="review" name="content" placeholder="리뷰작성" rows="5"></textarea></li>
							</ul>
							<div class="row pb-3">
								<div class="col d-grid">
									<input type="hidden" name="productNum" value="${pdata.productNum}" />
									<input type="hidden" name="memberId" value="${memberId}" />
									<button type="submit" class="btn btn-success btn-lg px-3">작성하기</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<slk:footer />

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/javascript.js"></script>
    
	 <script>
        const starContainers = document.querySelectorAll('.star-container');

        starContainers.forEach((container, index) => {
            const radioInput = container.querySelector('input[type="radio"]');

            radioInput.addEventListener('change', () => {
                for (let i = 0; i <= index; i++) {
                    const starIcon = starContainers[i].querySelector('.fa-star');
                    starIcon.style.color = '#FFD700'; // 노란색으로 변경
                }

                for (let i = index + 1; i < starContainers.length; i++) {
                    const starIcon = starContainers[i].querySelector('.fa-star');
                    starIcon.style.color = '#808080'; // 회색으로 변경
                }
            });
        });
    </script>
	<!-- End Script -->
</body>
</html>
