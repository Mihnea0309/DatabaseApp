import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const InsertServices: React.FC = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        tip: '',
        pret: 0,
        durata: 0,
    });
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // try {
        //     const response = await fetch('http://localhost:8080/Servicii/add', {
        //         method: 'POST',
        //         headers: { 'Content-Type': 'application/json' },
        //         body: JSON.stringify(formData),
        //     });
        //
        //     if (response.ok) {
        //         alert('Employee added successfully!');
        //         navigate('/services'); // Navigate back to the Employees page
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to add employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while adding the employee.');
        // }
        const response = await fetch('http://localhost:8080/Servicii/add', {
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
                    <label className="form-label">Tip:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="tip"
                        value={formData.tip}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Pret:</label>
                    <input
                        className="form-input"
                        type="number"
                        name="pret"
                        value={formData.pret}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Durata:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="durata"
                        value={formData.durata}
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

export default InsertServices;
