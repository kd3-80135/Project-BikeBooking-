import React from "react";
import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

function SetRole() {
  const role1 = { roledealer: 2 };
  const role2 = { rolecustomer: 1 };

  const roleCustomer = () => {
    axios.get(`http://localhost:8080/users/register${1}`);
  };

  const roleDealer = () => {
    axios.get(`http://localhost:8080/users/register${2}`);
  };

  return (
    <div className="container mt-5 text-center">
        <h1>Choose Your Role</h1>
      <div className="row row-cols-1 row-cols-md-2 justify-content-center g-4 mt-3">
      <div className="col text-white bg-primary rounded-3 py-4 px-5">
          <Link
            to={{ pathname: "/signup", state: { data: role1 } }}
            className="text-decoration-none"
          >
            <div className="card mb-3">
              <div className="card-body">
                <h5 className="card-title">SignUp as a Dealer</h5>
              </div>
            </div>
          </Link>
        </div>

        <div className="col text-white bg-secondary rounded-3 py-4 px-5">
          <Link
            to={{ pathname: "/signup", state: { data: role2 } }}
            className="text-decoration-none"
          >
            <div className="card mb-3">
              <div className="card-body">
                <h5 className="card-title">SignUp as a Customer</h5>
              </div>
            </div>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default SetRole;
