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

    if (!data || data.length === 0) {
        categoryList.innerHTML = "<li>No categories found</li>"
        return;
    }

    data.forEach(category => {
        const li = document.createElement("li");
        li.textContent = `${category.category_name} (${category.article_count})`
        categoryList.appendChild(li);
    })
}

document.addEventListener("DOMContentLoaded", getCategoryJson)
