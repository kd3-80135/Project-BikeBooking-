import axios from "axios";
import React, { useEffect, useState } from "react";


function CustomerOrders() {

    const [orders, setOrders] = useState([]);
    var userId = sessionStorage.getItem("userId");
    const [message, setMessage] = useState("");

    function getData() {
        axios
          .get(`http://localhost:8080/users/customer/myOrders/${userId}`)
          .then((response) => {
            setOrders(response.data);
          })
          .catch((err) => {
            setMessage(err.data);
          });
      }
      function handleCancel(id) {
        axios.put(`http://localhost:8080/users/admin/cancelOrder/${id}`)
          .then(() => {
            getData();
            setMessage("delete removed successfully");
            //if i click on delete button it will deleted the products from dealer & also from customers
          }).catch((err) => {
            console.log(err)
          });
      }
      useEffect(() => {
        getData();
      }, []);
    return ( 
        <div>
            <h3>Order List</h3>
          <table className="table table-response">
            <thead>
              <tr>
                
                <th>Order ID</th>
                <th>Placed At</th>
                <th>Delivary At</th>
                <th>Cancelled At</th>
              </tr>
            </thead>
            <tbody>
            {orders.map((order) => (
            <tr >

              
              <td>{order.orderId}</td>
              <td>{order.placedAt}</td>
              <td>{order.deliveredAt}</td>
              <td>{order.cancelledAt}</td>
              <td>
                {order.deliveredAt == "null" && order.cancelledAt == "null" && (
                   <button className='btn btn-danger' onClick={() => {
                    if (window.confirm('Are You Sure To Cancel Data ??')) { handleCancel(order.orderId)}
                  }}>Cancel</button>)
                }
               
              </td>
              

              </tr>
            ))}
                

            </tbody>
          </table>


        </div>
     );
}

export default CustomerOrders;