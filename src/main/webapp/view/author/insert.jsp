<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert author</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_page.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />
	<div class="admin_container container">
		<div class="col-md-4">
			<form action="" method="get">
				<div class="form-group">
					<label for="AuthorName" class="control-label">Author name</label> <input
						 name="name" class="form-control" /> <span
						 class="text-danger"></span>
				</div>
				<div class="form-group">
					<button name="action" value="handle-insert" class="btn btn-primary">Save</button>
				</div>
			</form>
		</div>
		<div>
			<a
				href="${pageContext.request.contextPath}/AuthorController?action=list">Back
				to List</a>
		</div>
	</div>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/popper.js"></script>

</body>
</html>