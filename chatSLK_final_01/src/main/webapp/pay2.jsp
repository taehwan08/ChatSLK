<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="slk"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SoundPlay | 결제</title>

<link rel="stylesheet" href="assets/css/adminstyle.css">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png" />
<link rel="shortcut icon" type="image/x-icon" href="assets/img/logo-no-background.ico" />

<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/templatemo.css" />
<link rel="stylesheet" href="assets/css/custom.css" />
<link rel="stylesheet" href="assets/css/join.css" />


<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap" />
<link rel="stylesheet" href="assets/css/fontawesome.min.css" />

<!-- Load map styles -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A==" crossorigin="" />

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

</head>
<body>
	<!-- Start Top Nav -->
	<slk:nav />
	<!-- Close Top Nav -->


	<!-- Header -->
	<slk:header />
	<!-- Close Header -->

	<!-- Modal -->
	<slk:modal />
	
	<!-- 챗봇 -->
	<slk:chatBot />

	<!-- Start Content Page -->
	<div class="container-fluid bg-dark py-5 text-light">
		<div class="col-md-6 m-auto text-center">
			<h1 class="h1 text-white">결제 페이지</h1>
		</div>
	</div>
	<!-- Start Contact -->
	<!-- Shopping Cart Section Begin -->
	<div class="shopping-cart spad">
		<div class="container py-5">
			<div id="payment-method"></div>
			<div style="text-align: right;">
				<a id="payment-request-button" class="btn btn-success btn-lg px-3 text-white">결제하기</a>
			</div>
		</div>
	</div>

	<slk:footer />


	<!-- Start Script -->

	<!-- 결제 API Script -->
	<script src="https://js.tosspayments.com/v1/payment-widget"></script>

	<script src="https://cdn.ckeditor.com/ckeditor5/38.1.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
	<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" defer></script>

	<!-- 주소 API Script -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<script src="assets/js/join.js"></script>

	<script>
      const paymentWidget = PaymentWidget(
        "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq",
        // 비회원 customerKey
        PaymentWidget.ANONYMOUS
      );

      /**
       * 결제창을 렌더링합니다.
       * @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B2%B0%EC%A0%9C-%EA%B8%88%EC%95%A1
       */
      paymentWidget.renderPaymentMethods("#payment-method", { value: ${total} });

      const paymentRequestButton = document.getElementById("payment-request-button");
      paymentRequestButton.addEventListener("click", () => {
        /** 결제 요청
         * @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment%EA%B2%B0%EC%A0%9C-%EC%A0%95%EB%B3%B4
         */
        paymentWidget.requestPayment({
          orderId: generateRandomString(),
          orderName: ${fn:length(cdatas)}+"개 상품",
      	  /*customerEmail: ${mdata.email},
          customerName: ${mdata.memberName}, */
          successUrl: "http://localhost:8080/app/pay.do",
          failUrl: "http://localhost:8080/app/fail.jsp",
        });
      });

      function generateRandomString() {
        return window.btoa(Math.random()).slice(0, 20);
      }
    </script>
	<!-- End Script -->
</body>
</html>