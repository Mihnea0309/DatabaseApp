import React, { useEffect, useState } from 'react';
import './ClientsFilter.css';
import {useNavigate, useParams} from "react-router-dom";

const ClientsFilter3: React.FC = () => {
    const navigate = useNavigate();
    const [clients, setClients] = useState<any[]>([]);
    const [error, setError] = useState<string>('');
    const { cost } = useParams<{ cost: string }>();

    useEffect(() => {
        fetchClients();
    }, []);

    const fetchClients = async () => {
        try {
            const asigurare = 'Nu';
            const response = await fetch(`http://localhost:8080/Clienti/get/filterCost/${cost}`);
            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setClients(data);
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
            <h2>Clientii si pretul minim de {cost} pe care trebuie sa-l plateasca pe manopera</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Nume</th>
                    <th>Prenume</th>
                    <th>Pretul pe manopera</th>
                </tr>
                </thead>
                <tbody>
                {clients.map((clients, index) => (
                    <tr key={index}>
                        <td>{clients[0]}</td>
                        <td>{clients[1]}</td>
                        <td>{clients[2]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ClientsFilter3;
