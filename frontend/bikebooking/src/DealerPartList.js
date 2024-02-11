import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function DealerPartList() {
    const [parts, setPartList] = useState([]);
    const [message, setMessage] = useState("");
var id= sessionStorage.getItem("userId");

    function getData() {

        axios.get(`http://localhost:8080/users/dealer/partList/${id}`)

            .then((response) => {
                setPartList(response.data)
            })

            .catch((err) => {
                console.log(err);
            });
    };
    const ShowMessage = (msgText) => {
        setMessage(msgText);
        window.setTimeout(() => {
            setMessage("");
        }, 3000);
    }
    useEffect(() => {
        getData();
    }, []);


    function handleDelete(id) {
        axios.delete(`http://localhost:8080/users/dealer/deletePart/${id}`)
            .then(() => {
                getData();
                ShowMessage("record added successfully");
                //if i click on delete button it will deleted the products from dealer & also from customers
            }).catch((err) => {
                console.log(err)
            });
    }

    function handleApprove(id) {
        axios.put(`http://localhost:8080/users/admin/approvePart/${id}`)
            .then(() => {
                getData();
                ShowMessage("approved successfully");
                //if i click on approve button it the approve status from false to true
            }).catch((err) => {
                console.log(err)
            });
    }
    function handleUnApprove(id) {
        axios.put(`http://localhost:8080/users/admin/disprovePart/${id}`)
            .then(() => {
                getData();
                ShowMessage("disapproved successfully");
                //if i click on approve button it the approve status from false to true
            }).catch((err) => {
                console.log(err)
            });
    }
    return (
        <div className="container">
            <h3>Part List</h3>
            <table className="table table-responsive" >
                <thead>
                    <tr >
                        <th>ID</th>
                        <th>Part Name</th>
                        <th>Quantity</th>
                        <th>ApproveStatus</th>
                        <th>Delete Status</th>

                    </tr>
                </thead>
                <tbody>
                    {parts.map((parts) => (
                        <tr >
                            <td>{parts.id}</td>
                            <td>{parts.name}</td>
                            <td>{parts.quantity}</td>
                            <td>{parts.approveStatus === 0 ? "Yes" : "No"}</td>
                            <td>{parts.deleteStatus === 0 ? "Yes" : "No"}</td>
                            <td>
                                <button className='btn btn-danger' onClick={() => {
                                    if (window.confirm('Are You Sure To Delete Data ??')) { handleDelete(parts.id) }
                                }}>Delete</button>
                            </td>
                            <td>
                                {/* <Link to='/blockuser'>
                    <button className='btn btn-primary' >BLOCK</button>
                  </Link> */}
                                {parts.approveStatus == 0 && (
                                    <button className="btn btn-info"
                                        onClick={() => {
                                            if (window.confirm('Are You Sure To Approve Data ??')) { handleApprove(parts.id) }
                                        }}>Approve</button>

                                )}
                                {parts.approveStatus == 1 && (

                                    <button className="btn btn-info"
                                        onClick={() => {
                                            if (window.confirm('Are You Sure To UnApprove  Data ??')) { handleUnApprove(parts.id) }
                                        }}>UnApprove</button>

                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="alert alert-success">
                {message}
            </div>
        </div>
    );
}

export default DealerPartList;