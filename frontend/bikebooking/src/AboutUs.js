import React from "react";
import './styles/about.css';
import { Container, Row, Col } from "react-bootstrap";
import superm from './images/gifbike2.gif'

function AboutUs() {
    return (
        <div>
                <section id="about" className="py-5">
    <Container>
      <Row>
        <Col md={6} className="order-md-2 mb-4 mb-md-0">
          <img
            src={superm}
            alt="About Us"
            className="img-fluid rounded"
          />
        </Col>
        <Col md={6} className="order-md-1">
            <h2 className="text-uppercase mb-3">About Us</h2>
            <p className="lead mb-4"> 
    {/* <div className="container-fluid px-0">
      <div className="row mx-0">
        <div className="col-md-6 bg-light p-5">
          <h2 className="text-uppercase text-secondary mb-4">About Store</h2>
          <p className="lead mb-5"> */}
            Welcome to our thrilling world of motorcycles and parts, where passion meets precision! At BIKE SPOT,
            we are more than just a marketplace; we are enthusiasts dedicated to fueling your two-wheeled adventures. 
            Our journey began with a shared love for the open road and the unmistakable hum of a powerful engine. 
            With an unwavering commitment to quality and performance, we curate a diverse selection of motorcycles and 
            parts to cater to riders of all levels.
          </p>
          <h2 className="text-uppercase text-secondary mb-4">What We Do</h2>
          <p className="lead mb-5">
          Founded on the belief that every rider deserves the best, our platform brings together a carefully selected 
          range of top-notch motorcycles and genuine parts from leading manufacturers. Whether you're a seasoned rider
          seeking the latest innovations or a beginner looking to customize your ride, we've got you covered. Our team
          consists of dedicated experts who live and breathe motorcycles, ensuring that every product we offer is not 
          just a purchase but an investment in the thrill of the ride.

          Join us in celebrating the freedom of the open road and the adrenaline rush that comes with it. At BIKE SPOT,
          we don't just sell motorcycles and parts; we cultivate a community of riders who share the same passion for 
          the ride. Explore our collection and gear up for a journey filled with power, precision, and the pure joy of 
          the ride.
        </p>
          <h2 className="text-uppercase text-secondary mb-4">Our Vision</h2>
          <ul className="lead">
            <li>
            Empowering Riders: We envision a future where every rider, regardless of experience, feels empowered to pursue 
            their two-wheeled dreams. By providing access to top-notch motorcycles and genuine parts, we aim to be the driving 
            force behind every rider's journey, fostering a community that embraces the freedom of the open road.
            </li>
            <li>
            Innovation and Quality: Striving for excellence, we are committed to staying at the forefront of innovation and 
            quality in the motorcycle industry. Our vision includes continuously sourcing and offering cutting-edge motorcycles 
            and parts, ensuring that our customers experience the thrill of the ride with the latest advancements in technology 
            and performance.
            </li>
            <li>Building a Riding Community: Beyond being a marketplace, we aspire to create a vibrant and supportive riding community.
                Our vision involves connecting riders from all walks of life, providing a platform for shared experiences, knowledge 
                exchange, and a collective passion for the exhilaration that comes with navigating the world on two wheels. Together, 
                we aim to build a community that celebrates the spirit of adventure and the bond among riders.</li>
          </ul>
         
          </Col>
        </Row>
      </Container>
    </section>
    <br /><br /><br /><br /><br /><br />
        </div>
      );
}

export default AboutUs;