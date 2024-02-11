import axios from "axios";
import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";

function DealerBikeList() {
    const [bikes, setBikesList] = useState([]);
    const [message, setMessage]= useState("");
    function getData (id){
        axios.get(`http://localhost:8080/users/dealer/bikeList/${id}`)
        .then((response)=>{
            setBikesList(response.data)
        })
        .catch((err)=>{
            console.log(err);
        });
    };

    const ShowMessage = (msgText)=>{
        setMessage(msgText);
        window.setTimeout(()=>{
            setMessage("");
        },3000);
    };

    useEffect(()=>{
        getData(sessionStorage.getItem("userId"));
    },[]);
    

    function handleDelete(id){
        axios.delete(`http://localhost:8080/users/admin/deleteBike/${id}`)
        .then(()=>{
            getData();
            ShowMessage("delete removed successfully");
            //if i click on delete button it will deleted the products from dealer & also from customers
        }).catch((err)=>{
            console.log(err)
        });
    }

//     function handleApprove(id){
//       axios.put(`http://localhost:8080/users/admin/approveBike/${id}`)
//       .then(()=>{
//           getData();
//           ShowMessage("approved successfully");
//           //if i click on approve button it the approve status from false to true
//       }).catch((err)=>{
//           console.log(err)
//       });
//   }
//   function handleUnApprove(id){
//     axios.put(`http://localhost:8080/users/admin/disproveBike/${id}`)
//     .then(()=>{
//         getData();
//         ShowMessage("dis-approved successfully");
//         //if i click on approve button it the approve status from false to true
//     }).catch((err)=>{
//         console.log(err)
//     });
//   }

    function types(type){
      if (type == 0) {
        bikes.bikeType="SCOOTER";
      }
      else
      {
        bikes.bikeType="BIKE";
      }
    }

    // function setDataToStorage(id, bikeName, Quantity, bikeType, bikeBrands, colour, approveStatus, deleteStatus) {
    //     localStorage.setItem('id', id);
    //     localStorage.setItem('bikeName', bikeName);
    //     localStorage.setItem('Quantity', Quantity);
    //     localStorage.setItem('bikeBrands', bikeBrands);
    //     localStorage.setItem('colour', colour);
    //     localStorage.setItem('approveStatus', approveStatus);
    //     localStorage.setItem('deleteStatus', deleteStatus);
    //   }

    return ( 
    <div className="container">
<h3>Bikes List</h3>

      <table className="table table-responsive">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Type</th>
            <th>Brand</th>
            <th>Colour</th>

            <th>Approve Status</th>
            <th>Delete</th>
            <th>Edit</th>
          </tr>
        </thead>
        <tbody>
          {bikes.map((bikes) => (
            <tr >

              <td>{bikes.id}</td>
                <td>{bikes.name}</td>
                <td>{bikes.quantity}</td>
                <td>{bikes.bikeType}</td>
                <td>{bikes.bikeBrands}</td>
                <td>{bikes.colour}</td>
                <td>{bikes.approveStatus === 0 ? "Yes" : "No"}</td>

              <td>
                <button className='btn btn-danger' onClick={() => {
                  if (window.confirm('Are You Sure To Delete Data ??')) { handleDelete(bikes.id) }
                }}>Delete</button>
              </td>

              <td>
                <button className='btn btn-secondary' onClick={() => {
                  if (window.confirm('Are You Sure To Delete Data ??')) { handleDelete(bikes.id) }
                }}>Edit</button>
              </td>

           {/* <td>
              { bikes.approveStatus == 0 && (
                  
                  <button className="btn btn-info"
                  onClick={() => {
                    if (window.confirm('Are You Sure To Approve Data ??')) { handleApprove(bikes.id) }
                  }}>Approve</button>
                
              )}
              { bikes.approveStatus == 1 && (
              
                
                  <button className="btn btn-info"
                  onClick={() => {
                    if (window.confirm('Are You Sure To UnApprove  Data ??')) { handleUnApprove(bikes.id) }
                  }}>UnApprove</button>
                
              )}

              </td> */}


            </tr>
          ))}
        </tbody>
      </table>
      <div>
        <button className="btn btn-success">Add Bikes</button>
      </div>
      <div className="alert alert-success">
                {message}
            </div>
    </div> );
}

export default DealerBikeList;