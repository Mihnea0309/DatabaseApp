import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const InsertRepairs: React.FC = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        descriere: '',
        costManopera: 0,
        dataInceput: '',
        dataFinalizare: '',
        nrInmatriculare: '',
        numePiesa: '',
        cantitatePiesa: ''
    });
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        console.log(`Field name: ${name}, value: ${value}`);
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
        const response = await fetch(`http://localhost:8080/Reparatii/add?nrInmatriculare=${formData.nrInmatriculare}`, {
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
                    <label className="form-label">Descriere:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="descriere"
                        value={formData.descriere}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Cost:</label>
                    <input
                        className="form-input"
                        type="number"
                        name="costManopera"
                        value={formData.costManopera}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Data Inceput:</label>
                    <input
                        className="form-input"
                        type="date"
                        name="dataInceput"
                        value={formData.dataInceput}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Data Finalizare:</label>
                    <input
                        className="form-input"
                        type="date"
                        name="dataFinalizare"
                        value={formData.dataFinalizare}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Numarul de inmatriculare al masinii:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="nrInmatriculare"
                        value={formData.nrInmatriculare}
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

export default InsertRepairs;
