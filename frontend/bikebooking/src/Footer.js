import React from "react";
import { Link } from "react-router-dom";

import '../node_modules/font-awesome/css/font-awesome.min.css'

function Footer() {
  return (
    <div className="footer-container">
      <div className="blockcode">
        <footer className="bg-light text-center text-dark p-4">
          <div className="container">
            <ul className="nav justify-content-center mb-3">
              <li className="nav-item">
                <Link to="/about" className="nav-link">
                  About Us
                </Link>
              </li>
            </ul>

            {/* Social Media Icons */}
            <div className="float-right">
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#3b5998" }}
                role="button"
              >
                <i className="fa fa-facebook" />
              </a>
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#55acee" }}
                role="button"
              >
                <i className="fa fa-twitter" />
              </a>
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#dd4b39" }}
                role="button"
              >
                <i className="fa fa-google" />
              </a>
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#ac2bac" }}
                role="button"
              >
                <i className="fa fa-instagram" />
              </a>
            </div>

            {/* Copyright */}
            <div className="text-center">
              <p className="mb-0">
                © 2023-2024 Copyright:{" "}
                <a className="text-dark" href="https://en.wikipedia.org/wiki/All_rights_reserved">
                  All Rights Reserved
                </a>
              </p>
            </div>
          </div>
        </footer>
      </div>
    </div>
  );
}

export default Footer;
