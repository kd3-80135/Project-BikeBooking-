import React, { useEffect, useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

function AddBike() {
    var id = sessionStorage.getItem("userId");

    const [bikes, setBikes] = useState([]);
    // const [bike, setBike] = useState({ name: "", price: "", quantity: "", bikeType: "", bikeBrands: "", description: "", colour: "" });
    const [message, setMessage] = useState("")
    var url = `http://localhost:8080/users/dealer/addBike/${id}`;
    const history = useHistory();
    const [bike, setBike] = useState({
        name: "",
        price: "",
        quantity: "",
        bikeType: "0",
        bikeBrands: "0",
        description: "",
        colour: ""
    });


    const OnTextChange = (args) => {
        var bike1 = { ...bike };
        bike1[args.target.name] = args.target.value;
        setBike(bike1);

    }



    const ClearBoxes = () => {
        setBike({ name: "", price: "", quantity: "", bikeType: "", bikeBrands: "", description: "", colour: "" });
    }


    const ShowMessage = (msgText) => {
        setMessage(msgText);
        window.setTimeout(() => {
            setMessage("");
        }, 3000);
    }

    const AddRecord = () => {

        const bikeToSend = {
            ...bike,
            bikeType: parseInt(bike.bikeType, 10),
            bikeBrands: parseInt(bike.bikeBrands, 10)
        };

        axios.post(url, bikeToSend).then((result) => {
            if (result.data !== undefined) {
                ClearBoxes();
                ShowMessage("Record Added Successfully");
            }
        })
            .then(() => window.setTimeout(() => {
                history.push('/bikeList')
            }, 3000))
            .catch((error) => {
                console.error("Error:", error);
                // Handle the error as needed
            });
    };



    useEffect(() => {
        console.log("Some state change did update the UI");

    }, [bikes, bike, message]);

    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Bike Type:</label>
                        <select
                            className="form-control"
                            name="bikeType"
                            value={bike.bikeType}
                            onChange={OnTextChange}
                        >
                            <option value="0">SCOOTY</option>
                            <option value="1">BIKE</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Brands:</label>
                        <select
                            className="form-control"
                            name="bikeBrands"
                            value={bike.bikeBrands}
                            onChange={OnTextChange}
                        >
                            <option value="0">HONDA</option>
                            <option value="1">SUZUKI</option>
                            <option value="2">YAMAHA</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Name:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="name"
                            value={bike.name}
                            onChange={OnTextChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Price:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="price"
                            value={bike.price}
                            onChange={OnTextChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Quantity:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="quantity"
                            value={bike.quantity}
                            onChange={OnTextChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Colour:</label>
                        <input
                            type="text"
                            className="form-control"
                            name="colour"
                            value={bike.colour}
                            onChange={OnTextChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Description:</label>
                        <textarea
                            className="form-control"
                            name="description"
                            value={bike.description}
                            onChange={OnTextChange}
                            rows="3"
                        />
                    </div>
                    <div className="form-group" >
                        <button
                            className="btn btn-primary"
                            onClick={AddRecord}
                        >
                            Submit
                        </button>
                    </div>

                </div>

               
            </div>

            <div className={`alert ${message ? "alert-success" : "d-none"}`}>
                {message}
            </div>
        </div>
    );
}

export default AddBike;