import React, { useEffect, useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

function AddPart() {
    var id = sessionStorage.getItem("userId");

    const [parts, setParts] = useState([]);
  const [message, setMessage] = useState("")
    var url = `http://localhost:8080/users/dealer/addPart/${id}`;
    const history = useHistory();
    const [part, setPart] = useState({
        name: "",
        price: "",
        description: "",
        quantity: ""
    });
    
    const OnTextChange = (args) => {
        var part1 = { ...part };
        part1[args.target.name] = args.target.value;
        setPart(part1);

    }



    const ClearBoxes = () => {
        setPart({ name: "", price: "", description: "", quantity: "" });
    }


    const ShowMessage = (msgText) => {
        setMessage(msgText);
        window.setTimeout(() => {
            setMessage("");
        }, 3000);
    }

    const AddRecord = () => {

      //  const partToSend = {
        //     ...bike,
        //     bikeType: parseInt(bike.bikeType, 10),
        //     bikeBrands: parseInt(bike.bikeBrands, 10)
        // };

        axios.post(url, part).then((result) => {
            if (result.data !== undefined) {
                ClearBoxes();
                ShowMessage("Record Added Successfully");
            }
        })
        .then(() => window.setTimeout(() => {
            history.push('/partList')
        }, 3000))
        .catch((error) => {
            console.error("Error:", error);
        });
    };


    useEffect(() => {
        console.log("Some state change did update the UI");

    }, [parts, part, message]);

    return (
        <div className="container">
            <hr></hr>
            <div className="table-respinsive" >
                <table className="table table-bordered">
                    <tbody>
                        <tr>
                            <td> Name</td>
                            <td> <input type="text" name="name" value={part.name} onChange={OnTextChange}></input></td>
                        </tr>
                        <tr>
                            <td> Price </td>
                            <td> <input type="number" name="price" value={part.price} onChange={OnTextChange}></input></td>
                        </tr>
                       
                        <tr>
                            <td> Quantity</td>
                            <td> <input type="number" name="quantity" value={part.quantity} onChange={OnTextChange}></input></td>
                        </tr>

                        <tr>
                            <td> Description </td>
                            <td>
                                <input
                                    type="text"
                                    name="description"
                                    value={part.description}
                                    onChange={OnTextChange}
                                    style={{ width: "60%", height: "60px" }}
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

export default AddPart;