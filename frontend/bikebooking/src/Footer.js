import React from "react";
//import "../node_modules/bootstrap/dist/css"

 function Footer()
{
    return(
        <div className='blockcode'>
        <div>
        <footer className="bg-light text-center text-white">
  {/* Grid container */}
  {/* <div className="container p-4 pb-0"> */}
  <div>
    {/* Section: Social media */}
    <section className="float-right">
      {/* Facebook */}
      <a
        className="btn text-white btn-floating m-1 img"
        style={{ backgroundColor: "#3b5998" }}
        href="/"
        role="button"
      >
        <i className="fa fa-facebook" />
      </a>
      {/* Twitter */}
      <a
        className="btn text-white btn-floating m-1"
        style={{ backgroundColor: "#55acee" }}
        href="/"
        role="button"
      >
        <i className="fa fa-twitter" />
      </a>
      {/* Google */}
      <a
        className="btn text-white btn-floating m-1"
        style={{ backgroundColor: "#dd4b39" }}
        href="/"
        role="button"
      >
        <i className="fa fa-google" />
      </a>
      {/* Instagram */}
      <a
        className="btn text-white btn-floating m-1"
        style={{ backgroundColor: "#ac2bac" }}
        href="/"
        role="button"
      >
        <i className="fa fa-instagram" />
      </a>
    </section>
    {/* Section: Social media */}
  </div>
  {/* Grid container */}
  {/* Copyright */}
  <div className='text-center p-3' style={{ backgroundColor: "rgba(0, 0, 0, 0.2)" }}>
  <div
    className="img"
    
    
  >
    © 2023-2024 Copyright:
    <a className="text-white" href="https://en.wikipedia.org/wiki/All_rights_reserved">
      All Rights Reserved
    </a>

  </div>
  </div>
  {/* Copyright */}
</footer>
</div>

    </div>
    )
}
export default Footer;