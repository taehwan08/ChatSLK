<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>SoundPlay | 배송지 목록</title>

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

	<div class="container">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<h5 class="h5">배송지 목록</h5>
				</div>
				<div class="card-body table-border-style">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>우편번호</th>
									<th>주소</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="v" items="${adatas}">
									<tr>
										<td>${v.zipcode}</td>
										<td>${v.address}</td>
										<td>
										<c:if test="${v.status eq 'MAIN'}">
											<span class="badge badge-secondary">기본배송지</span>
										</c:if>
										</td>
										<td>
											<a href="addDeliveryListPage.do?addressNum=${v.addressNum}" class="btn btn-primary">수정</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row" style="text-align: right;">
				<div class="continue__btn">
					<a href="addDeliveryListPage.do" class="btn btn-success btn-lg px-3">배송지 추가</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>