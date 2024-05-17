<%@page import="model.bean.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<head>
<title>Admin Zone - Reviews</title>
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
	List<Review> list = (List<Review>) request.getAttribute("list");
	%>
	<div class="admin_container container">

		<jsp:include page="../shared/admin-menu.jsp" />
		<a class="btn btn-outline-danger admin-button mb-2"
			href="ReportController?action=list">Reported Reviews</a>
		<table class="table">
			<thead>
				<tr>
					<th>UserID</th>
					<th>BookID</th>
					<th>ReviewDate</th>
					<th>Rating</th>
					<th>Up votes</th>
					<th>Down votes</th>
					<th>Total votes</th>
					<th>Detail</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td><%=list.get(i).getUserId()%></td>
					<td><%=list.get(i).getBookId()%></td>
					<td><%=list.get(i).getReviewDate()%></td>
					<td><%=list.get(i).getRating()%></td>
					<td><%=list.get(i).getUpVotes()%></td>
					<td><%=list.get(i).getDownVotes()%></td>
					<td><%=list.get(i).getTotalVotes()%></td>
					<td><%=list.get(i).getDetail()%></td>
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
        //function Contains(text1, text2) {
        //    if (text1.indexOf(text2) != -1) {
        //        return true;
        //    }
        //}
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
                if (!Contains($(this).text().toLowerCase(), searchText)) {
                    $(this).hide();
                }
                else {
                    $(this).show();
                }
            });
        });
    });

</script>
