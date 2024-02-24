import React from "react";
import { Link } from "react-router-dom";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import CustomerBikeList from "./CustomerBikeList";

function User() {
    return (
        <div className="container mt-5">
            <div className="row mb-4" onLoad={CustomerBikeList}>
                
                
            </div>

            {/* <div className="row">
                <div className="col-md-6">
                    <div className="card p-3">
                        <h3 className="mb-4">Explore Our Products</h3>
                        <form>
                            <div className="mb-3">
                                <label htmlFor="search" className="form-label">Search Bikes or Parts:</label>
                                <input type="text" className="form-control" id="search" />
                            </div>
                            <button type="submit" className="btn btn-info">Search</button>
                        </form>
                    </div>
                </div>
                <div className="col-md-6">
                    <aside className="card p-3">
                        <h5>Advertisement</h5>
                        <p>Special deals and discounts!</p>
                    </aside>
                </div>
            </div> */}
        </div>
    );
}

export default User;
