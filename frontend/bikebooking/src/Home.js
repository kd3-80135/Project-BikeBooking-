import React from "react";
import bike from './images/image.jpg';
import bike1 from './images/gifbike2.gif'
import './styles/cart.css'



function Home() {
    return (
        <div className="Landing2_Landin">
            <img
                src={bike1}
                alt="Login image"
                className="w-100 vh-70 login img styled-image"
                style={{ objectFit: 'cover', objectPosition: '50% 50%' }}
            />
            <div className="overlay"></div>
            <div className="content">
                <h1 className="text-white">Welcome to BIKE SPOT</h1>
                <p className="text-white">Explore the world of bikes with us!</p>
                
            </div>
        </div>
    );
}

export default Home;
