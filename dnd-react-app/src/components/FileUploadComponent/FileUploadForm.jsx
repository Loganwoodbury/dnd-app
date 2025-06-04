import ImageService from "../../services/ImageService";


export default function FileUploadForm(){

    function handleSubmit(event){
        event.preventDefault();

        const formElement = event.target;
        const formData = new FormData(formElement);

        ImageService.addImage(formData)
            .then(response => {
                console.log("Image uploaded successfully:", response.data);
                // Optionally, you can reset the form or show a success message
                formElement.reset();
            })
            .catch(error => {
                console.error("Error uploading image:", error);
                // Optionally, you can show an error message to the user
            });
    }


    return (
        <>
        <form id="imageUploadForm" onSubmit={handleSubmit}>
            <label htmlFor="imageFile">Choose Image To Upload:</label>
            <input type="file" id="imageFile" name="file" accept="image/*"></input>
            <button type="submit">Upload Image</button>
        </form>

        </>
    )
}

