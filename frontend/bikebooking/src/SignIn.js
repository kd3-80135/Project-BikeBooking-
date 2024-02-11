import React, { useEffect, useState } from 'react'
import bike from './images/image.jpg'
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

import  axios  from 'axios';

const SignIn = () => {
  const[email,setEmail]=useState("");
  const[password,setPassword]=useState("");
  const[user ,setUser] = useState([]);
  const[status,setStatus] = useState([]);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
 const history=useHistory();

  


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
      console.log(response)
      console.log(user)
      
      
    })
    .catch((error) => {
      // handle sign-in error
      setErrorMessage(error.message);
    });
};
console.log(user)
//debugger;
  useEffect(() => {
   // history.push("/user"); 
  if ( status === 202 &&
  user.role === "ADMIN_ROLE") {
history.push("/admin");
window.location = window.location.href;
sessionStorage.setItem("userName",user.firstName)
sessionStorage.setItem("userId",user.id)
sessionStorage.setItem("isLoggedin","true")
sessionStorage.setItem("role", user.role);
console.log("in admin")     
}

else if (status === 202 &&
user.role === "DEALER_ROLE") {
 history.push("/dealer");
 window.location = window.location.href;
 sessionStorage.setItem("userName",user.firstName)
 sessionStorage.setItem("userId",user.id)
sessionStorage.setItem("isLoggedin","true")
sessionStorage.setItem("role", user.role)  
 console.log("in dealer") 
 }

else if ( status === 202 &&
       user.role === "CUSTOMER_ROLE"){
history.push("/user");
window.location = window.location.href;
sessionStorage.setItem("userName",user.firstName)
sessionStorage.setItem("userId",user.id)
sessionStorage.setItem("isLoggedin","true")
sessionStorage.setItem("role", user.role) 
console.log("in customer")       
}

else if(status === 400){
  alert(user.message);
  console.log(user);
}

else if (user && user.status === "error") {
alert(user.error);
};
  }, [user]);

 

  // console.log(user.data.id);

  return (
    <div>
    <section className="vh-100">
<div className="container-fluid  ">
  <div className="row">
    <div className="col-sm-6 text-black">
      <div className="px-5 ms-xl-4">
        <i
          className="fas fa-crow fa-2x me-3 pt-5 mt-xl-4"
          style={{ color: "#709085" }}
        />
      </div>
      <div className="d-flex align-items-center h-custom-2 px-5 ms-xl-4  pt-5 pt-xl-0 mt-xl-n5">
        <form style={{ width: "23rem" }}>
          <h3 className="fw-normal mb-3 pb-3" style={{ letterSpacing: 1 }}>
            Log in
          </h3>

          <div className="form-outline mb-4">
            <input
            onChange =  { (e) => {
              setEmail(e.target.value);
              setEmailError(false);
              setErrorMessage("");
              
            }}
              type="email"
              id="form2Example18"
              className="form-control form-control-lg"
              required

            />
            {emailError && <div className="error-message text-danger">{errorMessage}</div>}
            <label className="form-label" htmlFor="form2Example18">
              Email address
            </label>
          </div>
          <div className="form-outline mb-4">
            <input
               onChange={(e) => {
                setPassword(e.target.value);
                setPasswordError(false);
                setErrorMessage("");
              }}
              type="password"
              id="form2Example28"
              className="form-control form-control-lg"
              required
            />
            {passwordError && <div className="error-message text-danger">{errorMessage}</div>}
            <label className="form-label" htmlFor="form2Example28">
              Password
            </label>
          </div>
          <div>
              
          </div>
          <div className="form-group my-3">
            <button onClick={handleSubmit} className="btn btn-info btn-lg btn-block" value="submit">
            
              Login 
            </button>
          </div>
          <p className="small mb-5 pb-lg-2">
            <a className="text-muted" href="/">
              Forgot password?
            </a>
          </p>
          <p>
            Don't have an account?{" "}
            <a href="/setrole" className="link-info">
              Register here
            </a>
          </p>
        </form>
      </div>
    </div>
    <div className="col-sm-6 px-0 d-none d-sm-block">
      <img
     src={bike} 
       alt="Login image" className="w-50 vh-50 loginimg " style={{objectFit: 'cover', objectPosition: 'left'}} />
    </div>
  </div>
</div>
</section>
<br /><br /> <br /><br /> <br /><br /> <br /><br />
  </div>
  )
}

export default SignIn;