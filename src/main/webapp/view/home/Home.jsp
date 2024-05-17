<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.List" import="model.bean.*"
	import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Objects"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/book_card.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home_style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/site.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Font-Awesome-640/css/all.css" />
</head>
<body>
	<jsp:include page="../shared/header.jsp" />
	<div class="wrap-container">
		<div class="container wrap-info">
			<div class="row justify-content-center home-nav">
				<div class="col col-4">
					<img class="home_img"
						src="${pageContext.request.contextPath}/img/home_img.png"
						alt="Image Title" />
				</div>
				<div class="col col-6 home-introduction">
					<h1 class="mb-3 fw-semibold lh-1 home-introduction-title">Get
						on board with knowledge at Book Station!</h1>
					<p class="lead mb-4 fw-normal home-introduction-detail">The
						book review community is a group of passionate readers who share
						recommendations and feedback about book. Joining this community
						connects you with like-minded individuals and helps you discover
						new books to read.</p>
				</div>
				<div class="categories-filter">
					<div class="categories-filter-title">Find your next great
						read using our filter categories</div>
					<form method="GET" action="HomeController" class="category-submit">
						<select class="category-select form-select form-select-lg"
							aria-label=".form-select-lg example" name="category_id">
							<option selected value="">Category</option>
							<%
							List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
							for (Category obj : listCategories) {
							%>
							<option value="<%=obj.getCategoryId()%>"><%=obj.getCategoryName()%></option>
							<%
							}
							%>
						</select>
						<button type="submit" class="btn search-btn">Search</button>
					</form>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col col-12">
					<section class="dark">
						<div class="py-4">
							<h1 class="h1 text-center" id="pageHeaderTitle">Search your
								book by name</h1>
							<div class="search-name-model">
								<div class="search-name">
									<input type="text" id="Search" class="search-bar"
										placeholder="Search" />
									<div class="search-table">
										<table>
											<tbody>
												<%
												List<BookCard> listBooks = (List<BookCard>) request.getAttribute("listAllBookCard");
												for (BookCard obj : listBooks) {
												%>
												<tr class="Search">
													<td class="row-search"
														onmouseover="showValue('<%=obj.getBookId()%>', '<%=obj.getImageName()%>',
            														'<%=obj.getBookName()%>', '<%=obj.getAuthorName()%>', '<%=obj.getAverageRating()%>')">
														<a
														href="ReviewController?action=getReviews&bookId=<%=obj.getBookId()%>&page=0&size=5">
															<%=obj.getBookName()%>
													</a>
													</td>
												</tr>
												<%
												}
												%>
											</tbody>
										</table>
									</div>
								</div>
								<div class="search-info">
									<div class="book-info">
										<a href="/Review/Index?bookId=1" id="search-info-link"><img
											class="search-info-img" src="./img/book_img/1.jpg"
											alt="Image Title" /></a>
										<div class="search-info-text">
											<h3 class="search-info-name">Rừng Na Uy</h3>
											<h5 class="search-info-author">Murakami Haruki</h5>
											<div class="search-info-wrap-rating">
												<h4 class="search-info-rating">4.5<i class="ms-1 fa-solid fa-star fa-xs" style="color: #ecf000;"></i></h4>
												<label class='bx bxs-star star'></label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<h1 class="h1 text-center" id="pageHeaderTitle">Top 10 Most
								Popular Books</h1>
							<%
							String[] classes = new String[]{"blue", "red", "green", "yellow"};
							int count = 0;
							List<BookCard> listBookCards = (List<BookCard>) request.getAttribute("listCardBooks");
							List<Book> listPopularBooks = (List<Book>) request.getAttribute("listPopularBooks");
							//BookBO bookBO = new BookBO();
							//CategoryBO categoryBO = new CategoryBO();
							for (BookCard book : listBookCards) {
							%>
							<article
								class="postcard dark <%=classes[count % classes.length]%>">
								<a class="postcard__img_link"
									href="ReviewController?action=getReviews&bookId=<%=book.getBookId()%>">
									<img class="postcard__img"
									src="./img/book_img/<%=book.getBookId()%>.jpg"
									alt="Image Title" />
								</a>
								<div class="postcard__text">
									<h1
										class="postcard__title <%=classes[count % classes.length]%>">
										<a
											href="ReviewController?action=getReviews&bookId=<%=book.getBookId()%>&page=0&size=5"><%=book.getBookName()%></a>
									</h1>
									<div class="postcard__subtitle small">
										<i class="mr-2"></i><%=book.getAuthorName()%>
									</div>
									<div class="postcard__bar"></div>
									<div class="postcard__preview-txt"><%=book.getSummary()%>...
									</div>
									<ul class="postcard__tagbox">
										<li class="tag__item"><i class="mr-2"></i><%=book.getCategoryName1()%></li>
										<li class="tag__item"><i class="mr-2"></i><%=book.getCategoryName2()%></li>
										<li class="tag__item"><i class="mr-2"></i>Average Rating:
											<%=book.getAverageRating()%></li>
									</ul>
								</div>
							</article>
							<%
							count++;
							}
							%>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<div class="search-result-model">
		<div class="search-result-container">
			<div class="search-result-header">
				<div class="search-result-category">
					<h2>Category</h2>
					<%
					int categoryId = 0;
					//categoryId = Integer.parseInt(request.getParameter("category_id"));
					if (Objects.nonNull(request.getParameter("category_id")) && !request.getParameter("category_id").isEmpty()) {
						categoryId = Integer.parseInt(request.getParameter("category_id"));
					}
					//<h2 class="search-result-category-current">@Model.FindCategory(categoryId)</h2>
					List<Book> listALLBooks = (List<Book>) request.getAttribute("listBooks");
					%>
				</div>
				<button class="btn btn-danger close-btn">Cancel</button>
			</div>

			<div class="search-result-content container">
				<div class="row">
					<%
					for (Book book : listALLBooks) {
						if (book.getCategoryId1() == categoryId || book.getCategoryId2() == categoryId
						|| book.getCategoryId3() == categoryId) {
					%>
					<div class="col col-4 search-result-book">
						<img class="search-result-book-img"
							src="./img/book_img/<%=book.getBookId()%>.jpg" alt="Image Title" />
						<div class="search-result-book-info">
							<h3 class="search-result-book-title"><%=book.getBookName()%></h3>
							<a class="btn btn-outline-primary more-btn"
								href="ReviewController?action=getReviews&bookId=<%=book.getBookId()%>&page=0&size=5">More</a>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/home.js"></script>
	<script src="${pageContext.request.contextPath}/jquery/dist/jquery.js"></script>
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
</body>
</html>