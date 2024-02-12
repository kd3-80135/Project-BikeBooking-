import axios from "axios";
import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";

function ProductList() {
    const [bikes, setBikesList] = useState([]);
    const [message, setMessage]= useState("");
    function getData (){
        axios.get("http://localhost:8080/users/admin/bikes")
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
        getData();
    },[]);
    

    function handleDelete(id){
        axios.delete(`http://localhost:8080/bikes/${id}`)
        .then(()=>{
            getData();
            ShowMessage("record added successfully");
        }).catch((err)=>{
            console.log(err)
        });
    }

    function types(type){
      if (type == 0) {
        bikes.bikeType="SCOOTER";
      }
      else
      {
        bikes.bikeType="BIKE";
      }
    }

    function setDataToStorage(id, bikeName, Quantity, bikeType, bikeBrands, colour, approveStatus, deleteStatus) {
        localStorage.setItem('id', id);
        localStorage.setItem('bikeName', bikeName);
        localStorage.setItem('Quantity', Quantity);
        localStorage.setItem('bikeBrands', bikeBrands);
        localStorage.setItem('colour', colour);
        localStorage.setItem('approveStatus', approveStatus);
        localStorage.setItem('deleteStatus', deleteStatus);
      }

    return ( 
    <div className="container">
<h3>Product List</h3>

      <table className="table table-responsive">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Type</th>
            <th>Brand</th>
            <th>Colour</th>

            <th>Status</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {bikes.map((product) => (
            <tr >
              <td>{bikes.id}</td>
                <td>{bikes.Name}</td>
                <td>{bikes.quantity}</td>
                <td>{bikes.biketype}</td>
                <td>{bikes.bikeBrands}</td>
                <td>{bikes.color}</td>

              <td>
                <button className='btn btn-danger' onClick={() => {
                  if (window.confirm('Are You Sure To Delete Data ??')) { handleDelete(product.id) }
                }}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div> );
}

export default ProductList;