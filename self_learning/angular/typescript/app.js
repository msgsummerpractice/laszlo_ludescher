"use strict";
const button = document.getElementById("showDogBtn");
button.addEventListener("click", showDog);
async function showDog() {
    try {
        const response = await fetch('https://dog.ceo/api/breeds/image/random');
        const data = await response.json();
        console.log(data.message);
        let image = document.getElementById("image");
        image.src = data.message;
        image.style.display = "block";
    }
    catch (error) {
        console.error('Failed to fetch dog image:', error);
    }
}
