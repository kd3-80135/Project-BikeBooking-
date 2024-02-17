import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./styles/header.css"

function CustomerPartList() {
    const [parts, setPartList] = useState([]);
    const [message, setMessage] = useState("");
    const [currentPage, setCurrentPage] = useState(1);
    const [partsPerPage] = useState(3);
    


    function getData() {

        axios.get(`http://localhost:8080/users/customer/partList`)

            .then((response) => {
                setPartList(response.data)
            })

            .catch((err) => {
                console.log(err);
            });
    };

    useEffect(() => {
        getData();
    }, []);

    const indexOfLastPart = currentPage * partsPerPage;
    const indexOfFirstPart = indexOfLastPart - partsPerPage;
    const currentPart = parts.slice(indexOfFirstPart, indexOfLastPart);

    
    const paginate = (pageNumber) => setCurrentPage(pageNumber);


   
    return (
        <div className="container">
            <h3 className="text-black">Part List</h3>

            <div className="row">
                {currentPart.map((part) => (
                    <div key={part.id} className="col-md-4 mb-4">
                        <div className="card">
                            <Link to={`/partDetails?partId=${part.id}`} className="text-decoration-none text-black">
                                <div className="row">
                                    <div className="col-md-6">
                                        <img src={part.imageURL} className="card-img-top" alt={part.name} />
                                    </div>
                                    <div className="col-md-6">
                                        <div className="card-body">
                                            <h5 className="card-title">{part.name}</h5>
                                            <p className="card-text">Price: {part.price}</p>
                                            <p className="card-text">Quantity: {part.quantity}</p>
                                        </div>
                                    </div>
                                </div>
                            </Link>
                        </div>
                    </div>
                ))}

            </div>
            <ul className="pagination">
                {Array.from({ length: Math.ceil(parts.length / partsPerPage) }, (_, index) => (
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

export default CustomerPartList;