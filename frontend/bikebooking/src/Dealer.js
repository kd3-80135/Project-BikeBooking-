import React from "react";
import { Link } from "react-router-dom";


function Dealer() {
    return (
        <div className="container mt-5">
            
                    <div className="col text-white bg-primary rounded-3 py-4 px-5">
                        <Link
                        to={{ pathname: "/bikeList"}}
                        className="text-decoration-none"
                        >
                        <div className="card mb-3">
                        <div className="card-body">
                            <h5 className="card-title">Bike List</h5>
                        </div>
                        </div>
                    </Link>
                    </div>
                    <div className="col text-white bg-secondary rounded-3 py-4 px-5">
                        <Link
                        to={{ pathname: "/partList"}}
                        className="text-decoration-none"
                        >
                        <div className="card mb-3">
                        <div className="card-body">
                            <h5 className="card-title">Part List</h5>
                        </div>
                        </div>
                    </Link>
                    </div>
            
        </div>
    );
}

export default Dealer;
