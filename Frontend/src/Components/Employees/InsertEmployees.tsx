import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './InsertEmployees.css';

const InsertEmployees: React.FC = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        nume: '',
        prenume: '',
        cnp: '',
        email: '',
        adresa: '',
        numarTelefon: '',
        functie: '',
        salariu: '',
    });
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        //
        // try {
        //     const response = await fetch('http://localhost:8080/Angajati/add', {
        //         method: 'POST',
        //         headers: { 'Content-Type': 'application/json' },
        //         body: JSON.stringify(formData),
        //     });
        //
        //     if (response.ok) {
        //         alert('Employee added successfully!');
        //         navigate('/employees'); // Navigate back to the Employees page
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to add employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while adding the employee.');
        // }
        const response = await fetch('http://localhost:8080/Angajati/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        });
        const errorMessage = await response.text();
        alert(errorMessage);
    };

    const handleGoBack = () => {
        navigate(-1);
    };

    return (
        <div className="insert-employees-container">
            <form onSubmit={handleSubmit} className="insert-employees-form">
                <div className="form-group">
                    <label className="form-label">Nume:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="nume"
                        value={formData.nume}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Prenume:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="prenume"
                        value={formData.prenume}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">CNP:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="cnp"
                        value={formData.cnp}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Email:</label>
                    <input
                        className="form-input"
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Adresa:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="adresa"
                        value={formData.adresa}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Numar de telefon:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="numarTelefon"
                        value={formData.numarTelefon}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Functie:</label>
                    <select
                        className="form-select"
                        name="functie"
                        value={formData.functie}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Select a position</option>
                        <option value="Manager">Manager</option>
                        <option value="Mechanic">Mecanic</option>
                        <option value="Receptionist">Receptionist</option>
                        <option value="Cleaner">Curatator</option>
                    </select>
                </div>
                <div className="form-group">
                    <label className="form-label">Salariu:</label>
                    <input
                        className="form-input"
                        type="number"
                        name="salariu"
                        value={formData.salariu}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="button-container">
                    <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>
                    <button type="submit" className="submit-button">Adauga</button>
                </div>
            </form>
        </div>
    );
};

export default InsertEmployees;
