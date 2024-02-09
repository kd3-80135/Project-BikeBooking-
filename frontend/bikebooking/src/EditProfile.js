import axios from "axios";
import React from "react";
import { useState,useEffect } from "react";
import { useHistory } from "react-router-dom";




function EditProfile() {
    const [userData, setUserData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        mobileno: "",
      });
      const [message, setMessage] = useState("")
      const history = useHistory();
      useEffect(() => {
        // Get the user data from the server based on the userID in session storage
        const userID = sessionStorage.getItem("userId");
        axios
          .get(`http://localhost:8080/users/edit/${userID}`)
          .then((response) => setUserData(response.data))
          .catch((error) => console.error(error));
      }, []);
      const ShowMessage = (msgText)=>{
        setMessage(msgText);
        window.setTimeout(()=>{
            setMessage("");
        },3000);
    }
      
    const OnTextChange=(args)=>{
        var user1= {...userData};
        user1[args.target.name] = args.target.value;
        setUserData(user1);
        
    }
      const handleSubmit = (event) => {
    event.preventDefault();
    const userID = sessionStorage.getItem("userId");
    axios
      .put(`http://localhost:8080/users/update/${userID}`, userData)
      .then(() => console.log("User data updated successfully.")).catch((error) => console.error(error));
    //   history.push('/myprofile')
      ShowMessage("update Successfully")

      
  };
  
    return ( 
        <div className="container" >
        <hr></hr>
        <div className="table-respinsive" >
            <table className="table table-bordered">
                <tbody>
                    <tr>
                        <td> First Name</td>
                        <td> <input type="text" name="firstName" value={userData.firstName} onChange={OnTextChange} ></input></td>
                    </tr>
                    <tr>
                        <td> Last Name</td>
                        <td> <input type="text" name="lastName" value={userData.lastName} onChange={OnTextChange}></input></td>
                    </tr>
                     <tr>
                        <td> Email</td>
                        <td> <input type="email" name="email" value={userData.email} onChange={OnTextChange}></input></td>
                    </tr> 
                    <tr>
                        <td> Password </td>
                        <td> <input type="password" name="password" value={userData.password} onChange={OnTextChange} ></input></td>
                    </tr>
                    <tr>
                        <td> Mobile </td>
                        <td> <input type="text" name="mobile" value={userData.mobile} onChange={OnTextChange} ></input></td>
                    </tr>
                        
                    <tr>
                        <td></td>
                        <td> 
                            <button className="btn btn-primary" onClick={handleSubmit}>
                                Submit
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div className="alert alert-success">
                {message}
            </div>
        </div>
    </div>
     );
    };

export default EditProfile ;