import React, { useEffect, useState } from "react";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import SetRole from "./SetRole";

function SignUp({ location }) {
    var roleReceived = location.state.data;
    console.log(roleReceived.roledealer);

    const [user, setUser] = useState({ firstName: "", lastName: "", email: "", password: "", mobile: "", role: "" });
    const [message, setMessage] = useState("");
    var url = "http://localhost:8080/users/register";

    const updateUrl = () => {
        if (roleReceived.roledealer === 2) {
            url = `http://localhost:8080/users/register/${roleReceived.roledealer}`
        } else {
            url = `http://localhost:8080/users/register/${roleReceived.rolecustomer}`
        }
    }
    updateUrl();

    const OnTextChange = (args) => {
        var user1 = { ...user };
        user1[args.target.name] = args.target.value;
        setUser(user1);
    }

    const ClearBoxes = () => {
        setUser({ firstName: "", lastName: "", email: "", password: "", mobile: "", role: "" });
    }

    const ShowMessage = (msgText) => {
        setMessage(msgText);
        window.setTimeout(() => {
            setMessage("");
        }, 3000);
    }

    const AddRecord = () => {
        axios.post(url, user).then((result) => {
            if (result.data !== undefined) {
                ClearBoxes();
                ShowMessage("Record Added Successfully");
            }
        });
    }

    useEffect(() => {
        console.log("Some state change did update the UI");
    }, [user, message]);

    return (
        
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-body">
                            <h2 className="card-title text-center mb-4">Create an Account</h2>
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="firstName" className="form-label">First Name</label>
                                    <input type="text" name="firstName" value={user.firstName} onChange={OnTextChange} className="form-control" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="lastName" className="form-label">Last Name</label>
                                    <input type="text" name="lastName" value={user.lastName} onChange={OnTextChange} className="form-control" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label">Email</label>
                                    <input type="email" name="email" value={user.email} onChange={OnTextChange} className="form-control" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label">Password</label>
                                    <input type="password" name="password" value={user.password} onChange={OnTextChange} className="form-control" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="mobile" className="form-label">Mobile</label>
                                    <input type="text" name="mobile" value={user.mobile} onChange={OnTextChange} className="form-control" />
                                </div>
                                <div className="text-center">
                                    <button type="button" className="btn btn-primary" onClick={AddRecord}>
                                        Register
                                    </button>
                                </div>
                            </form>
                            {message && <div className="alert alert-success mt-3">{message}</div>}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SignUp;
