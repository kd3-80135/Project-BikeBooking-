import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";


function ResetPassword() {
    
    const [confirmpassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const [user, setUser] = useState({  email: "", password: ""  })
    const history = useHistory();

    const handleEmailChange = (e) => {
        var user1 = { ...user };
        user1[e.target.name] = e.target.value;
        setUser(user1);
      };

      const handlePasswordChange = (e) => {
        var user1 = { ...user };
        user1[e.target.name] = e.target.value;
        setUser(user1);
      };

      const handleConfirmPasswordChange = (e) => {
       
        setConfirmPassword(e.target.value);
      };
      const AddRecord = () => {
        axios.post(`http://localhost:8080/users/resetPassword`, user).then((result) => {
            if (result.data !== undefined) {
                history.push('/signin')
            }
        });
    }   

    return ( 
        <div>
             <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                      Email address
                    </label>
                    <input
                      type="email"
                      id="email"
                      className="form-control"
                      name="email"
                      onChange={handleEmailChange}
                      required
                    />
                  </div>
                  <label htmlFor="password" className="form-label">
                      Password
                    </label>
                    <input
                      type="password"
                      id="password"
                      className="form-control"
                      name="password"
                      onChange={handlePasswordChange}
                      required
                    />
                  
                  <div>
                  <label htmlFor="confirmpassword" className="form-label">
                  Confirm Password
                </label>
                <input
                  type="password"
                  id="confirmpassword"
                  className="form-control"
                  name=""
                  onChange={handleConfirmPasswordChange}
                  required
                />
              </div>
              <div className="text-center">
                                    <button type="button" className="btn btn-primary" onClick={AddRecord}>
                                        Submit
                                    </button>
                                </div>
        </div>
     );
}

export default ResetPassword;