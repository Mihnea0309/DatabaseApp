import React, { useEffect, useState } from 'react';
import './RepairsFilter.css';
import {useNavigate, useParams} from "react-router-dom";

const RepairsFilter: React.FC = () => {
    const navigate = useNavigate();
    const [repairs, setRepairs] = useState<any[]>([]);
    const [error, setError] = useState<string>('');
    const { year } = useParams<{ year: string }>();

    useEffect(() => {
        fetchClients();
    }, []);

    const fetchClients = async () => {
        try {
            const response = await fetch(`http://localhost:8080/Reparatii/get/filterYear/${year}`);
            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setRepairs(data);
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
            <h2>Reparatii care au fost terminate pe masini fabricate dupa anul {year}</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Descriere</th>
                    <th>Cost</th>
                </tr>
                </thead>
                <tbody>
                {repairs.map((repairs, index) => (
                    <tr key={index}>
                        <td>{repairs[0]}</td>
                        <td>{repairs[1]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default RepairsFilter;
