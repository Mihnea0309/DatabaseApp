import React, { useEffect, useState } from 'react';
import './EmployeesFilter.css';
import {useNavigate, useParams} from "react-router-dom";

// interface Employee {
//     nume: string;
//     prenume: string;
//     dataFinalizare: string;
// }

const EmployeesFilter: React.FC = () => {
    const navigate = useNavigate();
    const [employees, setEmployees] = useState<any[]>([]);
    const [error, setError] = useState<string>('');
    const { year } = useParams<{ year: string }>();

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            const dataFinalizare = '2024-01-01'; // Data filtrului
            const response = await fetch(`http://localhost:8080/reparatii_angajati/get/filterYear/${year}`);
            if (response.ok) {
                const data = await response.json();
                console.log(data); // Verifică ce conține răspunsul
                setEmployees(data);
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
            <h2>Angajati care au terminat reparatii inainte de anul {year}</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Nume</th>
                    <th>Prenume</th>
                    <th>Data finalizarii</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((employee, index) => (
                    <tr key={index}>
                        <td>{employee[0]}</td>
                        <td>{employee[1]}</td>
                        <td>{employee[2]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default EmployeesFilter;
