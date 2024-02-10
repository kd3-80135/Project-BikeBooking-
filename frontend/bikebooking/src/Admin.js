import React from "react";

import Bike from './images/bike2.jpg'
import { Link } from "react-router-dom";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'



function Admin()
{
    return(

        <div>
            
            <div>
            <Link to='/userlist'> User Detials</Link>
           </div>
           <div>
            <Link to='/bikes'> Bike Detials</Link>
           </div>
           <div>
            <Link to='/parts'> Part Details</Link>
           </div>
           
            
            <div style={{display: 'flex', justifyContent:'right'}} >
            <img
       src={Bike} 
         alt="Login image" className="w-50 vh-50 loginimg " style={{ objectPosition: 'right'}} />
            </div>
        </div>
        


    )
}
export default Admin;