import React, { useEffect, useState } from "react";
import axios from "axios";


function AdminOrder() {
  const [orders, setOrderList] = useState([]);
  const [message, setMessage] = useState("");
  function getData() {
    axios.get("http://localhost:8080/users/admin/orderList")
      .then((response) => {
        setOrderList(response.data)
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
  function handleCancel(id) {
    axios.put(`http://localhost:8080/users/admin/cancelOrder/${id}`)
      .then(() => {
        getData();
        ShowMessage("delete removed successfully");
        //if i click on delete button it will deleted the products from dealer & also from customers
      }).catch((err) => {
        console.log(err)
      });
  }
  function handleApprove(id) {
    axios.put(`http://localhost:8080/users/admin/successOrder/${id}`)
      .then(() => {
        getData();
        ShowMessage("delete removed successfully");
        //if i click on delete button it will deleted the products from dealer & also from customers
      }).catch((err) => {
        console.log(err)
      });
  }
  useEffect(() => {
    getData();
  }, []);
    return (

        <div className="container">
          <h3>Order List</h3>
          <table className="table table-response">
            <thead>
              <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Order ID</th>
                <th>Placed At</th>
                <th>Delivary At</th>
                <th>Cancelled At</th>
              </tr>
            </thead>
            <tbody>
            {orders.map((order) => (
            <tr >

              <td>{order.firstName}</td>
              <td>{order.lastName}</td>
              <td>{order.orderId}</td>
              <td>{order.placedAt}</td>
              <td>{order.deliveredAt}</td>
              <td>{order.cancelledAt}</td>
              {/* <td>
                { order.cancelledAt == "null" && (
                   <button className='btn btn-danger' onClick={() => {
                    if (window.confirm('Are You Sure To Cancel Data ??')) { handleCancel(order.orderId)}
                  }}>Cancel</button>)
                }
                 
               
              </td>
              <td>
                {order.deliveredAt == "null" && (
                <button className='btn btn-success' onClick={() => {
                  if (window.confirm('Are You Sure To Approve Data ??')) { handleApprove(order.orderId)}
                }}>Success</button>)}

              </td> */}
              <td>
              {orders.map((order) => (
      <div key={order.orderId}>
        {order.cancelledAt === null && order.deliveredAt === null && (
          <div>
            <button className='btn btn-danger' onClick={() => {
              if (window.confirm('Are You Sure To Cancel Data ??')) {
                handleCancel(order.orderId);
              }
            }}>Cancel</button>
            <button className='btn btn-success' onClick={() => {
              if (window.confirm('Are You Sure To Approve Data ??')) {
                handleApprove(order.orderId);
              }
            }}>Success</button>
          </div>
        )}
      </div>
    ))}
              </td>
              </tr>
            ))}
                

            </tbody>
          </table>
        </div>
      );
}

export default AdminOrder;