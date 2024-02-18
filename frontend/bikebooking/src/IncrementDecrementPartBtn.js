import axios from "axios";
import React, { useState } from "react";



const IncrementDecrementBikeBtn = ({ minValue = 0, maxValue = 100 ,cartId }) => {
  const [count, setCount] = useState(minValue);
 // const cartId = sessionStorage.getItem("userId");

  const handleIncrementCounter = () => {
    if (count < maxValue) {
      setCount((prevState) => prevState + 1);
    }
    axios.put(`http://localhost:8080/users/customer/increasePartCount/${cartId}`)
    .then((response) => {
        
    })
    .catch((err) => {
        console.log(err);
    }); 
  };

  const handleDecrementCounter = () => {
    if (count > minValue) {
      setCount((prevState) => prevState - 1);
    }
    axios.put(`http://localhost:8080/users/customer/decreasePartCount/${cartId}`)
    .then((response) => {
       
    })
    .catch((err) => {
        console.log(err);
    });
  };

  return (
    <div className="btn-group">
      <button className="btn rounded " onClick={handleIncrementCounter} >
        <span class="btn btn-info" >+</span>
      </button>
      <p>{count}</p>
      <button className="btn" onClick={handleDecrementCounter}>
        <span class="btn btn-info">-</span>
      </button>
    </div>
  );
};

export default IncrementDecrementBikeBtn;