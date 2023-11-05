<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<c:if test="${empty memberId}">
	<form class="col-md-9 m-auto" action="signup.do" method="post" role="form">
		<input type="hidden" name="searchCondition" value="1" />
		<div class="input-group mb-3">
			<span class="input-group-text"> <i class="fa fa-fw fa-user text-dark"></i></span>
			<input type="text" name="memberId" id="memberId" class="form-control" placeholder="ID" aria-label="ID" aria-describedby="basic-addon1" required></input>
		</div>
		<div id="noticeId" class="input-group mb-3">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"> <i class="fa fa-fw fa-lock text-dark"></i></span>
			<input type="password" name="memberPw" id="memberPw" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" required />
		</div>
		<div id="noticePw" class="input-group mb-3">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"> <i class="fa fa-fw fa-id-card text-dark"></i></span>
			<input type="text" name="memberName" id="memberName" class="form-control" placeholder="Name" aria-label="Username" aria-describedby="basic-addon1" required />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"> <i class="fa fa-fw fa-mobile text-dark"></i></span>
			<input type="text" name="phonenumber" id="phonenumber" class="form-control" placeholder="Mobile" aria-label="Mobile" aria-describedby="basic-addon1" required />
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"> <i class="fa fa-fw fa-envelope text-dark"></i></span>
			<input type="text" name="email" id="email" class="form-control" placeholder="email" aria-label="Email" required />
			<span class="input-group-text">@</span>
			<input class="form-control" name="domain" id="domain-txt" type="text" required /> <select class="form-select" id="domain-list">
				<option value="type">직접 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="nate.com">nate.com</option>
				<option value="kakao.com">kakao.com</option>
			</select>
		</div>
		
		<div class="input-group mb-3" style="justify-content: flex-end;">
			<input type="submit" id="signupButton" class="btn btn-success btn-lg px-3" value="회원가입">
		</div>
	</form>
</c:if>



<c:if test="${not empty memberId}">
	<form class="col-md-9 m-auto" action="updateMember.do" method="post" role="form">
		<div class="input-group mb-3">
			<a href="deliveryListPage.do" class="btn btn-success btn-lg px-3" onclick="openNewWindow(event)">배송지 관리</a>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text"><i class="fa fa-fw fa-user text-dark"></i></span>
			<input type="text" name="memberId" class="form-control" placeholder="ID" aria-label="ID" aria-describedby="basic-addon1" value="${mdata.memberId}" readonly />
		</div>
		<c:set var="memberId" value="${memberId}" />
		<c:choose>
			<c:when test="${mdata.role eq 'GOOGLE'}">
				<div class="input-group mb-3" style="display:none;">
					<span class="input-group-text"><i class="fa fa-fw fa-lock text-dark"></i></span>
					<input type="password" name="tmpPw" class="form-control" placeholder="구글회원은 비밀번호를 변경 할 수 없습니다." aria-label="Password" aria-describedby="basic-addon1" readonly />
				</div>
			</c:when>
			<c:otherwise>
				<div class="input-group mb-3">
					<span class="input-group-text"><i class="fa fa-fw fa-lock text-dark"></i></span>
					<input type="password" name="tmpPw" class="form-control" placeholder="NEW Password" aria-label="Password" aria-describedby="basic-addon1" />
				</div>
			</c:otherwise>
		</c:choose>
		<div class="input-group mb-3">
			<span class="input-group-text"><i class="fa fa-fw fa-id-card text-dark"></i></span>
			<input type="text" name="memberName" class="form-control" placeholder="Name" aria-label="Username" aria-describedby="basic-addon1" value="${mdata.memberName}" required />
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text"><i class="fa fa-fw fa-mobile text-dark"></i></span>
			<input type="text" name="phonenumber" class="form-control" placeholder="Mobile" aria-label="Username" aria-describedby="basic-addon1" value="${mdata.phonenumber}" required />
		</div>

		<div class="input-group mb-3">
			<span class="input-group-text"> <i class="fa fa-fw fa-envelope text-dark"></i>
			</span> <input type="text" name="email" class="form-control" placeholder="email" aria-label="Email" value="${mdata.email}" required />
			<span class="input-group-text">@</span>
			<input class="form-control" name="domain" id="domain-txt" type="text" value="${mdata.domain}" required />
			<select class="form-select" id="domain-list">
				<option value="type">직접 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="nate.com">nate.com</option>
				<option value="kakao.com">kakao.com</option>
			</select>
		</div>
		
		<div class="input-group mt-10 mb-3" style="justify-content: flex-end;">
			<input type="submit" class="btn btn-success btn-lg px-3" value="변경하기">
		</div>
	</form>
