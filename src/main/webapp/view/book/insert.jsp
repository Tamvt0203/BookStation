<%@page import="java.util.List"%>
<%@page import="model.bean.Author"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create book</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_page.css" />
</head>
<body>
	<%
	List<Author> authorList = (List<Author>) request.getAttribute("authorList");
	List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
	%>
	<div class="container">
		<form action="BookController" method="post"
			enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label for="BookName" class="control-label">BookName</label> <input
							name="book-name" class="form-control" />
					</div>
					<div class="form-group">
						<label for="AuthorId" class="control-label">Author Name</label> <br>
						<select id="authorSelect" name="author-id"
							class="Author-select form-select" onchange="changeSelectAuthor()">
							<option selected value="" disabled="disabled">Author</option>
							<%
							for (int i = 0; i < authorList.size(); i++) {
							%>
							<option value="<%=authorList.get(i).getAuthorId()%>"><%=authorList.get(i).getAuthorName()%></option>
							<%
							}
							%>
						</select> <input type="hidden" /> <span class="text-danger"></span>
					</div>
					<div class="form-group">
						<label for="CategoryId1" class="control-label">Category 1</label>
						<br> <select name="category1" id="categorySelect1"
							class="Category-select form-select"
							onchange="changeSelectCategory_1()">
							<option selected disabled="disabled" value="">Category</option>
							<%
							for (int i = 0; i < categoryList.size(); i++) {
							%>
							<option value="<%=categoryList.get(i).getCategoryId()%>"><%=categoryList.get(i).getCategoryName()%></option>
							<%
							}
							%>
						</select> <input type="hidden" /> <span class="text-danger"></span>
					</div>
					<div class="form-group">
						<label for="CategoryId2" class="control-label">Category 2</label><br>
						<select name="category2" id="categorySelect2"
							class="Category-select form-select"
							onchange="changeSelectCategory_2()">
							<option selected value="">Category</option>
							<%
							for (int i = 0; i < categoryList.size(); i++) {
							%>
							<option value="<%=categoryList.get(i).getCategoryId()%>"><%=categoryList.get(i).getCategoryName()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="form-group">
						<label class="control-label">Category 3</label><br> <select
							name="category3" id="categorySelect3"
							class="Category-select form-select"
							onchange="changeSelectCategory_3()">
							<option selected value="">Category</option>
							<%
							for (int i = 0; i < categoryList.size(); i++) {
							%>
							<option value="<%=categoryList.get(i).getCategoryId()%>"><%=categoryList.get(i).getCategoryName()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="form-group">
						<label for="Summary" class="control-label"></label>
						<textarea name="summary" class="form-control"></textarea>

					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="FileUpload" class="control-label">Book image</label> <input
							name="img" type="file" required="required" class="form-control"
							id="FileUpload" onchange="preview()" /> <span
							class="text-danger"></span> <img id="frame" src=""
							class="img-fluid" />
					</div>
					<div class="form-group">
						<button name="action" value="handle-insert"
							class="btn btn-primary">Save</button>
					</div>
				</div>
				<div>
					<a
						href="${pageContext.request.contextPath}/BookController?action=list">Back
						to List</a>
				</div>
			</div>
		</form>
	</div>

	<script>
		function preview() {
			frame.src = URL.createObjectURL(event.target.files[0]);

		}
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/popper.js"></script>
</body>
</html>