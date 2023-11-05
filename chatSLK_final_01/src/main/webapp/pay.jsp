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
	<form action="try.do" method="post">
		<div class="shopping-cart spad">
			<div class="container py-5">
				<div class="shopping__cart__table">
					<table class="table">
						<thead class="table-dark">
							<tr>
								<th></th>
								<th>상품</th>
								<th>수량</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody style="vertical-align: middle;">
							<slk:order />
						</tbody>
						<tbody>
							<tr>
								<c:set var="total" value="0" />
								<c:forEach var="v" items="${cdatas}">
									<c:set var="price" value="${v.productPrice*v.cartCount}" />
									<c:set var="total" value="${total+price}" />
								</c:forEach>
								<fmt:formatNumber value="${total}" var="formProductPrice" />
								<td></td>
								<td></td>
								<td><h5 class="text-right mt-3 mb-3">총 금액</h5></td>
								<td><h5 class="text-right mt-3 mb-3 price">${formProductPrice}&nbsp;원</h5></td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="total" value="${total}" />
				</div>
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th>주문 정보</th>
						</tr>
					</thead>
				</table>
				<table>
					<tr>
						<td class="product__cart__item">
							<div class="container">
								<div class="row py-4">
									<label for="basic-url" class="form-label">주문자 이름</label>
									<div class="input-group mb-3">
										<input class="form-control" type="text" name="memberName" value="${mdata.memberName}" />
									</div>
									<label for="basic-url" class="form-label">핸드폰 번호</label>
									<div class="input-group mb-3">
										<input class="form-control" type="text" name="phonenumber" value="${mdata.phonenumber}" />
									</div>
									<div class="input-group mb-3">
										<button type="button" id="selectAddressButton" class="btn btn-success btn-lg px-3">배송지 선택</button>
									</div>
									<div class="table-responsive" style="display: none;">
										<table id="deliveryAddressTable" class="table col-6">
											<thead id="tableHead">
												<tr>
													<th>선택</th>
													<th>우편번호</th>
													<th>주소</th>
													<th>상세주소</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="v" items="${adatas}">
													<tr>
														<td><c:if test="${v.status eq 'MAIN'}">
																<input type="radio" id="customRadio${v.addressNum}" class="checkbtn" name="status" checked="checked">
															</c:if> <c:if test="${v.status ne 'MAIN'}">
																<input type="radio" id="customRadio${v.addressNum}" class="checkbtn" name="status">
															</c:if></td>
														<td class="zipcode">${v.zipcode}</td>
														<td class="address">${v.address}</td>
														<td class="addressDetail">${v.addressDetail}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="input-group mb-3">
										<input class="btn btn-success btn-lg px-3" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
									</div>
									<c:forEach var="i" begin="0" end="${fn:length(adatas)-1}">
										<c:if test="${adatas[i].status eq 'MAIN'}">
											<label for="basic-url" class="form-label">우편번호</label>
											<div class="input-group mb-3">
												<input type="hidden" id="addressNum" name="addressNum" value="${adatas[i].addressNum}" />
												<input class="form-control" type="text" id="zipcode" name="zipcode" value="${adatas[i].zipcode}" readonly required />
											</div>
											<label for="basic-url" class="form-label">주소</label>
											<div class="input-group mb-3">
												<input class="form-control" type="text" id="address" name="address" value="${adatas[i].address}" readonly required />
											</div>
											<label for="basic-url" class="form-label">상세주소</label>
											<div class="input-group mb-3">
												<input class="form-control" type="text" id="addressDetail" name="addressDetail" value="${adatas[i].addressDetail}" required />
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<div class="container py-5">
					<div style="text-align: right;">
						<button type="submit" class="btn btn-success btn-lg px-3">다음단계</button>
					</div>
				</div>
			</div>
		</div>
	</form>

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
        var tableHead = $(".table-responsive");
        
        $("#selectAddressButton").on("click", function () {
            if (tableHead.css("display") === "none") {
                tableHead.show();
            } else {
                tableHead.hide();
            }
        });
    </script>
	<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
            	
            	var addr = '';
            	
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                
              //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#zipcode").val(data.zonecode);
                $("#address").val(addr);

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                var guideTextBox = $("#guide");
                if (data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.html('(예상 도로명 주소 : ' + expRoadAddr + ')');
                    guideTextBox.css("display", "block");
                } else if (data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.html('(예상 지번 주소 : ' + expJibunAddr + ')');
                    guideTextBox.css("display", "block");
                } else {
                    guideTextBox.css("display", "none");
                }
            }
        }).open({autoClose: true});
    }
</script>
<script>
document.addEventListener("DOMContentLoaded", function() {
    const radioButtons = document.querySelectorAll(".checkbtn");
    const zipcodeInput = document.getElementById("zipcode");
    const addressInput = document.getElementById("address");
    const addressDetailInput = document.getElementById("addressDetail");
    var tableHead = $(".table-responsive");

    radioButtons.forEach(button => {
        button.addEventListener("change", function() {
            if (this.checked) {
                const row = this.closest("tr");
                const zipcode = row.querySelector(".zipcode").textContent;
                const address = row.querySelector(".address").textContent;
                const addressDetail = row.querySelector(".addressDetail").textContent;

                zipcodeInput.value = zipcode;
                addressInput.value = address;
                addressDetailInput.value = addressDetail;
                
                tableHead.hide();
            }
        });
    });
});
</script>
	<!-- End Script -->
</body>
</html>