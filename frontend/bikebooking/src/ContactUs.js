import React from "react";
import '../node_modules/font-awesome/css/font-awesome.min.css'

function ContactUs() {
    return ( 
        <div class="text-justify" style={{
            fontFamily:"sans-serif",
            fontSize:"16px"
            
              
          }}>
            <p >
                <ul style={{listStyleType : "none"}}>
                    <li>Thank you for considering BIKE SPOT for your two-wheeled adventures.</li>
                    <li>Whether you have questions, need assistance, or are ready to book your dream ride, we're </li>
                    <li>here to help. Contact us through the following channels, and our dedicated team will ensure</li>
                    <li>you have a smooth and memorable biking experience.</li>
                 
                </ul>
           </p>
                

                <h4>Customer Support:</h4>
                <p style={{alignContent:"initial"}}>

                   <ul style={{listStyleType : "none"}} >
                    <li>Our knowledgeable and friendly customer support team is available to assist you with any</li>
                    <li>inquiries or concerns. Reach out via phone or email, and we'll provide prompt and personalized</li>
                    <li>assistance.</li>
                    <li>Phone: +91-9152634589</li>
                    <li>Email: bikespot@gmail.com</li>

                   </ul> 
                </p>
                
                <h4>Live Chat:</h4>
                
                <p>
                    <ul style={{listStyleType : "none"}}>
                        <li>For real-time support, utilize our live chat feature available on our website. Our team is ready </li>
                        <li>to chat and guide you through the booking process, answer questions about our bikes, or assist with</li>
                        <li>any other information you may need.</li>
                        </ul>
                
                
                </p>
                
                <h4>Visit Our Office:</h4>
                
                <p>
                    <ul style={{listStyleType : "none"}}>
                    <li>If you prefer a face-to-face interaction, feel free to visit our office during business hours. </li>
                    <li>Our location details are provided below:</li>
                    </ul>          
                       
                

                <ul style={{listStyleType : "none"}}>
                    <li><h5>BIKE SPOT</h5></li>
                    <li>4th Floor, B4 Building</li>
                    <li>Cerebrum IT park</li>
                    <li>Ambar City, Wakad </li>
                    <li>Pune - 411014</li>
                </ul>
                
                </p>

                <h4>Social Media:</h4>
                <ul style={{listStyleType : "none"}}>
                    <li>Connect with us on social media to stay updated on the latest bikes, promotions, and biking tips.</li>
                </ul>
                
                 
                <div className="float-right">
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#3b5998" }}
                role="button"
              >
                <i className="fa fa-facebook" />
              </a>
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#55acee" }}
                role="button"
              >
                <i className="fa fa-twitter" />
              </a>
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#dd4b39" }}
                role="button"
              >
                <i className="fa fa-google" />
              </a>
              <a
                className="btn btn-dark btn-floating m-1"
                href="/"
                style={{ backgroundColor: "#ac2bac" }}
                role="button"
              >
                <i className="fa fa-instagram" />
              </a>
            </div>
                <ul style={{listStyleType : "none"}}>
                    <li>we welcome your messages and interactions.</li>
                    <li>At BIKE SPOT, we're passionate about providing you with the best biking experience.</li>
                    <li>Your feedback is valuable to us, and we look forward to assisting you on your journey with us.<b>Ride on!</b> </li>

                </ul>
                

                 
                






        </div>
     );
}

export default ContactUs;