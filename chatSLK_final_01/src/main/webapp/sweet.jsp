<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
</head>
<body>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>

	<c:if test="${empty alert.canceltext}">
		<script>
window.onload=function() {
    swal.fire({
      title: '${alert.title}',
      text: '${alert.msg}',
      icon: '${alert.icon}',
      confirmButtonColor: '#198754', // confrim 버튼 색깔 지정
      confirmButtonText: '${alert.confirmtext}', // confirm 버튼 text
    }).then((result) => {
    if (result.isConfirmed) {
    	window.history.go(-1);
       }
    });
  };
</script>
	</c:if>
	<c:if test="${not empty alert.canceltext}">
		<script>
window.onload=function() {
    swal.fire({
      title: '${alert.title}',
      text: '${alert.msg}',
      icon: '${alert.icon}',
      confirmButtonColor: '#198754', // confrim 버튼 색깔 지정
      confirmButtonText: '${alert.confirmtext}', // confirm 버튼 text
      showCancelButton: true, // cancle 버튼 보이기
      cancelButtonColor: '#6c757d', // cancel 버튼 색깔 지정
      cancelButtonText: '${alert.canceltext}' // cancel 버튼 text
    }).then((result) => {
    if (result.isConfirmed) {
       	location.href = '${alert.path}';
       }else{
    	   window.history.go(-1);
       }
    });
  };
</script>
	</c:if>



</body>
</html>