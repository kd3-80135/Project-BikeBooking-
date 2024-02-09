import React from "react";
import { useEffect,useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function Userlist() {
    const [users, setUserList] = useState([]);
    const [message, setMessage] = useState("")
  function getData() {

    axios.get("http://localhost:8080/admin/userlist")


      .then((response) => {
        setUserList(response.data)
      })

      .catch((err) => {
        console.log(err);
      });
  };
  const ShowMessage = (msgText)=>{
    setMessage(msgText);
    window.setTimeout(()=>{
        setMessage("");
    },3000);
};
  useEffect(() => {
    getData();
  }, []);
  function handleDelete(id) {
    axios.delete(`http://localhost:8080/admin/deleteuser/${id}`)
      .then(() => {

        getData();
        ShowMessage("Record Deleted Successfully");
      }).catch((err) => {
        console.log(err)
      });
  }
    return (
        <div>
        <h3>User List</h3>
        <table onLoad={getData} >
          <thead>
            <tr >
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Mobile No</th>
              <th>Role</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr >
                <td>{user.id}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.email}</td>
                <td>{user.mobileno}</td>
                <td>{user.role}</td>
  
                <td>
                  <button className='btn btn-danger' onClick={() => {
                    if (window.confirm('Are You Sure To Delete Data ??')) { handleDelete(user.id) }
                  }}>Delete</button>
                </td>
                <td>
                  <Link to='/blockuser'>
                    <button className='btn btn-primary' >BLOCK</button>
                  </Link>
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

export default Userlist;