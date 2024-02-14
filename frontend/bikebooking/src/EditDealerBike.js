import axios from "axios";
import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

function EditDealerBike(props) {
    const [bikes, setBikes] = useState([]);
   // const [bike, setBike] = useState({ name: "", price: "", quantity: "", bikeType: "", bikeBrands: "", description: "", colour: "" });

  const [bike, setBike] = useState({
    name: "",
    price: "",
    quantity: "",
    bikeType: "0", // Set to the value of the first option for bikeType
    bikeBrands: "0", // Set to the value of the first option for bikeBrands
    description: "",
    colour: ""
});
    const [message, setMessage] = useState("")
    const history = useHistory();
    const searchParams = new URLSearchParams(props.location.search);
    const bikeId = searchParams.get('bikeId');

  
    

    useEffect(() => {

        axios
            .get(`http://localhost:8080/users/dealer/editBike/${bikeId}`)
            .then((response) => setBike(response.data))
            .catch((error) => console.error(error));
    }, []);


    const ShowMessage = (msgText) => {
        setMessage(msgText);
        window.setTimeout(() => {
            setMessage("");
        }, 3000);
    }
    const OnTextChange = (args) => {
        var user1 = { ...bike };
        user1[args.target.name] = args.target.value;
        setBike(user1);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        axios
            .put(`http://localhost:8080/users/dealer/updateBike/${bikeId}`, bike)
            .then(() => console.log("User data updated successfully."))
            .then(() => window.setTimeout(() => {
                history.push('/bikeList')
            }, 3000))
            .catch((error) => console.error(error));
        ShowMessage("update Successfully")

    };
    return (
        <div className="container" >
            <hr></hr>
            <div className="table-respinsive" >
                <table className="table table-bordered">
                    <tbody>
                        <tr>
                            <td> BikeType</td>
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
                            <td> Brands</td>
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
                            <td> <input type="number" name="price" value={bike.price} onChange={OnTextChange} ></input></td>
                        </tr>
                        <tr>
                            <td> Quantity </td>
                            <td> <input type="number" name="quantity" value={bike.quantity} onChange={OnTextChange} ></input></td>
                        </tr>
                        <tr>
                            <td> Colour </td>
                            <td> <input type="text" name="colour" value={bike.colour} onChange={OnTextChange} ></input></td>
                        </tr>
                        <tr>
                            <td> Description </td>
                            <td> <input type="text" name="description" value={bike.description} onChange={OnTextChange} ></input></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td>
                                <button className="btn btn-primary" onClick={handleSubmit}>
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

};

export default EditDealerBike;