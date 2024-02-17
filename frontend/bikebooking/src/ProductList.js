import axios from "axios";
import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";

function ProductList() {
    const [products, setProductsList] = useState([]);
    const [message, setMessage]= useState([]);
    function getData (){
        axios.get("http://localhost:8080/admin/productlist")
        .then((response)=>{
            setProductsList(response.data)
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
        axios.delete(`http://localhost:8080/products/${id}`)
        .then(()=>{
            getData();
            ShowMessage("record added successfully");
        }).catch((err)=>{
            console.log(err)
        });
    }

    function setDataToStorage(id, productName, price, stockQuantity, description) {
        localStorage.setItem('id', id);
        localStorage.setItem('productName', productName);
        localStorage.setItem('price', price);
        localStorage.setItem('stockQuantity', stockQuantity);
        localStorage.setItem('description', description);
      }

    return ( 
    <div>
<h3>Product List</h3>
      <table>
        <thead>
          <tr className="table table-dark">
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>

            <th>Delete</th>
            <th>Update</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr >
              <td>{product.id}</td>
              <td>{product.productName}</td>
              <td>{product.price}</td>
              <td>{product.stockQuantity}</td>

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