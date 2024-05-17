<%@page import="model.bean.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<head>
<title>Admin Zone - Books</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/site.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin_page.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />

	<%
	List<Book> list = (List<Book>) request.getAttribute("list");
	%>
	<div class="admin_container container">

		<jsp:include page="../shared/admin-menu.jsp" />
		<button class="btn btn-primary mb-2">
			<a class="admin_button"
				href="${pageContext.request.contextPath}/BookController?action=get-form-insert">Create
				New</a>
		</button>
		<input type="text" id="Search" placeholder="Search"
			class="form-control" />
		<table class="table">
			<thead>
				<tr>
					<th>Book Name</th>
					<th>AuthorID</th>
					<th>Category1</th>
					<th>Category2</th>
					<th>Category3</th>
					<th>Summary</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < list.size(); i++) {
				%>
				<tr class="Search">
					<td class="book-name"><%=list.get(i).getBookName()%></td>
					<td><%=list.get(i).getAuthorId()%></td>
					<td><%=list.get(i).getCategoryId1()%></td>
					<td><%=list.get(i).getCategoryId2()%></td>
					<td><%=list.get(i).getCategoryId3()%></td>
					<td><%=list.get(i).getSummary()%></td>
					<td><a
						href="${pageContext.request.contextPath}/BookController?action=get-form-edit&id=<%=list.get(i).getBookId()%>">Edit</a>
						| <a
						href="${pageContext.request.contextPath}/BookController?action=handle-delete&id=<%=list.get(i).getBookId()%>">Delete</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
        function removeVietnameseDiacritics(text) {
            const diacriticsMap = {
                'á': 'a', 'à': 'a', 'ả': 'a', 'ã': 'a', 'ạ': 'a',
                'â': 'a', 'ấ': 'a', 'ầ': 'a', 'ẩ': 'a', 'ẫ': 'a', 'ậ': 'a',
                'ă': 'a', 'ắ': 'a', 'ằ': 'a', 'ẳ': 'a', 'ẵ': 'a', 'ặ': 'a',
                'đ': 'd',
                'é': 'e', 'è': 'e', 'ẻ': 'e', 'ẽ': 'e', 'ẹ': 'e',
                'ê': 'e', 'ế': 'e', 'ề': 'e', 'ể': 'e', 'ễ': 'e', 'ệ': 'e',
                'í': 'i', 'ì': 'i', 'ỉ': 'i', 'ĩ': 'i', 'ị': 'i',
                'ó': 'o', 'ò': 'o', 'ỏ': 'o', 'õ': 'o', 'ọ': 'o',
                'ô': 'o', 'ố': 'o', 'ồ': 'o', 'ổ': 'o', 'ỗ': 'o', 'ộ': 'o',
                'ơ': 'o', 'ớ': 'o', 'ờ': 'o', 'ở': 'o', 'ỡ': 'o', 'ợ': 'o',
                'ú': 'u', 'ù': 'u', 'ủ': 'u', 'ũ': 'u', 'ụ': 'u',
                'ư': 'u', 'ứ': 'u', 'ừ': 'u', 'ử': 'u', 'ữ': 'u', 'ự': 'u',
                'ý': 'y', 'ỳ': 'y', 'ỷ': 'y', 'ỹ': 'y', 'ỵ': 'y',
            };

            return text.replace(/[^\u0000-\u007E]/g, function (char) {
                return diacriticsMap[char] || char;
            });
        }
        function Contains(text1, text2) {
            const normalizedText1 = removeVietnameseDiacritics(text1.toLowerCase());
            const normalizedText2 = removeVietnameseDiacritics(text2.toLowerCase());
            if (normalizedText1.includes(normalizedText2)) {
                return true;
            } else {
                return false;
            }
        }
        $("#Search").keyup(function () {
            var searchText = $("#Search").val().toLowerCase();
            $(".Search").each(function () {
                var bookName = $(this).find(".book-name").text().toLowerCase();
                if (!Contains(bookName, searchText)) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });
        });
    });

</script>