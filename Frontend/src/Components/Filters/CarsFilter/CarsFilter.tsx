import React, { useEffect, useState } from 'react';
import './CarsFilter.css';
import {useNavigate, useParams} from "react-router-dom";

// interface Employee {
//     nume: string;
//     prenume: string;
//     dataFinalizare: string;
// }

const CarsFilter: React.FC = () => {
    const navigate = useNavigate();
    const [cars, setCars] = useState<any[]>([]);
    const [error, setError] = useState<string>('');
    const { price } = useParams<{ price: string }>();

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = async () => {
        try {
            const pret = '1000';
            const response = await fetch(`http://localhost:8080/Masini/get/filterPrice/${price}`);
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
            <h2>Masinile si pretul minim de {price} pentru care au fost reparate</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Masina</th>
                    <th>Model</th>
                    <th>Asigurare</th>
                    <th>Descriere reparatie</th>
                    <th>Cost total</th>
                </tr>
                </thead>
                <tbody>
                {cars.map((cars, index) => (
                    <tr key={index}>
                        <td>{cars[0]}</td>
                        <td>{cars[1]}</td>
                        <td>{cars[2]}</td>
                        <td>{cars[3]}</td>
                        <td>{cars[4]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default CarsFilter;
