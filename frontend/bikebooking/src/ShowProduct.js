import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { Link, useHistory } from "react-router-dom";

function ShowProduct() {
    const [products, setProductsList] = useState([]);
    const history = useHistory();

    function handleCLick(){
        history.push('/admin');
    }

    function getData(){
        axios.get(`http://localhost:8080/products`)
        .then((response)=>{
            setProductsList(response.data)
        })
        .catch((err)=>{
            console.log(err);
        });
    };

    useEffect(()=>{
        getData();
    },[])

    function handleDelete(){
        axios.delete(`http://localhost:8080/products`)
        .then(()=>{
            getData();
        })
        .catch((err)=>{
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
              <button className='btn btn-primary' onClick={handleClick}>Back</button>
       <br></br>
       <br></br>
       <br></br>
       <button className='btn btn-primary' onClick={AddhandleClick}>Add Product</button>
       <br></br>
       <br></br>

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
              <td>
                <Link to='/editproduct'>
                  <button className='btn btn-primary' onClick={() => setDataToStorage(product.id, product.productName, product.price, product.stockQuantity)}>Update</button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <br /><br /> <br /><br /> <br /><br /> <br /><br />

    </div>  );
}

export default ShowProduct;