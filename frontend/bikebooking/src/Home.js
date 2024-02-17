import React from "react";
import bike from './images/index.jpg';
import './styles/cart.css'
import Gif from './images/gifbike2.gif'



function Home() {
    return (
        <div className="Landing2_Landin"
       >
            <img
                src={bike}
                alt="Login image"
                className="w-100 vh-70 loginimg styled-image"
                style={{ objectFit: 'cover', objectPosition: '50% 50%' }}
            />
            <div className="overlay"></div>
            <div className="content">
                <h1 className="text-white">Welcome to Our Website</h1>
                <p className="text-white">Explore the world of bikes with us!</p>
                <button className="btn btn-primary">Get Started</button>
            </div>
        </div>
    );
}

export default Home;
