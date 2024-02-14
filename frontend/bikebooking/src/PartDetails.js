import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function PartDetails(props) {
    const [part, setpart] = useState([]);
  //  const [message, setMessage] = useState("");
    const searchParams = new URLSearchParams(props.location.search);
    const id = searchParams.get('partId');
    function getData(id) {
        axios.get(`http://localhost:8080/users/customer/part/${id}`)
            .then((response) => {
                setpart(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    useEffect(() => {
        getData(id);
    }, [id]);

    return (
        <div>
            <div className="row">
                <div className="col-md-6">
                    <img src={part.imageURL} className="card-img-top" alt={part.name} />
                </div>
                <div className="col-md-6">
                    <div className="card-body">
                        <h5 className="card-title">{part.name}</h5>
                        <p className="card-text">Quantity: {part.quantity}</p>
                        <p className="card-text">Price: {part.price}</p>
                        <p className="card-text">Description: {part.description}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PartDetails;
