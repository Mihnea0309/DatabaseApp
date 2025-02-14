import React, { useEffect, useState } from 'react';
import './ClientsFilter.css';
import {useNavigate} from "react-router-dom";

// interface Employee {
//     nume: string;
//     prenume: string;
//     dataFinalizare: string;
// }

const ClientsFilter2: React.FC = () => {
    const navigate = useNavigate();
    const [clients, setClients] = useState<any[]>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        fetchClients();
    }, []);

    const fetchClients = async () => {
        try {
            const asigurare = 'Nu';
            const response = await fetch(`http://localhost:8080/Clienti/get/filter_cost`);
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
            <h2>Clientii si suma pe care trebuie sa o plateasca pentru fiecare reparatie</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Car</th>
                        <th>Model</th>
                        <th>Repair description</th>
                        <th>Total cost</th>
                    </tr>
                </thead>
                <tbody>
                    {clients.map((clients, index) => (
                        <tr key={index}>
                            <td>{clients[0]}</td>
                            <td>{clients[1]}</td>
                            <td>{clients[2]}</td>
                            <td>{clients[3]}</td>
                            <td>{clients[4]}</td>
                            <td>{clients[5]}</td>
                            <td>{clients[6]}</td>
                        </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
    };

    export default ClientsFilter2;
