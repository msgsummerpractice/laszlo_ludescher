document.getElementById("showDogBtn").addEventListener("click", showDogJs);

async function showDogJs() {
    try {
        const response = await fetch('https://dog.ceo/api/breeds/image/random'); 
        const data = await response.json(); 
        console.log(data.message); 
        let image = document.getElementById("image");
        image.src =data.message;
        document.getElementById('image').style.display = "block";
    } catch (error) {
        console.error('Failed to fetch dog image:', error);
    }
}