import React from 'react';
import { Link, useHistory } from 'react-router-dom';
import { Navbar, Nav, Container } from 'react-bootstrap';
import { useState } from 'react';
import Cart from './images/cart.jpg';
import DropDown from './DropDown';
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

    <div className='container-fluid' >
      <div className='header-most-top-left container-fluid'>
        <Navbar expand='lg'>
          <Container>
            <Navbar.Brand as={Link} to='/'>
              <img src='./bikelogo2.jpg' style={{ width: '50px', height: 'auto' }} alt="Bike Logo" />
            </Navbar.Brand>
            
          </Container>
        </Navbar>
      </div>
<div  className='header-container'></div>
      <Navbar bg='light' expand='lg' >
        <Container >
          <Navbar.Toggle aria-controls='basic-navbar-nav' />
          <Navbar.Collapse id='basic-navbar-nav'>
            <Nav className='me-auto'>
              <ul className='navbar-nav moveul'>
                {!loggedin && (
                  <li className='nav-item'>
                    <Link to='/'>
                      <span className='nav-link active'><img src="./home.png" style={{width:25}}/></span>
                    </Link>
                  </li>
                )}
                 {loggedin && role == "CUSTOMER_ROLE" && (
                  <li className="nav-item" aria-current="page">
                    <Link to="/customerbikelist">
                      <span className="nav-link active"><img src="./home.png" style={{width:25}}/></span>
                    </Link>
                  </li>)}

                {loggedin && role == "ADMIN_ROLE" && (
                  <li className="nav-item" aria-current="page">
                    <Link to="/admin" >
                      <span className="nav-link active"><img src="./home.png" style={{width:25}}/></span>
                    </Link>
                  </li>)}
                {loggedin && role == "DEALER_ROLE" && (
                  <li className='nav-item' aria-current='page'>
                    <Link to={`/dealer`} style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>
                      <span className='nav-link active'><img src="./home.png" style={{width:25}}/></span>
                    </Link>
                  </li>
                )}
                <li className='nav-item'>
                  <Link to='/aboutus' style={{textDecoration:'none'}}>
                    <span className='nav-link'> <div style={{font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>ABOUT US</div></span>
                  </Link>
                </li>
                {loggedin && role === 'CUSTOMER_ROLE' && (
                  <li className='nav-item'>
                    <Link to='/cart' >
                      <span className='nav-link'>
                        <img src={Cart} className='A' alt='cart' title='cart' style={{width:'35px',height:'35px'}}  />
                      </span>
                    </Link>
                  </li>
                )}
                {loggedin && role === 'CUSTOMER_ROLE' && (
                  <li className='nav-item'>
                    <Link to='/customerbikelist' style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>
                      <span className='nav-link'>
                        BIKES
                      </span>
                    </Link>
                  </li>
                )}
                {loggedin && role === 'CUSTOMER_ROLE' && (
                  <li className='nav-item'>
                    <Link to='/customerpartlist' style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>
                      <span className='nav-link'>
                        PARTS
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
                    <Link to='/signin' style={{textDecoration:'none'}}>
                      <span className='nav-link' style={{font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>SignIn</span>
                    </Link>
                  </li>
                  <li className='signup'>
                    <Link to='/setrole' style={{textDecoration:'none'}}>
                      <span className='nav-link' style={{font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>SignUp</span>
                    </Link>
                  </li>
                </>
              )}
              {loggedin && (
                <>
                  <li className='nav-item'>
                    {role === 'CUSTOMER_ROLE' || role === 'DEALER_ROLE' ? (
                      <DropDown />
                    ) : (
                      <Link to='/myprofile' style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>
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
