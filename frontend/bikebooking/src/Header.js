import React from 'react';
import { Link, useHistory } from 'react-router-dom';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { useState } from 'react';
import Cart from './images/cart.jpg';
import Dropdown from './Dropdown';
import bike from './images/images.png';
import './styles/header.css'


const Header = () => {
  const history = useHistory();
  const name = sessionStorage.getItem('userName');
  const [selectedOption, setSelectedOption] = useState('');

  function autoRefresh() {
    window.location = window.location.href;
  }

  const onLogout = () => {
    window.sessionStorage.clear();
    history.push('/');
    autoRefresh();
  };

  const loggedin = !!sessionStorage.getItem('userName');
  const role = sessionStorage.getItem('role');
  const userName = sessionStorage.getItem('userName');

  return (

    <div className='container-fluid'>
      <div className='header-most-top-left container-fluid'>
        <Navbar expand='lg'>
          <Container>
            <Navbar.Brand as={Link} to='/'>
              <img src={bike} style={{ width: '50px', height: 'auto' }} alt="Bike Logo" />
            </Navbar.Brand>
            
          </Container>
        </Navbar>
      </div>
<div  className='header-container'></div>
      <Navbar bg='light' expand='lg'>
        <Container>
          <Navbar.Toggle aria-controls='basic-navbar-nav' />
          <Navbar.Collapse id='basic-navbar-nav'>
            <Nav className='me-auto'>
              <ul className='navbar-nav moveul'>
                {!loggedin && (
                  <li className='nav-item'>
                    <Link to='/'>
                      <span className='nav-link active'>Home</span>
                    </Link>
                  </li>
                )}
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
                  <li className='nav-item' aria-current='page'>
                    <Link to={`/dealer`}>
                      <span className='nav-link active'>Home</span>
                    </Link>
                  </li>
                )}
                <li className='nav-item'>
                  <Link to='/contact'>
                    <span className='nav-link'>Contact Us</span>
                  </Link>
                </li>
                {loggedin && role === 'CUSTOMER_ROLE' && (
                  <li className='nav-item'>
                    <Link to='/cart'>
                      <span className='nav-link'>
                        <img src={Cart} className='A' alt='cart' title='cart' width={"35px"}  />
                      </span>
                    </Link>
                  </li>
                )}
              </ul>
            </Nav>
            <Nav>
              {!loggedin && (
                <>
                  <li className='button'>
                    <Link to='/signin'>
                      <span className='nav-link'>SignIn</span>
                    </Link>
                  </li>
                  <li className='signup'>
                    <Link to='/setrole'>
                      <span className='nav-link'>SignUp</span>
                    </Link>
                  </li>
                </>
              )}
              {loggedin && (
                <>
                  <li className='nav-item'>
                    {role === 'CUSTOMER_ROLE' || role === 'DEALER_ROLE' ? (
                      <Dropdown />
                    ) : (
                      <Link to='/myprofile'>
                        <span className='nav-link'>{userName}</span>
                      </Link>
                    )}
                  </li>
                  <div className='logout'>
                    <li className='nav-item'>
                      <span onClick={onLogout} className='nav-link'>
                        <b>
                          <Link style={{ marginLeft: '20px' }} to='/'>
                            Logout
                          </Link>
                        </b>
                      </span>
                    </li>
                  </div>
                </>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

export default Header;
