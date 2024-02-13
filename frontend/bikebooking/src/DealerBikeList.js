import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function DealerBikeList() {
  const [bikes, setBikesList] = useState([]);
  const [message, setMessage] = useState("");
  //const [bike, setBike] = useState({ name: "", price: "", quantity: "", bikeType: "", bikeBrands: "", description: "", colour: "" });
  function getData(id) {
    axios.get(`http://localhost:8080/users/dealer/bikeList/${id}`)
      .then((response) => {
        setBikesList(response.data)
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
  };

  useEffect(() => {
    getData(sessionStorage.getItem("userId"));
  }, []);


  // function handleDelete(id){
  //     axios.delete(`http://localhost:8080/users/dealer/deleteBike/${id}`)
  //     .then(()=>{
  //         getData();
  //         ShowMessage("delete removed successfully");
  //         //if i click on delete button it will deleted the products from dealer & also from customers
  //     }).catch((err)=>{
  //         console.log(err)
  //     });
  // }

  function handleDelete(id) {
    axios.delete(`http://localhost:8080/users/dealer/deleteBike/${id}`)
      .then(() => {
        getData(sessionStorage.getItem("userId"));
        ShowMessage("Delete successful");
      })
      .catch((err) => {
        console.log(err);
      });
  }


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
              <td>
                {bikes.approveStatus == 0 && (
                  <h6>No</h6>
                )}
                {bikes.approveStatus == 1 && (
                  <h6>Yes</h6>
                )}
              </td>
              <td>
                <button className='btn btn-danger' onClick={() => {
                  if (window.confirm('Are You Sure you want to Delete Data ??')) { handleDelete(bikes.id) }
                }}>Delete</button>
              </td>

              <td>
                <div>
                  <Link to={`/editBike?bikeId=${bikes.id}`}>
                    <button className="btn btn-secondary">Edit</button>
                  </Link>

                </div>
              </td>


            </tr>
          ))}
        </tbody>
      </table>
      <div>
        <Link to='/addBike'>
          <button className="btn btn-info">AddBike</button>
        </Link>

      </div>
      <div className="alert alert-success">
        {message}
      </div>
    </div>);
}

export default DealerBikeList;