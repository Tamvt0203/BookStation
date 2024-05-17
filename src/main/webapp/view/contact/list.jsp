<%@page import="model.bean.Contact"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<head>
<title>Admin Zone - Contacts</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_page.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />
	<%-- <div><%= ${pageContext.request.contextPath}%></div> --%>
	<%
	List<Contact> list = (List<Contact>) request.getAttribute("list");
	%>
	<div class="admin_container container">
		<jsp:include page="../shared/admin-menu.jsp" />
		<div class="row">
			<div class="col col-6">
				<h2>Unprocessed contact list</h2>
				<table class="table not-block-table">
					<thead>
						<tr>
							<th class="thead-username">Content</th>
							<th class="thead-num">Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getStatus() == 0) {
						%>
						<tr>
							<td><%=list.get(i).getContent()%></td>
							<td>Unprocessed</td>
							<td><a class="btn btn-outline-primary" href="ContactController?id=<%=list.get(i).getId()%>&action=process">Process</a></td>
						</tr>
						<%
						}
						}
						%>

					</tbody>
				</table>
			</div>
			<div class="col col-6">
				<h2>Processed contact list</h2>
				<table class="table not-block-table">
					<thead>
						<tr>
							<th class="thead-username">Content</th>
							<th class="thead-num">Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).getStatus() == 1) {
						%>
						<tr>
							<td><%=list.get(i).getContent()%></td>
							<td>Processed</td>
							<td><a class="btn btn-outline-danger" href="ContactController?id=<%=list.get(i).getId()%>&action=undo-process">Undo
									process</a></td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>
