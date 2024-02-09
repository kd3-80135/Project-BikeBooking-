import axios from "axios";
import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

function EditAddress() {
const [userAdd, setUserAdd] = useState({
    houseNo:"",
    apartmentName:"",
    street:"",
    pincode:"",
    state:"",
    city:"",
    area:""
});

const[message, setMessage] = useState("")
const history = useHistory();

useEffect(()=>{
    const userID = sessionStorage.getItem("userId");
    axios
    .get(`http://localhost:8080/users/editAddress/${userID}`)
    .then((response)=> setUserAdd(response.data))
    .catch((error)=>console.error(error));
}, []);


const ShowMessage = (msgText)=>{
    setMessage(msgText);
    window.setTimeout(()=>{
        setMessage("");
    },3000);
}
const OnTextChange=(args)=>{
    var user1= {...userAdd};
    user1[args.target.name] = args.target.value;
    setUserAdd(user1);
}

const handleSubmit = (event) => {
    event.preventDefault();
    const userID = sessionStorage.getItem("userId");
    axios
      .put(`http://localhost:8080/users/updateAddress/${userID}`, userAdd)
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
                        <td> House No</td>
                        <td> <input type="text" name="houseNo" value={userAdd.houseNo} onChange={OnTextChange} ></input></td>
                    </tr>
                    <tr>
                        <td> Apartment Name</td>
                        <td> <input type="text" name="apartmentName" value={userAdd.apartmentName} onChange={OnTextChange}></input></td>
                    </tr>
                     <tr>
                        <td> Street</td>
                        <td> <input type="text" name="street" value={userAdd.street} onChange={OnTextChange}></input></td>
                    </tr> 
                    <tr>
                        <td> City </td>
                        <td> <input type="text" name="city" value={userAdd.city} onChange={OnTextChange} ></input></td>
                    </tr>
                    <tr>
                        <td> Area </td>
                        <td> <input type="text" name="area" value={userAdd.area} onChange={OnTextChange} ></input></td>
                    </tr>
                    <tr>
                        <td> Pincode </td>
                        <td> <input type="number" name="pincode" value={userAdd.pincode} onChange={OnTextChange} ></input></td>
                    </tr>
                    <tr>
                        <td> State </td>
                        <td> <input type="text" name="state" value={userAdd.state} onChange={OnTextChange} ></input></td>
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

export default EditAddress;