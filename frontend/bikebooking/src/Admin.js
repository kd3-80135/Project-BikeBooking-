import React from "react";
import { Link } from "react-router-dom";
import Bike from './images/bike2.jpg';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

function Admin() {
    return (
        <div className="container mt-5">
            <div className="row mb-4">
                <div className="col">
                    <Link to='/userlist' className="btn btn-primary">User Details</Link>
                </div>
                <div className="col">
                    <Link to='/bikes' className="btn btn-success">Bike Details</Link>
                </div>
                <div className="col">
                    <Link to='/parts' className="btn btn-info">Part Details</Link>
                </div>
            </div>

            <div className="row">
                <div className="col-md-6">
                    <img src={Bike} alt="Login image" className="w-100 vh-50 loginimg" style={{ objectPosition: 'right' }} />
                </div>
                
            </div>
        </div>
    );
}

export default Admin;
