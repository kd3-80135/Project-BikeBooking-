import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Cart() {
    const [bikes, setBikesList] = useState([]);
    const [parts, setPartsList] = useState([]);
    const [message, setMessage] = useState("");
    const [currentPage, setCurrentPage] = useState(1);
    const [cartsPerPage] = useState(3);

    var cartId = sessionStorage.getItem("userId");

    function getBikeData() {
        axios.get(`http://localhost:8080/users/customer/bikeList/${cartId}`)
            .then((response) => {
                setBikesList(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }
    function getPartData() {
        axios.get(`http://localhost:8080/users/customer/partList/${cartId}`)
            .then((response) => {
                setPartsList(response.data);
            })
            .catch((err) => {
                console.log(err);
            });
    }
   

    useEffect(() => {
        getBikeData();
        getPartData();
    }, []);

    // Pagination Logic
    const indexOfLastBike = currentPage * bikesPerPage;
    const indexOfFirstBike = indexOfLastBike - bikesPerPage;
    const currentBikes = bikes.slice(indexOfFirstBike, indexOfLastBike);
    

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="container">
            <h3 className="text-black">Bikes List Customer</h3>

            <div className="row">
                {currentBikes.map((bike) => (
                    <div key={bike.id} className="col-md-4 mb-4">
                        <div className="card">
                            <Link to={`/bikeDetails?bikeId=${bike.id}`} className="text-decoration-none text-black">
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
                                        </div>
                                    </div>
                                </div>
                            </Link>
                        </div>
                    </div>
                ))}
                {currentBikes.map((bike) => (
                    <div key={bike.id} className="col-md-4 mb-4">
                        <div className="card">
                            <Link to={`/bikeDetails?bikeId=${bike.id}`} className="text-decoration-none text-black">
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
                                        </div>
                                    </div>
                                </div>
                            </Link>
                        </div>
                    </div>
                ))}

            </div>
            <ul className="pagination">
                {Array.from({ length: Math.ceil(bikes.length / bikesPerPage) }, (_, index) => (
                    <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
                        <span className="page-link" onClick={() => paginate(index + 1)}>
                            {index + 1}
                        </span>
                    </li>
                ))}
            </ul>

            <div className="alert alert-success">
                {message}
            </div>
        </div>
    );
}

export default Cart;
