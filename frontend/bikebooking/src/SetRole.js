import React from "react";
import { Link } from "react-router-dom/cjs/react-router-dom.min";
import { useState } from "react";
import axios from "axios";

function SetRole()
{
    const role1 ={ roledealer : 2}
    const role2 ={ rolecustomer : 1}
    const roleCustomer =()=>
    {
        axios.get(`http://localhost:8080/users/register${1}`)
    }
    const roleDealer =()=>
    {
        axios.get(`http://localhost:8080/users/register${2}`)

    }
    return(
    
       <div>
         <Link     to={{pathname: '/signup' , state : { data : role1 }}} >
        <div className="container"  >
       <h1>Click Here for Dealer </h1> 
        
        </div>
        </Link>

        <Link to={{pathname: '/signup' , state : { data : role2 }}}>
        <div className="container" >
        <h1>Click Here for Customer</h1>
        
        </div>
        </Link>
    
       </div>
       
           
        
        
    )
}
export default SetRole;