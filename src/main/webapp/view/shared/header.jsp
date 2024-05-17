<%@page import="model.bean.UserRole"%>
<%@page import="context.SessionUtils"%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>
<body>
	<header>
		<%
		UserRole userRole = (UserRole) SessionUtils.getInstance().getValue(request, "userRole");
		%>
		<div id="nav" class="sticky-nav">
			<nav class="navbar navbar-expand-lg" style="padding: 0px !important;">
				<div class="container">
					<a class="navbar-brand fw-bold text-white" href="HomeController">Book
						Station</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a
								class="nav-link active fw-bold text-white fs-6"
								aria-current="page"
								href="ContactController?action=get-form-insert"> Contact us
							</a></li>
							<li class="nav-item"><a class="btn btn-outline-info fw-bold"
								style="margin-left: 12px" href="TopController?action=view">
									Top 10 </a></li>
						</ul>
						<%
						if (userRole == null) {
						%>
						<%@ include file="_LoginPartial.jsp"%>
						<%
						}

						else if (userRole.getUserRole().equalsIgnoreCase("admin")) {
						%>

						<%@ include file="admin-partial.jsp"%>
						<%@ include file="user-partial.jsp"%>
						<ul class="navbar-nav">
							<li class="nav-item">
								<form id="manage" class="form-inline fw-bold text-white fs-6"
									style="line-height: initial;">
									<a class="nav-link active fw-bold text-white fs-6"
										aria-current="page"><%=userRole.getUserName()%> </a>
								</form>
							</li>
							<li class="nav-item">
								<form id="logoutForm"
									class="form-inline fw-bold text-white fs-6"
									action="LoginController">
									<button id="logout" type="submit" name="action"
										value="handle-logout" class="btn btn-outline-danger fw-bold">Logout</button>
								</form>
							</li>

						</ul>
						<%
						}

						else if (userRole.getUserRole().equalsIgnoreCase("user")) {
						%>
						<%@ include file="user-partial.jsp"%>
						<ul class="navbar-nav">
							<li class="nav-item">
								<form id="manage" class="form-inline fw-bold text-white fs-6"
									style="line-height: initial;">
									<a class="nav-link active fw-bold text-white fs-6"
										aria-current="page"><%=userRole.getUserName()%> </a>
								</form>
							</li>
							<li class="nav-item">
								<form id="logoutForm"
									class="form-inline fw-bold text-white fs-6"
									action="LoginController">
									<button id="logout" type="submit" name="action"
										value="handle-logout" class="btn btn-outline-danger fw-bold">Logout</button>
								</form>
							</li>

						</ul>
						<%
						}
						%>

					</div>
				</div>
			</nav>
		</div>
	</header>
</body>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/popper.js"></script>