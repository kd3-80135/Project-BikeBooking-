import React from "react";
import { Link } from "react-router-dom";


function Dealer()
{
    return(

        <div>
            <div>
            <Link to='/bikeList'> Bike List</Link>
           </div>
           <div>
            <Link to='/partList'> Part List</Link>
           </div>
        </div>

        
       

    )
}
export default Dealer;