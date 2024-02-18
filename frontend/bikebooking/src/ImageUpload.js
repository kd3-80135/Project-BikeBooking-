import React, { useState } from 'react';
import axios from 'axios';

function ImageUpload(props)  {
    const [selectedImage, setSelectedImage] = useState(null);
    const searchParams = new URLSearchParams(props.location.search);
    const bikeId = searchParams.get('bikeId');

    const handleImageChange = (e) => {
        setSelectedImage(e.target.files[0]);
    };

    const handleUpload = async () => {
        try {
            const formData = new FormData();
            formData.append('image', selectedImage);    

            // Replace 'http://localhost:8080' with your backend server URL
            const response = await axios.post(`http://localhost:8080/users/dealer/bikeimages/${bikeId}`, formData);

            console.log('Image uploaded successfully:', response.data.imageUrl);
        } catch (error) {
            console.error('Error uploading image:', error.message);
        }
    };

    return (
        <div>
            <h1>Image Upload</h1>
            <input type="file" onChange={handleImageChange} />
            <button onClick={handleUpload}>Upload Image</button>
        </div>
    );
};

export default ImageUpload;
