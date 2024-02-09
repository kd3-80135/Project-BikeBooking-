
import React from 'react';
import { Link, NavLink,useHistory } from 'react-router-dom'

//import "../style/header.css";
//import logo from "../images/logo.png"
import { Navbar,Nav,Container} from 'react-bootstrap';
import { useState } from 'react';
import Cart from './images/cart.jpg'
import './styles/cart.css'
import Dropdown from './DropDown';




const Header = () => {
  const history = useHistory();

  const name = sessionStorage.getItem("userName")
  const [selectedOption, setSelectedOption] = useState('');

  function autoRefresh() {
    window.location = window.location.href;
  }
  const onLogout = () => {

    loggedin = false;
    window.sessionStorage.removeItem("user");
    window.sessionStorage.removeItem("userID");
    window.sessionStorage.removeItem("role");
    window.sessionStorage.removeItem("isLoggedin")
    window.sessionStorage.removeItem("userName")

    history.push("/");
    autoRefresh();
  };

  var loggedin = false;
  var userid = sessionStorage.getItem("userID");
  var userName = sessionStorage.getItem("userName");

  if (userName!=null) {
    loggedin = true;
  }


  let role = sessionStorage.getItem("role");

  function handleNavigate(page) {
    window.location.href = page;
  }



  return (
    <div className='container-fluid'>
      <div className='header-most-top-left container-fluid '>
        <nav className="navbar navbar-expand-lg navbar-brand" style={{ marginTop: "-10px" }}>



          <div className="h-text para col-md-4 logo_agile">
            <span className='h-text1'><h1>Bike Spot</h1></span> <h4 className='h-text2'><sub></sub></h4></div>

          {/* <form className="d-flex" role="search">
            <input className="form-control me-2 " type="search" placeholder="Search Product" aria-label="Search" />
            <button className="btn btn-success" type="submit">Search</button>
          </form> */}

        </nav>
      </div>

      {/* <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand replace ="/">My Ecommerce Site </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
          
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar> */}

      <Navbar bg="light" expand="lg">
        <Container>


          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <ul className="navbar-nav moveul">

                {!loggedin && (
                  <li className="nav-item" aria-current="page">
                    <Link to="/">
                      <span className="nav-link active">Home</span>
                    </Link>
                  </li>)}

                {loggedin && role == "CUSTOMER_ROLE" && (
                  <li className="nav-item" aria-current="page">
                    <Link to="/user">
                      <span className="nav-link active">Home</span>
                    </Link>
                  </li>)}

                {loggedin && role == "ADMIN_ROLE" && (
                  <li className="nav-item" aria-current="page">
                    <Link to="/admin">
                      <span className="nav-link active">Home</span>
                    </Link>
                  </li>)}
                  {loggedin && role == "DEALER_ROLE" && (
                  <li className="nav-item" aria-current="page">
                    <Link to="/dealer">
                      <span className="nav-link active">Home</span>
                    </Link>
                  </li>)}

                <li className="nav-item">
                  <Link to="/about">
                    <span className="nav-link">About Us</span>
                  </Link>
                </li>

                <li className="nav-item">
                  <Link to="/contact">
                    <span className="nav-link">Contact Us</span>
                  </Link>
                </li>

                {loggedin && role == "CUSTOMER_ROLE" && (
                  <li className="nav-item">
                    <Link to="/showcart">
                      <span className="nav-link"><img src={Cart} className='A' alt='cart'/></span>
                    </Link>
                  </li>)}


                {loggedin && role == "CUSTOMER_ROLE" && (
                  <li className="nav-item">
                    <Link to="/myorders">
                      <span className="nav-link">Orders</span>
                    </Link>
                  </li>
                )}

                

              </ul>
            </Nav>
            <Nav>


              {!loggedin && (
                <li className="button">
                  <Link to="/signin">
                    <span className="nav-link">SignIn</span>
                  </Link>
                </li>
              )}
              {!loggedin && (
                <li className="signup">
                  <Link to="/setrole">
                    <span className="nav-link"> SignUp</span>
                    {/* <button type="button" className="btn btn-secondary btn-lg">
                    SignUp
                    </button> */}
                  </Link>
                </li>
              )}

                {/* {loggedin && role == "CUSTOMER_ROLE" && (
                  <li className="nav-item">
                    <Link to="/myprofile">
                      <span className="nav-link">{userName}</span>
                    </Link>
                  </li>
                )}   */}
              {loggedin && role == "CUSTOMER_ROLE" && (
                    <li className="nav-item">
                   <Dropdown />
                </li>
                )}

{loggedin && role == "DEALER_ROLE" && (
                    <li className="nav-item">
                   <Dropdown />
                </li>
                )}


              {loggedin && (
                <div className="logout">

                  <li className="nav-item">

                    <span onClick={onLogout} className="nav-link"><b><Link style={{ marginLeft: "20px" }} to="/"> Logout</Link></b></span>

                  </li>
                </div>
              )}




            </Nav>
          </Navbar.Collapse>

        </Container>
      </Navbar>
    </div>
  )
}




export default Header;








