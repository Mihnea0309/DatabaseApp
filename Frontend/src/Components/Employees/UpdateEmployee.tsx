import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './UpdateEmployee.css';

interface Employee {
    id: number;
    nume: string;
    prenume: string | null;
    cnp: string;
    email: string;
    adresa: string;
    numarTelefon: string;
    functie: string;
    salariu: string;
}

const UpdateEmployee: React.FC = () => {
    const { employeeId } = useParams<{ employeeId: string }>(); // Accesăm `employeeId` din URL
    const navigate = useNavigate();

    const [employee, setEmployee] = useState<Employee>({
        id: 0,
        nume: '',
        prenume: '',
        cnp: '',
        email: '',
        adresa: '',
        numarTelefon: '',
        functie: '',
        salariu: '',
    });

    const [error, setError] = useState<string>('');

    useEffect(() => {
        if (employeeId) {
            const fetchEmployee = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/Angajati/get/${employeeId}`);
                    if (response.ok) {
                        const data = await response.json();
                        setEmployee(data);
                    } else {
                        setError('Failed to fetch employee data.');
                    }
                } catch (err) {
                    setError('An error occurred while fetching the employee data.');
                }
            };

            fetchEmployee();
        } else {
            console.log(employeeId);
            setError('Employee ID is missing.');
        }
    }, [employeeId]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setEmployee((prevEmployee) => ({ ...prevEmployee, [name]: value }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        // try {
        //     const response = await fetch(`http://localhost:8080/Angajati/update/${employeeId}`, {
        //         method: 'PUT',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //         body: JSON.stringify(employee),
        //     });
        //
        //     if (response.ok) {
        //         navigate('/employees');
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to update employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while updating the employee.');
        // }
        const response = await fetch(`http://localhost:8080/Angajati/update/${employeeId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(employee),
        });
        const errorMessage = await response.text();
        alert(errorMessage);
    };

    return (
        <div className="update-employee-container">

            <form onSubmit={handleSubmit} className="update-employee-form">
                <label>
                    Nume:
                    <input
                        type="text"
                        name="nume"
                        value={employee.nume}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Prenume:
                    <input
                        type="text"
                        name="prenume"
                        value={employee.prenume || ''}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    CNP:
                    <input
                        type="text"
                        name="cnp"
                        value={employee.cnp}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Email:
                    <input
                        type="email"
                        name="email"
                        value={employee.email}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Adresa:
                    <input
                        type="text"
                        name="adresa"
                        value={employee.adresa}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Număr Telefon:
                    <input
                        type="text"
                        name="numarTelefon"
                        value={employee.numarTelefon}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label className="form-label">Functie:</label>
                <select
                    className="form-select"
                    name="functie"
                    value={employee.functie}
                    onChange={handleInputChange}
                    required
                >
                    <option value="">Select a position</option>
                    <option value="Manager">Manager</option>
                    <option value="Mechanic">Mecanic</option>
                    <option value="Receptionist">Receptionist</option>
                    <option value="Cleaner">Curatator</option>
                </select>

                <label>
                    Salariu:
                    <input
                        type="number"
                        name="salariu"
                        value={employee.salariu}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <button type="submit" className="save-button">Save</button>
                <button type="button" onClick={() => navigate(-1)} className="cancel-button">
                    Cancel
                </button>
            </form>
        </div>
    );
};

export default UpdateEmployee;
