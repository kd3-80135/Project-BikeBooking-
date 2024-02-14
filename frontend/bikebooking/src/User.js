import React from "react";
import { Link } from "react-router-dom";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

function User() {
    return (
        <div className="container mt-5">
            <div className="row mb-4">
                <div className="col">
                    <Link to='/customerbikelist' className="btn btn-primary">Bike List</Link>
               <br/>
               <br/>
               <br/>
               <Link to='/customerpartlist' className="btn btn-success">Part List</Link>
                
                </div>
                <div className="col">
                    
                    </div>
            </div>

            <div className="row">
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
            </div>
        </div>
    );
}

export default User;
