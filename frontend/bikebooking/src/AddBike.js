import React, { useEffect, useState } from "react";
import axios from "axios";

function AddBike() {
    var id = sessionStorage.getItem("userId");

    const [bikes, setBikes] = useState([]);
    const [bike, setBike] = useState({firstName:"", lastName:"", email:"", password:"", mobile:"", role:""});
    const [message, setMessage] = useState("")
    var url = "http://localhost:8080/users/register";
    const updateUrl=()=>{
        if (roleRecieved.roledealer == 2){
            url = `http://localhost:8080/users/register/${roleRecieved.roledealer}` 
        }
        else{
            url = `http://localhost:8080/users/register/${roleRecieved.rolecustomer}`
        }
    }
    updateUrl();

    
    const OnTextChange=(args)=>{
        var user1= {...user};
        user1[args.target.name] = args.target.value;
        setUser(user1);
        
    }

    const ClearBoxes = ()=>{
        setUser({firstName:"", lastName:"", email:"", password:"", mobile:"", role:""});
    }

    
    const ShowMessage = (msgText)=>{
        setMessage(msgText);
        window.setTimeout(()=>{
            setMessage("");
        },3000);
    }

    const AddRecord = ()=>{
        
        axios.post(url,user).then((result)=>{
            if (result.data !== undefined){
                ClearBoxes();
                ShowMessage("Record Added Successfully");
            }
        });
    }

    useEffect(()=>{
        console.log("Some state change did update the UI");

    },[users,user,message]);

    return (
        <div className="container">
            <hr></hr>
            <div className="table-respinsive" >
                <table className="table table-bordered">
                    <tbody>
                        <tr>
                            <td> First Name</td>
                            <td> <input type="text" name="firstName" value={user.firstName} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Last Name</td>
                            <td> <input type="text" name="lastName" value={user.lastName} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Email</td>
                            <td> <input type="email" name="email" value={user.email} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Password </td>
                            <td> <input type="password" name="password" value={user.password} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Mobile </td>
                            <td> <input type="text" name="mobile" value={user.mobile} onChange={OnTextChange}></input></td>
                        </tr>
                            
                        <tr>
                            <td></td>
                            <td> 
                                <button className="btn btn-primary" onClick={AddRecord}>
                                    Register
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
}

export default AddBike;