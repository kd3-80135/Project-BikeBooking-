import React from "react";
import { Link } from "react-router-dom";

function User()
{
    return(

        <div>
            <div>
            <Link to='/customerbikelist'> Bike List</Link>
           </div>
           <div>
            <Link to='/partList'> Part List</Link>
           </div>
        </div>


    )
}
export default User;