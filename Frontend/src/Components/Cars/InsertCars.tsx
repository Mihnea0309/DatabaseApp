import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


const InsertCars: React.FC = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        marca: '',
        model: '',
        anFabricatie: '',
        numar_Inmatriculare: '',
        asigurare: '',
        serieSasiu: '',
        email: ''
    });
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // try {
        //     const response = await fetch(`http://localhost:8080/Masini/add?email=${formData.email}`, {
        //         method: 'POST',
        //         headers: { 'Content-Type': 'application/json' },
        //         body: JSON.stringify(formData),
        //     });
        //
        //     if (response.ok) {
        //         alert('Car added successfully!');
        //         navigate('/cars');
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to add employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while adding the employee.');
        // }
        const response = await fetch(`http://localhost:8080/Masini/add?email=${formData.email}`, {
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
                    <label className="form-label">Marca:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="marca"
                        value={formData.marca}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Model:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="model"
                        value={formData.model}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Anul fabricatiei:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="anFabricatie"
                        value={formData.anFabricatie}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Numar de inmatriculare:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="numar_Inmatriculare"
                        value={formData.numar_Inmatriculare}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Asigurare:</label>
                    <select
                        className="form-select"
                        name="asigurare"
                        value={formData.asigurare}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Selecteaza o optiune</option>
                        <option value="Da">Da</option>
                        <option value="Nu">Nu</option>
                    </select>
                </div>
                <div className="form-group">
                    <label className="form-label">Serie sasiu:</label>
                    <input
                        className="form-input"
                        type="text"
                        name="serieSasiu"
                        value={formData.serieSasiu}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Email-ul clientului care detine masina:</label>
                    <input
                        className="form-input"
                        type="email"
                        name="email"
                        value={formData.email}
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

export default InsertCars;
