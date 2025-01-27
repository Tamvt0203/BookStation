﻿
document.querySelector('.close-btn').addEventListener("click", function () {
    document.querySelector('.search-result-model').style.display = "none";
    document.querySelector('body').style.overflow = "visible";
    const searchParams = new URLSearchParams(window.location.search);
    searchParams.set('category_id', '');
    window.location.search = searchParams;
});

const searchParams = new URLSearchParams(window.location.search);
if (searchParams.get('category_id')) {
    document.querySelector('.search-result-model').style.display = "flex";
    document.querySelector('body').style.overflow = "hidden";
    var selectElement = document.querySelector('.category-select');
    var selectedValue = selectElement.value;
}

function showValue(bookId, imgName, bookName, author, rating) {
	
    document.querySelector('.search-info-name').innerHTML = bookName;
    document.querySelector('.search-info-author').innerHTML = author;
    document.querySelector('.search-info-rating').innerHTML = rating + '<i class="ms-1 fa-solid fa-star fa-xs" style="color: #ecf000;"></i>';
    document.querySelector('.search-info-img').setAttribute("src", "./img/book_img/" + imgName);
    document.querySelector('#search-info-link').setAttribute("href", "/Review/Index?bookId=" + bookId);
}


