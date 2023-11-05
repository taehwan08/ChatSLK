<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<c:if test="${empty cdatas}">
	<!-- 비어있을경우 출력하도록 만들기  -->
	<tr>
		<td width=100% colspan="7" style="text-align: center; line-height: 300px; font-size: 30px;"><b>장바구니가 비어있습니다.</b></td>
	</tr>
</c:if>
<c:if test="${not empty cdatas}">

	<c:forEach var="v" items="${cdatas}">
		<tr class="cartlist ${v.productNum}">
			<td>
				<div class="chk-option m-t-35">
					<label class="check-task custom-control custom-checkbox d-flex justify-content-center done-task">
						<input id="checkbox${v.productNum}" type="checkbox" name="number" class="custom-control-input ${v.productNum}" value="${v.productNum}">
						<span class="custom-control-label"></span>
					</label>
				</div>
			</td>
			<td class="product__cart__item"></td>
			<td class="product__cart__item">
				<div class="product__cart__item__pic">
					<a href="shopSinglePage.do?productNum=${v.productNum}"> <img src="assets/productimg/${v.path}" width="100px" height="100px">
					</a>
				</div>
			</td>
			<td class="product__cart__item">
				<div class="product__cart__item__text">
					<p class="text-light">
						<a href="shopSinglePage.do?productNum=${v.productNum}" style="color: #212529 !important; text-decoration: none !important;">${v.company}</a>
					</p>
					<p class="text-light">
						<a href="shopSinglePage.do?productNum=${v.productNum}" style="color: #212529 !important; text-decoration: none !important;">${v.productName}</a>
					</p>
				</div>
			</td>
			<td class="quantity__item">
				<div class="quantity">
					<div class="col-auto">
						<ul class="list-inline pb-3">
							<li class="list-inline-item"><span class="btn btn-success ${v.productNum} btn-minus">-</span></li>
							<li class="list-inline-item"><span class="badge bg-secondary ${v.productNum}" id="var-value">${v.cartCount}</span></li>
							<li class="list-inline-item"><span class="btn btn-success ${v.productNum} btn-plus">+</span></li>
						</ul>
					</div>
				</div>
			</td>
			<fmt:formatNumber value="${v.productPrice * v.cartCount}" var="formProductPrice" />
			<td class="cart__price ${v.productNum}">${formProductPrice}&nbsp;원</td>
			<td><span class="btn btn-success ${v.productNum} btn-delete">x</span></td>
			<td class="cart__close"><i class="fa fa-close"></i></td>
		</tr>
		<script>
		
$(".btn.btn-success." + ${v.productNum} + ".btn-minus").on("click", function () {
    console.log("장바구니 마이너스 콘솔");
    var a = $(".btn.btn-success." + ${v.productNum} + ".btn-minus").text();
    console.log(a);
    var b = parseInt($(".badge.bg-secondary." + ${v.productNum}).text());
    if (b > 1) {
        $.ajax({
            url: 'addCartlist.do?productNum=' + ${v.productNum} + '&cartCount=' + a + '1',
            type: 'POST',
            success: function (result) {
                console.log(result);
                if (result == 'update') {
                    console.log('장바구니 -1 성공');
                    $(".badge.bg-secondary." + ${v.productNum}).text(b - 1);

                    var c = parseInt($(".badge.bg-secondary." + ${v.productNum}).text());
                    console.log(c);
                    console.log(${v.productPrice} * c);
                    $(".cart__price." + ${v.productNum}).text((${v.productPrice} * c).toLocaleString()+" 원");
                } else {
                    console.log('장바구니 업데이트 실패');
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
});


$(".btn.btn-success."+${v.productNum}+".btn-plus").on("click",function(){
	console.log("장바구니 플러스 콘솔");
	   var a = $(".btn.btn-success."+${v.productNum}+".btn-plus").text();
	   console.log(a);
	   $.ajax({
	      url: 'addCartlist.do?productNum='+${v.productNum}+'&cartCount=1',
	      type:'POST',
	      success:function(result){
	         console.log(result);
	         if(result=='update'){
	            console.log('장바구니 +1 성공');
	            <!-- 수량 변경 -->
	            var b = parseInt($(".badge.bg-secondary."+${v.productNum}).text());
	            $(".badge.bg-secondary."+${v.productNum}).text(b+1);
	            <!-- 가격 변경 -->
	            var c = parseInt($(".badge.bg-secondary." + ${v.productNum}).text());
                console.log(c);
                console.log(${v.productPrice} * c);
                $(".cart__price." + ${v.productNum}).text((${v.productPrice} * c).toLocaleString()+" 원");
	         }else{
	            console.log('장바구니 업데이트 실패');
	         }
	      },
	      error:function(error){
	         console.log(error);
	      }
	   });
	});

$(".btn.btn-success."+${v.productNum}+".btn-delete").on("click",function(){
   console.log("장바구니 삭제 콘솔");   
   $.ajax({
      url: 'deleteCart.do?productNum='+${v.productNum},
      type:'POST',
      success:function(result){
         console.log(result);
         if(result=='delete'){
            console.log('장바구니 삭제 성공');
            <!-- td 없애버리기 -->
            $(".cartlist."+${v.productNum}).remove();
            location.reload();
         }else{
            console.log('장바구니 업데이트 실패');
         }
      },
      error:function(error){
         console.log(error);
      }
   });
});

</script>
	</c:forEach>
</c:if>