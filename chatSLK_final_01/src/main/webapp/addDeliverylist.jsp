<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${empty adata}">
<title>SoundPlay | 배송지 추가</title>
</c:if>
<c:if test="${not empty adata}">
<title>SoundPlay | 배송지 수정</title>
</c:if>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${empty adata}">
	<div class="container">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h5>배송지 추가</h5>
				</div>
				<div class="card-body table-border-style">
					<form action="addDeliveryList.do" method="post">
						<div class="form-group mb-3">
							<div class="input-group mb-3">
								<input class="btn btn-success px-3" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
							</div>
							<div class="input-group mb-3">
								<input class="form-control" type="text" id="postcode" name="zipcode" placeholder="우편번호" readonly />
							</div>
							<div class="input-group mb-3">
								<input class="form-control" type="text" id="address" placeholder="기본주소" name="address" readonly />
							</div>
							<div class="input-group mb-3">
								<input class="form-control" type="text" id="addressdetail" name="addressDetail" placeholder="상세주소" />
							</div>
							<div class="form-check mb-3">
								<input class="form-check-input" type="radio" id="status" name="status" value="MAIN">
								<label class="form-check-label" for="status">기본배송지로 설정</label>
							</div>
						</div>
						<button type="submit" class="btn btn-lg btn-success">추가</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${not empty adata}">
	<div class="container">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h5>배송지 수정</h5>
				</div>
				<div class="card-body table-border-style">
					<form method="post">
						<div class="form-group mb-3">
							<div class="input-group mb-3">
								<input class="btn btn-success px-3" type="button" onclick="execDaumPostcode()" value="우편번호 찾기" />
							</div>
							<div class="input-group mb-3">
								<input class="form-control" type="text" id="postcode" name="zipcode" placeholder="우편번호" value="${adata.zipcode}" readonly />
							</div>
							<div class="input-group mb-3">
								<input class="form-control" type="text" id="address" placeholder="기본주소" name="address" value="${adata.address}" readonly />
							</div>
							<div class="input-group mb-3">
								<input class="form-control" type="text" id="addressdetail" name="addressDetail" placeholder="상세주소" value="${adata.addressDetail}" />
							</div>
							<div class="form-check mb-3">
							<c:if test="${adata.status eq 'MAIN'}">
								<input class="form-check-input" type="radio" id="status" name="status" value="MAIN" checked="checked">
							</c:if>
							<c:if test="${adata.status ne 'MAIN'}">
								<input class="form-check-input" type="radio" id="status" name="status" value="MAIN">
							</c:if>
							<label class="form-check-label m-l-10" for="status">기본배송지로 설정</label>
							</div>
						</div>
						<input type="hidden" name="addressNum" value="${adata.addressNum}" />
						<button type="submit" id="deletebtn" class="btn btn-lg btn-danger" formaction="deleteDeliveryList.do">삭제</button>
						<button type="submit" class="btn btn-lg btn-success" formaction="updateDeliveryList.do">수정</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</c:if>




	<script>
	var radio = document.getElementById("status");
	var deletebtn = document.getElementById("deletebtn");
	
	if(radio.checked){
		deletebtn.disabled = true;
	} else {
	    deletebtn.disabled = false;
  	}
	
	
	// 라디오 버튼의 변경 이벤트를 감지합니다.
	radio.addEventListener("change", function () {
	  // 라디오 버튼이 체크되었는지 확인합니다.
	  if (radio.checked) {
	    // 버튼을 비활성화합니다.
	    deletebtn.disabled = true;
	  } else {
	    // 버튼을 활성화합니다.
	    deletebtn.disabled = false;
	  }
	});
	</script>

	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
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
                $("#postcode").val(data.zonecode);
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
</body>
</html>