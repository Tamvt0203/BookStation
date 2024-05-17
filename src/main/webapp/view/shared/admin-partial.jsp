<%@page import="context.SessionUtils"%>
<%@page import="model.bean.UserRole"%>
<a class="btn btn-outline-info fw-bold" style="margin-right: 12px" href="StatController?action=view">Stat</a>
<div class="dropdown">
	<button class="btn btn-outline-danger fw-bold dropdown-toggle"
		style="margin-right: 12px" type="button" id="dropdownMenuButton1"
		data-bs-toggle="dropdown" aria-expanded="false">Manager Menu
	</button>
	<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
		<li><a class="dropdown-item" href="AuthorController?action=list">Authors</a></li>
		<li><a class="dropdown-item" href="BookController?action=list">Books</a></li>
		<li><a class="dropdown-item" href="CategoryController?action=list">Categories</a></li>
		<li><a class="dropdown-item" href="ReviewController?action=list">Reviews</a></li>
		<li><a class="dropdown-item" href="ContactController?action=list">Contacts</a></li>
	</ul>
</div>

