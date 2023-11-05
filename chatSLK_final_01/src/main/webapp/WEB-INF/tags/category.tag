<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:forEach var="ct" items="${ctdatas}">
	<li class="pb-3"><a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="#"> ${ct.categoryName} <i class="fa fa-fw fa-chevron-circle-down mt-1"></i>
	</a>
		<ul class="collapse show list-unstyled pl-3">
			<c:forEach var="cd" items="${cddatas}">
				<c:if test="${ct.categoryNum eq cd.categoryNum}">
					<li><a class="text-decoration-none" href="shopPage.do?categoryDetailNum=${cd.categoryDetailNum}">${cd.categoryDetailName}</a></li>
				</c:if>
			</c:forEach>
		</ul></li>
</c:forEach>