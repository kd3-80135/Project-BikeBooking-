import React, { useEffect, useState } from 'react';
import bike from './images/Moto.jpg';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [user, setUser] = useState([]);
  const [status, setStatus] = useState([]);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const history = useHistory();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!email) {
      setEmailError(true);
      setErrorMessage("Please enter your email");
      return;
    }
    if (!password) {
      setPasswordError(true);
      setErrorMessage("Please enter your password");
      return;
    }
    axios.post("http://localhost:8080/users/signin", {
      email,
      password
    })
      .then((response) => {
        setUser(response.data);
        setStatus(response.status);
        console.log(response);
        console.log(user);
      })
      .catch((error) => {
        setErrorMessage("Invalid User Name or Password");
      });
  };

  useEffect(() => {
    if (status === 202 && user.role === "ADMIN_ROLE") {
      history.push("/admin");
      window.location = window.location.href;
      sessionStorage.setItem("userName", user.firstName);
      sessionStorage.setItem("userId", user.id);
      sessionStorage.setItem("isLoggedin", "true");
      sessionStorage.setItem("role", user.role);
      console.log("in admin");
    } 
    else if (status === 202 && user.role === "DEALER_ROLE") {
      history.push("/dealer");
      window.location = window.location.href;
      sessionStorage.setItem("userName", user.firstName);
      sessionStorage.setItem("userId", user.id);
      sessionStorage.setItem("isLoggedin", "true");
      sessionStorage.setItem("role", user.role);
      console.log("in dealer");
    } 
    else if (status === 202 && user.role === "CUSTOMER_ROLE") {
      history.push("/customerbikelist");
      window.location = window.location.href;
      sessionStorage.setItem("userName", user.firstName);
      sessionStorage.setItem("userId", user.id);
      sessionStorage.setItem("isLoggedin", "true");
      sessionStorage.setItem("role", user.role);
      console.log("in customer");
    } else if (status === 400) {
      setErrorMessage("Invalid User Name or Password")
    } else if (user && user.status === "error") {
      alert("Invalid Email or Password");
    }
  }, [user]);

  return (
    <div className="w-100 vh-100 d-flex justify-content-center align-items-center"
      style={{
        backgroundImage: `url(${bike})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
      }}
    >
      <div className="bg-white p-5 rounded shadow-lg" style={{ width: "400px" }}>
        <h2 className="text-center mb-4">Sign In to BS Account</h2>
        <form>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email address</label>
            <input
              type="email"
              id="email"
              className={`form-control ${emailError ? 'is-invalid' : ''}`}
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
                setEmailError(false);
                setErrorMessage("");
              }}
              required
            />
            {emailError && <div className="invalid-feedback">{errorMessage}</div>}
          </div>

          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              type="password"
              id="password"
              className={`form-control ${passwordError ? 'is-invalid' : ''}`}
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
                setPasswordError(false);
                setErrorMessage("");
              }}
              required
            />
            {passwordError && <div className="invalid-feedback">{errorMessage}</div>}
          </div>

          <div className="mb-3 text-danger">
            {errorMessage}
          </div>

          <div className="d-grid gap-2">
            <button onClick={handleSubmit} className="btn btn-primary" type="button">Sign In</button>
          </div>

          <div className="mt-3 text-center">
            <a className="text-muted" href="/ForgotPassword">Forgot password?</a>
          </div>

          <div className="mt-4 text-center">
            <p>Don't have an account? <a href="/setrole" className="link-info">Register here</a></p>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignIn;
