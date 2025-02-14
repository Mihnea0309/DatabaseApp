import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './InsertClients.css';

const InsertClients: React.FC = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        nume: '',
        prenume: '',
        email: '',
        adresa: '',
        telefon: '',
    });
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // try {
        //     const response = await fetch('http://localhost:8080/Clienti/add', {
        //         method: 'POST',
        //         headers: { 'Content-Type': 'application/json' },
        //         body: JSON.stringify(formData),
        //     });
        //
        //     if (response.ok) {
        //         alert('Employee added successfully!');
        //         navigate('/clients');
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to add employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while adding the employee.');
        // }
        const response = await fetch('http://localhost:8080/Clienti/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        });
        const errorMessage = await response.text();
        alert(errorMessage);
    };

    const handleGoBack = () => {
        navigate(-1); // Navighează înapoi la pagina anterioară
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
                    <label className="form-label">Telefon:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="telefon"
                        value={formData.telefon}
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

export default InsertClients;
