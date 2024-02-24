import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { useHistory } from "react-router-dom";
import { Button } from "react-bootstrap";


const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const [emailSent, setEmailSent] = useState(false);
  const [error, setError] = useState("");
  const [otp, setOtp] = useState("");
  const history = useHistory();
  
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
    setError("");
  };

  const handleOtpChange =(e) =>{
    setOtp(e.target.value);

  }
  const handleSendOTP = (e) => {
    e.preventDefault();

    if (!email) {
      setError("Please enter your email");
      return;
    }

    axios
      .post(`http://localhost:8080/users/forgotPassword/${email}`)
      .then((response) => {
        setEmailSent(true);
        console.log(response.data);
      })
      .catch((error) => {
        setError("Failed to send reset email. Please try again.");
        console.error(error);
      });
  };

  function verifyOtp(e){
    // e.preventDefault();

    axios.post(`http://localhost:8080/users/verifyOTP/${otp}/${email}`)
    .then((response)=>{
        if(response.status ==200){
            history.push('/resetPassword')
        }
        
        else{
            history.push('/forgotpassword')
        }
        
    })
  }

  return (
    <div className="container">
      <div className="row justify-content-center mt-5">
        <div className="col-lg-6 col-md-8">
          <div className="card shadow">
            <div className="card-body p-5">
              <h2 className="text-center mb-4">Forgot Password</h2>
              {emailSent ? (<div>
                
                <Button onClick={verifyOtp} className="btn btn-primary">
                  Verify OTP
                </Button>
                <input  type="text"
                      id="otp"
                      className="form-control"
                      
                      onChange={handleOtpChange}
                      required  />
              </div>
 
              ) : (
                <form onSubmit={handleSendOTP}>
                  <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                      Email address
                    </label>
                    <input
                      type="email"
                      id="email"
                      className="form-control"
                      value={email}
                      onChange={handleEmailChange}
                      required
                    />
                  </div>

                  {error && <div className="text-danger mb-3">{error}</div>}

                  <div className="d-grid gap-2">
                    <button type="submit" className="btn btn-primary">
                      Send OTP
                    </button>
                  </div>

                  <div className="mt-3 text-center">
                    <Link to="/signin" className="text-muted">
                      Back to Sign In
                    </Link>
                  </div>
                </form>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ForgotPassword;
