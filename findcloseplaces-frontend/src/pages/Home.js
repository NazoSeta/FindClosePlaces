"use client";

import React, { useState } from 'react';
import axios from 'axios';
import { APIProvider, Map, AdvancedMarker, Pin, InfoWindow } from "@vis.gl/react-google-maps"

export default function Home() {

    const position = { lat: 53.54, lng: 10 };

    const [info, setInfo] = useState({
        longitude: "",
        latitude: "",
        radius: "",
    });

    const { longitude, latitude, radius } = info;

    const [response, setResponse] = useState(null);

    const onInputChange = (e) => {
        setInfo({ ...info, [e.target.name]: e.target.value })
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        const result = await axios.post(`http://localhost:8080/addlocation`, info);
        setResponse(result.data);
    };

    return (
        <div style={{ display: 'flex' }}>
            <div className="container" style={{ maxWidth: "30%" }}>
                <div className='row' style={{ paddingTop: "20px" }}>
                    <div className='col-md-10 offset-md-3 border rounded p-4 mt-2 shadow' style={{ marginLeft: "20px" }}>
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
            <APIProvider apiKey={process.env.REACT_APP_GOOGLE_PLACES_API_KEY}>
                <div style={{ height: "80vh", width: "100%" }}>
                    <Map zoom={9} center={position} mapId={process.env.REACT_APP_GOOGLE_PLACES_MAP_ID}>
                        <AdvancedMarker position={position}>

                        </AdvancedMarker>
                    </Map>
                </div>
            </APIProvider>
        </div>
    )
}
