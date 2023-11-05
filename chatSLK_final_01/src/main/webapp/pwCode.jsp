<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SoundPlay | 비밀번호찾기</title>
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



</head>

<body>
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<h2 class="h2 text-white">인증번호 입력</h2>
		</div>
	</div>
	<div class="container py-5">
		<div class="row py-5">
			<form action="pwsearchDonePage.do" class="col-md-9 m-auto" method="post" role="form">
				<div class="row">
					<div class="form-group col-md-6 mb-3">
						<label for="inputname">인증번호</label>
						<input type="text" class="form-control mt-1" name="memberId" value="${mdata.memberId}">
						<input type="text" class="form-control mt-1" id="code" value="${code}">
						<input type="text" class="form-control mt-1" id="checkcode" name="code" placeholder="인증번호">
					</div>
				</div>
				<div class="row">
					<div class="col text-end mt-2">
						<button type="submit" class="btn btn-success btn-lg px-3">인증</button>
					</div>
				</div>
			</form>
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
    $(document).ready(function() {
        var codeInput = $("#code");
        var checkcodeInput = $("#checkcode");
        var submitButton = $("button[type='submit']");

        submitButton.prop("disabled", true);

        codeInput.on("input", toggleSubmitButton);
        checkcodeInput.on("input", toggleSubmitButton);

        function toggleSubmitButton() {
            var codeValue = codeInput.val();
            var checkcodeValue = checkcodeInput.val();

            if (codeValue === checkcodeValue) {
                submitButton.prop("disabled", false);
            } else {
                submitButton.prop("disabled", true);
            }
        }
    });
	</script>
	<!-- End Script -->
</body>
</html>
