import React from "react";
import { Link } from "react-router-dom";


function Dealer() {
    return (
        <div className="container mt-5">
            <div className="mb-3">
                <Link to='/bikeList' className="btn btn-primary">Bike List</Link>
            </div>
            <div>
                <Link to='/partList' className="btn btn-success">Part List</Link>
            </div>
        </div>
    );
}

export default Dealer;
