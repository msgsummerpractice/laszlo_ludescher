if (document.getElementById("showDogBtn") != null) {
  const button: HTMLButtonElement = document.getElementById(
    "showDogBtn",
  ) as HTMLButtonElement;
  button.addEventListener("click", showDog);
}

type DogApiResponse = {
  message: string;
  status: string;
};

async function showDog(): Promise<void> {
  try {
    const response: Response = await fetch(
      "https://dog.ceo/api/breeds/image/random",
    );
    const data: DogApiResponse = await response.json();
    console.log(data.message);

    let image: HTMLImageElement = document.getElementById(
      "image",
    ) as HTMLImageElement;
    image.src = data.message;
    image.style.display = "block";
  } catch (error) {
    console.error("Failed to fetch dog image:", error);
  }
}
