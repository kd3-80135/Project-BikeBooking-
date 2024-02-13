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
            <Link to='/customerpartlist'> Part List</Link>
           </div>
        </div>


    )
}
export default User;