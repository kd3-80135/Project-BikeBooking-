import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function BikeDetails(props) {
    const [bike, setBike] = useState([]);
    const [message, setMessage] = useState("");
    const [cart, setCart] = useState([]);
    const searchParams = new URLSearchParams(props.location.search);
    const id = searchParams.get('bikeId');
    const cartId = sessionStorage.getItem("userId");

    function getData(id) {
        axios.get(`http://localhost:8080/users/customer/bike/${id}`)
            .then((response) => {
                setBike(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    

    const addToCart = () => {
        axios.post(`http://localhost:8080/users/customer/addBikeToCart/${cartId}/${id}`)
        .then((response)=>{
            setMessage(response.data)

        })
        setCart([...cart, bike]); 
        ShowMessage("Product added to cart");
    };

    const ShowMessage = (msgText) => {
        setMessage(msgText);
        window.setTimeout(() => {
            setMessage("");
        }, 3000);
    };

    useEffect(() => {
        getData(id);
    }, [id]);

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6">
                    <img src={bike.imageURL} className="card-img-top" alt={bike.name} />
                </div>
                <div className="col-md-6">
                    <div className="card-body">
                        <h5 className="card-title">{bike.name}</h5>
                        <p className="card-text">Quantity: {bike.quantity}</p>
                        <p className="card-text">Type: {bike.bikeType}</p>
                        <p className="card-text">Brand: {bike.bikeBrands}</p>
                        <p className="card-text">Colour: {bike.colour}</p>
                        <p className="card-text">Price: {bike.price}</p>
                        <p className="card-text">Description: {bike.description}</p>
                    </div>
                </div>
                <div>
                    <button  className= "btn btn-success" onClick={addToCart}>
                        <span id="submit.add-to-cart-announce" className="a-button-text" aria-hidden="true">Add to Cart</span>
                    </button>
                </div>
                <div className="alert alert-success">
                    {message}
                </div>
            </div>
        </div>
    );
}

export default BikeDetails;
