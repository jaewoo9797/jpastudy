const SERVER_URI = "http://localhost:8080"


async function getCategoryJson() {
    try {
        const response = await fetch(SERVER_URI + "/open-api/category/all");
        const data = await response.json();
        renderCategoryList(data);
    } catch (error) {
        console.log("Failed to fetch category date: ", error);
    }
}

function renderCategoryList(data) {
    const categoryList = document.getElementById("category-list");
    categoryList.innerHTML = "";

    if (!data || data.length === 0) {
        categoryList.innerHTML = "<li>No categories found</li>"
        return;
    }

    data.forEach(category => {
        const li = document.createElement("li");
        li.classList.add("list-group-item", "d-flex", "justify-content-between", "align-items-start")

        const divContainer = document.createElement("div");
        divContainer.classList.add("ms-2", "me-auto");

        const categoryLink = document.createElement("a");
        categoryLink.classList.add("fw-bold", "text-decoration-none");
        categoryLink.textContent = category.category_name;
        categoryLink.href = '#';    //TODO 링크 설정해주기
        categoryLink.target = "_self";

        const spanBadge = document.createElement("span");
        spanBadge.classList.add("badge", "text-bg-warning", "rounded-pill");
        spanBadge.textContent = category.article_count;

        divContainer.appendChild(categoryLink);

        li.appendChild(divContainer);
        li.appendChild(spanBadge)

        categoryList.appendChild(li);
    })
}

document.addEventListener("DOMContentLoaded", getCategoryJson)
