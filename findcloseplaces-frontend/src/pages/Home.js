import React, { useState } from 'react';
import axios from 'axios';

export default function Home() {

    const [info, setInfo] = useState({
        longitude: "",
        latitude: "",
        radius: "",
        theJSON:"",
    });

    const { longitude, latitude, radius, theJSON } = info;

    const onInputChange = (e) => {
        setInfo({ ...info, [e.target.name]: e.target.value })
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post(`http://localhost:8080/addlocation`, info);
    };

    return (
        <div style={{ display: 'flex' }}>
            <div className="container">
                <div className='row' style={{ paddingTop: "20px" }}>
                    <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow' style={{ marginLeft: "20px" }}>
                        <h2 className='text-center m-4'>Search Nearby Locations</h2>
                        <form onSubmit={(e) => onSubmit(e)}>
                            <div className='mb-2'>
                                <label htmlFor='Longitude' className='form-label'>
                                    Longitude
                                </label>
                                <input
                                    type={"number"}
                                    className='form-control'
                                    placeholder='Enter your longitude'
                                    name='longitude'
                                    value={longitude}
                                    onChange={(e) => onInputChange(e)}
                                    required
                                />
                            </div>
                            <div className='mb-2'>
                                <label htmlFor='Latitude' className='form-label'>
                                    Latitude
                                </label>
                                <input
                                    type={"number"}
                                    className='form-control'
                                    placeholder='Enter your latitude'
                                    name='latitude'
                                    value={latitude}
                                    onChange={(e) => onInputChange(e)}
                                    required
                                />
                            </div>
                            <div className='mb-2'>
                                <label htmlFor='Radius' className='form-label'>
                                    Radius
                                </label>
                                <input
                                    type={"number"}
                                    className='form-control'
                                    placeholder='Enter your radius'
                                    name='radius'
                                    value={radius}
                                    onChange={(e) => onInputChange(e)}
                                    required
                                />
                            </div>
                            <button type='submit' className='btn btn-outline-primary'>Search</button>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                output
            </div>
        </div>
    )
}
