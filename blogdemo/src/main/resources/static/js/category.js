const SERVER_URI = "http://localhost:8080"


async function logJsonData() {
    const response = await fetch(SERVER_URI + "/open-api/category/all");
    const jsonData = await response.json();
    console.log(jsonData);
}

document.addEventListener("DOMContentLoaded", logJsonData)
