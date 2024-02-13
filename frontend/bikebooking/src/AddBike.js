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
    // const bikeTypes = [
    //     { value: "0", label: "Scooty" },
    //     { value: "1", label: "MotorBike" },
    // ];


    // const bikeBrands = [
    //     { value: "0", label: "HONDA" },
    //     { value: "1", label: "SUZUKI" },
    //     { value: "2", label: "YAMAHA" }
    // ];


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



    // const AddRecord = () => {

    //     axios.post(`http://localhost:8080/users/dealer/addBike/${id}`, bike).then((result) => {
    //         if (result.data !== undefined) {
    //             ClearBoxes();
    //             ShowMessage("Record Added Successfully");
    //         }
    //     });
    // }

    useEffect(() => {
        console.log("Some state change did update the UI");

    }, [bikes, bike, message]);

    return (
        <div className="container">
            <hr></hr>
            <div className="table-respinsive" >
                <table className="table table-bordered">
                    <tbody>

                        {/* <tr>
                            <td> BikeType </td>
                            <td>
                                <select
                                    name="bikeType"
                                    value={bike.bikeType}
                                    onChange={OnTextChange}
                                >
                                    {bikeTypes.map((type) => (
                                        <option key={type.value} value={type.value}>
                                            {type.label}
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr> */}

                        <tr>
                            <td> BikeType </td>
                            <td>
                                <select
                                    name="bikeType"
                                    value={bike.bikeType}
                                    onChange={OnTextChange}
                                >
                                        <option key='SCOOTY' value='0'>
                                            SCOOTY
                                        </option>
                                        <option key='BIKE' value='1'>
                                            BIKE
                                        </option>
                                    
                                </select>
                            </td>
                        </tr>

                        <tr>
                             <td> Brands </td>
                            <td>
                                <select
                                    name="bikeBrands"
                                    value={bike.bikeBrands}
                                    onChange={OnTextChange}
                                >
                                    
                                    <option key='HONDA' value='0'>
                                            HONDA
                                        </option>
                                        <option key='SUZUKI' value='1'>
                                        SUZUKI
                                        </option>
                                        <option key='YAMAHA' value='2'>
                                        YAMAHA
                                        </option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td> Name</td>
                            <td> <input type="text" name="name" value={bike.name} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Price </td>
                            <td> <input type="text" name="price" value={bike.price} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Quantity</td>
                            <td> <input type="email" name="quantity" value={bike.quantity} onChange={OnTextChange}></input></td>
                        </tr>


                        <tr>
                            <td> Colour </td>
                            <td> <input type="text" name="colour" value={bike.colour} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Description </td>
                            <td>
                                <input
                                    type="text"
                                    name="description"
                                    value={bike.description}
                                    onChange={OnTextChange}
                                    style={{ width: "60%", height: "60px" }}  // Adjust the width and height as needed
                                />
                            </td>
                        </tr>
                        <tr>
                            <td> Image </td>
                            <td>
                                <input
                                    type="file"
                                    name="image"
                                    value={bike.name}
                                    onChange={OnTextChange}
                                      // Adjust the width and height as needed
                                />
                            </td>
                        </tr>



                        <tr>
                            <td></td>
                            <td>
                                <button className="btn btn-primary" onClick={AddRecord}>
                                    Submit
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div className="alert alert-success">
                    {message}
                </div>
            </div>
        </div>
    );
}

export default AddBike;