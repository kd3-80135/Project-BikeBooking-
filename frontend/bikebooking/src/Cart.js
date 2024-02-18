import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import IncrementDecrementBikeBtn from "./IncrementDecrementBikeBtn";
import IncrementDecrementPartBtn   from "./IncrementDecrementPartBtn"


function Cart() {
    const [bikes, setBikesList] = useState([]);
    const [parts, setPartsList] = useState([]); 
    const [message, setMessage] = useState("");
    const [totalPrice, setTotalPrice] = useState(0);


    var userId = sessionStorage.getItem("userId");

    function getBikeData() {
        axios.get(`http://localhost:8080/users/customer/cartBikeList/${userId}`)
            .then((response) => {
                setBikesList(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }
    function getPartData() {
        axios.get(`http://localhost:8080/users/customer/cartPartList/${userId}`)
            .then((response) => {
                setPartsList(response.data);
            })
            .catch((err) => {
                setMessage(err.data);
            });
    }

    function calculateTotalPrice() {
        let bikeTotal = bikes.reduce((acc, bike) => acc + bike.price * bike.bikeQuantity, 0);
        let partTotal = parts.reduce((acc, part) => acc + part.price * part.partQuantity, 0);
        setTotalPrice(bikeTotal + partTotal);
    }
    
   const deleteBike = (cartId) =>{
        axios.post(`http://localhost:8080/users/customer/removeBikeFromCart/${cartId}`)
            .then((response) => {
                getBikeData();
                
            })
            .catch((err) => {
                setMessage(err.data);
            });

    }

   const deletePart =(cartId) =>{
    axios.post(`http://localhost:8080/users/customer/removePartFromCart/${cartId}`)
    .then((response) => {
        getPartData();
        
    })
    .catch((err) => {
        console.log(err);
    });
   }

    useEffect(() => {
        getPartData();
        getBikeData();
    }, []);

    useEffect(() => {
        calculateTotalPrice();
    }, [bikes, parts]);

    return (
        <div className="container">
        <h3 className="text-black">Cart List Customer</h3>

        {/* 
         */}
         <div className="row">
                {bikes.map((bike) => (
                    <div key={bike.id} className="col-md-4 mb-4">
                        <div className="card">
                            
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
                                            <p className="card-text">Quantity: {bike.bikeQuantity}</p>
                                            <div className="container">
                                             <IncrementDecrementBikeBtn minValue={1} maxValue={3} cartId={bike.cartId} />
                                             </div>
                                             <div  key={bike.cartId} >
                                            <button type="button" class="btn btn-danger" onClick={deleteBike(bike.cartId)}>REMOVE</button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            
                        </div>
                    </div>
                ))}
                {parts.map((part) => (
                    <div key={part.id} className="col-md-4 mb-4">
                        <div className="card">
                            
                                <div className="row">
                                    <div className="col-md-6">
                                        <img src={part.imageURL} className="card-img-top" alt={part.name} />
                                    </div>
                                    <div className="col-md-6">
                                        <div className="card-body">
                                            <h5 className="card-title">{part.name}</h5>
                                            <p className="card-text">Quantity: {part.quantity}</p>
                                            <p className="card-text">Price: {part.price}</p>
                                            <div className="container">
                                            <IncrementDecrementPartBtn minValue={1} maxValue={3} cartId={part.cartId} />

                                            </div>
                                            <div   >
                                            <button type="button" class="btn btn-danger" onClick={deletePart(part.cartId)}>REMOVE</button>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                          
                        </div>
                    </div>
                ))}

            </div>
        

        <div>
            <p className="card-text">Total cart price: {totalPrice}</p>
        </div>

        <div>
            <Link>
                <Button className="btn btn-info">Order</Button>
            </Link>
        </div>

        <div className="alert alert-success">
            {message}
        </div>
    </div>
    );
}

export default Cart;
{/* <table className="table">
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                {[...bikes, ...parts].map((product) => (
                    <tr key={product.id}>
                        <td>
                            <img src={product.imageURL} alt={product.name} style={{ maxWidth: "50px", maxHeight: "50px" }} />
                        </td>
                        <td>{product.name}</td>
                        <td>
                            {/* <Form.Control
                                as="select"
                                value={product.bikeQuantity || product.partQuantity}
                                onChange={(e) => handleQuantityChange()}
                            >
                                {[1, 2, 3].map((quantity) => (
                                    <option key={quantity} value={quantity}>
                                        {quantity}
                                    </option>
                                ))}
                            </Form.Control> */}
            //                 <div className="container">
            //                 <IncrementDecrementBtn minValue={1} maxValue={3} />
            //                 </div>
            //             </td>
            //             <td>{product.price}</td>
            //         </tr>
            //     ))}
                
            // </tbody>
            // </table> */}
