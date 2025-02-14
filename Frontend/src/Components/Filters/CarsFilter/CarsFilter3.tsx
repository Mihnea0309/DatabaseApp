import React, { useEffect, useState } from 'react';
import './CarsFilter.css';
import {useNavigate, useParams} from "react-router-dom";


const CarsFilter3: React.FC = () => {
    const navigate = useNavigate();
    const [cars, setCars] = useState<any[]>([]);
    const [error, setError] = useState<string>('');
    const { lastName } = useParams<{ lastName: string }>();
    const { firstName } = useParams<{ firstName: string }>();

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = async () => {
        try {
            const pret = '1000';
            const response = await fetch(`http://localhost:8080/Masini/get/filterOwner/${lastName}/${firstName}`);
            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setCars(data);
                setError('');
            } else {
                setError('There was an issue fetching the data.');
            }
        } catch (err) {
            setError('An error occurred while fetching the data.');
        }
    };

    const handleGoBack = () => {
        navigate(-1);
    };


    return (
        <div>
            <h2>Masina sau masinile clientului {lastName} {firstName}</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Nume</th>
                    <th>Prenume</th>
                    <th>Masina</th>
                    <th>Model</th>
                </tr>
                </thead>
                <tbody>
                {cars.map((cars, index) => (
                    <tr key={index}>
                        <td>{cars[0]}</td>
                        <td>{cars[1]}</td>
                        <td>{cars[2]}</td>
                        <td>{cars[3]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CarsFilter3;