</c:if>
<script>
// ---- 아이디 특수문자 불가능 --
// ---- 회원가입 아이디 중복검사 --
$(document).ready(function() {
    $("#memberId").on("input", function() {
    	var inputid = $("#memberId").val();
    	var specialCharacters = /[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/;
    	if(inputid.length < 6){
    		$("#noticeId").html('<small class="form-text text-muted"><code>아이디는 6자 이상이어야 합니다.</code></small>');
    		disableSubmitButton();
    		return;
    	}
    	else if (specialCharacters.test(inputid)) {
            $("#noticeId").html('<small class="form-text text-muted"><code>특수문자는 사용할 수 없습니다.</code></small>');
            $(this).val(inputid.replace(specialCharacters, ''));
            disableSubmitButton();
            return;
        } else {
            $("#noticeId").empty();
            enableSubmitButton();
        }
        $.ajax({
            type: "POST",
            url: "checkId.do?memberId="+inputid,
            success: function(response) {
                if (response == "true") {
                    $("#noticeId").html('<small class="form-text text-muted">사용 가능한 아이디입니다.</small>');
                    enableSubmitButton();
                } else {
                    $("#noticeId").html('<small class="form-text text-muted"><code>이미 사용중인 아이디입니다.</code></small>');
                    disableSubmitButton();
                }
            },
            error: function() {
                $("#noticeId").html('<small class="form-text text-muted">서버 오류가 발생했습니다. 나중에 다시 시도해주세요.</small>');
            }
        });
        
    });
    
    
    $('#memberPw').on("input", function(){
    	var inputPw = $('#memberPw').val();
    	
    	if(inputPw.length < 4){
    		$("#noticePw").html('<small class="form-text text-muted"><code>비밀번호는 4자 이상이어야 합니다.</code></small>');
    		disableSubmitButton();
    	} else {
            $("#noticePw").empty();
            enableSubmitButton();
        }
    });
    
    
 // 회원가입 버튼 클릭 이벤트 핸들러
    $('#signupButton').click(function() {
        if ($("#noticeId").html() !== "") {
            // 아이디 오류가 있는 경우 아이디 입력 필드로 스크롤 또는 포커스 이동
            $("#memberId").focus();
            // 혹은 필드가 가시화 되지 않을 경우 스크롤로 스크롤 위치 이동
            // window.scrollTo(0, $("#memberId").offset().top);
            return;
        }
        
        if ($("#noticePw").html() !== "") {
            // 비밀번호 오류가 있는 경우 비밀번호 입력 필드로 스크롤 또는 포커스 이동
            $("#memberPw").focus();
            // 혹은 필드가 가시화 되지 않을 경우 스크롤로 스크롤 위치 이동
            // window.scrollTo(0, $("#memberPw").offset().top);
            return;
        }

        // 아이디와 비밀번호 유효성 검사 통과 시 회원가입 처리
        // 예: $("#signupForm").submit();
    });

    // 회원가입 버튼 비활성화
    function disableSubmitButton() {
        $("#signupButton").prop("disabled", true);
    }

    // 회원가입 버튼 활성화
    function enableSubmitButton() {
        $("#signupButton").prop("disabled", false);
    }
    
    
    
});
</script>

<script type="text/javascript">
	// --- [ 팝업 ] ---
	function openNewWindow(event) {
	  event.preventDefault(); // 기본 링크 동작을 막습니다.
	  
	  var linkURL = event.currentTarget.href;
	  var windowName = "_blank"; // 새 창 이름 (기본적으로 "_blank"를 사용하면 새 창이 열립니다.)
	  var windowFeatures = "width=600,height=700,top=200"; // 새 창 크기 및 기타 특성
	  
	  var newWindow = window.open(linkURL, windowName, windowFeatures);
	  if (newWindow === null) {
	    alert("팝업 차단이 설정되어 있을 수 있습니다. 팝업 차단을 해제해주세요.");
	  }
	}
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