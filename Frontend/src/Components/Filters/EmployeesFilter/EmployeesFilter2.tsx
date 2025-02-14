import React, { useEffect, useState } from 'react';
import './EmployeesFilter.css';
import {useNavigate, useParams} from "react-router-dom";

// interface Employee {
//     nume: string;
//     prenume: string;
//     dataFinalizare: string;
// }

const EmployeesFilter2: React.FC = () => {
    const navigate = useNavigate();
    const [employees, setEmployees] = useState<any[]>([]);
    const [error, setError] = useState<string>('');
    const { hours } = useParams<{ hours: string }>();

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            const numarOre = 4;
            const response = await fetch(`http://localhost:8080/reparatii_angajati/get/filterHours/${hours}`);
            if (response.ok) {
                const data = await response.json();
                console.log(data);
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
            <h2>Salariile angajatilor care au petrecut mai mult de {hours} ore</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Nume</th>
                    <th>Prenume</th>
                    <th>Salariu</th>
                    <th>Numar Ore</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((employee, index) => (
                    <tr key={index}>
                        <td>{employee[0]}</td>
                        <td>{employee[1]}</td>
                        <td>{employee[2]}</td>
                        <td>{employee[3]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default EmployeesFilter2;
