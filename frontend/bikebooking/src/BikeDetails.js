import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function BikeDetails(props) {
    const [bike, setBike] = useState([]);
    const [message, setMessage] = useState("");

    function getData(id) {
        axios.get(`http://localhost:8080/users/customer/bike/${id}`)
            .then((response) => {
                setBike(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    const searchParams = new URLSearchParams(props.location.search);
    const id = searchParams.get('bikeId');

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
        <div>
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
            </div>
        </div>
    );
}

export default BikeDetails;
