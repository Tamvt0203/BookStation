var cateNames = document.querySelectorAll("#cate-name")
var bookCounts = document.querySelectorAll("#book-count")

var cateNamesArr = []
var bookCountsArr = []
for (var i = 0; i < cateNames.length; i++) {
    cateNamesArr.push(cateNames[i].innerHTML);
    bookCountsArr.push(bookCounts[i].innerHTML);
}

var cateNames1 = document.querySelectorAll("#cate-name1")
var reviewCounts = document.querySelectorAll("#review-count")

var cateNamesArr1 = []
var reviewCountsArr = []
for (var i = 0; i < cateNames1.length; i++) {
    cateNamesArr1.push(cateNames1[i].innerHTML);
    reviewCountsArr.push(reviewCounts[i].innerHTML);
}

const ctx = document.getElementById('barchart');
new Chart(ctx, {
    type: 'bar',
    data: {
        labels: cateNamesArr,
        datasets: [{
            label: 'Number of books in each category',
            data: bookCountsArr,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1,
            barPercentage: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        plugins: {
            legend: {
                display: false
            }
        }
    }
});

const ctx1 = document.getElementById('doughnut');
new Chart(ctx1, {
    type: 'doughnut',
    data: {
        labels: cateNamesArr1,
        datasets: [{
            label: 'Number of reviews in each category',
            data: reviewCountsArr,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1,
            barPercentage: 1
        }]
    },
    options: {
        scales: {
            y: {
                display: false
            }
        },
        plugins: {
            legend: {
                display: false
            }
        }
    }
});