import React from "react";
import { Link } from "react-router-dom";
import Bike from './images/bike2.jpg';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

function Admin() {
    return (
        <div className="container mt-5">
            <div className="row row-cols-1 row-cols-md-2 justify-content-center g-4 mt-3">
                
                     <div className="col text-white bg-primary rounded-3 py-4 px-5">
                        <Link
                        to={{ pathname: "/userlist"}}
                        className="text-decoration-none"
                        >
                        <div className="card mb-3">
                        <div className="card-body" >
                            <h5 className="card-title" >User Details</h5>
                        </div>
                        </div>
                    </Link>
                    </div>

                    <div className="col text-white bg-info rounded-3 py-4 px-5">
                        <Link
                        to={{ pathname: "/bikes"}}
                        className="text-decoration-none"
                        >
                        <div className="card mb-3">
                        <div className="card-body">
                            <h5 className="card-title">Bike Details</h5>
                        </div>
                        </div>
                    </Link>
                    </div>
                    <div className="col text-white bg-success rounded-3 py-4 px-5">
                        <Link
                        to={{ pathname: "/parts"}}
                        className="text-decoration-none"
                        >
                        <div className="card mb-3">
                        <div className="card-body">
                            <h5 className="card-title">Part Details</h5>
                        </div>
                        </div>
                    </Link>
                    </div>
                    <div className="col text-white bg-secondary rounded-3 py-4 px-5">
                        <Link
                        to={{ pathname: "/adminorder"}}
                        className="text-decoration-none"
                        >
                        <div className="card mb-3">
                        <div className="card-body">
                            <h5 className="card-title">Order Details</h5>
                        </div>
                        </div>
                    </Link>
                    </div>
                
                
            </div>

            <div className="container">
               
                
            </div>
        </div>
    );
}

export default Admin;
