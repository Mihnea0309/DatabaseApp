import React, { useEffect, useState } from 'react';
import './ServicesFilter.css';
import {useNavigate} from "react-router-dom";

// interface Employee {
//     nume: string;
//     prenume: string;
//     dataFinalizare: string;
// }

const ServicesFilter: React.FC = () => {
    const navigate = useNavigate();
    const [services, setServices] = useState<any[]>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = async () => {
        try {
            const pret = '1000';
            const response = await fetch(`http://localhost:8080/reparatii_servicii/get/filter`);
            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setServices(data);
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
            <h2>Reparatiile si serviciile efectuate</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Masina</th>
                    <th>Model</th>
                    <th>Descriere reparatie</th>
                    <th>Descriere serviciu</th>
                    <th>Cost</th>
                </tr>
                </thead>
                <tbody>
                {services.map((services, index) => (
                    <tr key={index}>
                        <td>{services[0]}</td>
                        <td>{services[1]}</td>
                        <td>{services[2]}</td>
                        <td>{services[3]}</td>
                        <td>{services[4]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ServicesFilter;
