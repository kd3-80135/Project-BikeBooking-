import React from "react";
//import '../node_modules/bootstrap/dist/css/'
import bike from './images/image.jpg'



function Home()
{
    return(
        <div className="Landing2_Landin">
                    <img
       src={bike} 
         alt="Login image" className="w-100 vh-70 loginimg " style={{objectFit: 'fill', objectPosition: 'right'}} />
      </div>
    )
}

export default Home;