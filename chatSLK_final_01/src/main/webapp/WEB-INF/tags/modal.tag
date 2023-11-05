<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade bg-transparent show" id="templatemo_search" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document" style="margin-top: 10rem !important;">
		<form action="shopPage.do" method="get" class="modal-content modal-body border-0 p-0">
			<div class="input-group mb-10">
				<input type="text" class="form-control" id="inputModalSearch" name="searchKeyword" placeholder="Search ..." />
				<button type="submit" class="input-group-text bg-success text-light">
					<i class="fa fa-fw fa-search text-white"></i>
				</button>
			</div>
		</form>
	</div>
</div>