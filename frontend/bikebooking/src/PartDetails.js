import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import './styles/home.css'

function PartDetails(props) {
    
    const [part, setpart] = useState([]);
  //  const [message, setMessage] = useState("");
  const [message, setMessage] = useState("");
    const [cart, setCart] = useState([]);
    const searchParams = new URLSearchParams(props.location.search);
    const id = searchParams.get('partId');
    const cartId = sessionStorage.getItem("userId");
    function getData(id) {
        axios.get(`http://localhost:8080/users/customer/part/${id}`)
            .then((response) => {
                setpart(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }
    const addToCart = () => {
        axios.post(`http://localhost:8080/users/customer/addPartToCart/${cartId}/${id}`)
        .then((response)=>{
            setMessage(response.data)

        })
        setCart([...cart, part]); 
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
                <div>
                    <button  className= "btn btn-success" onClick={addToCart}>
                        <span id="submit.add-to-cart-announce" className="a-button-success" aria-hidden="true">Add to Cart</span>
                    </button>
                </div>
                <div className="alert alert-info" role="alert">
                    {message}
                </div>
            </div>
        </div>
    );
}

export default PartDetails;
