import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './UpdateClients.css';

interface Client {
    id: number;
    nume: string;
    prenume: string | null;
    email: string;
    adresa: string;
    telefon: string;
}

const UpdateClients: React.FC = () => {
    const { clientId } = useParams<{ clientId: string }>();
    const navigate = useNavigate();

    const [client, setClient] = useState<Client>({
        id: 0,
        nume: '',
        prenume: '',
        email: '',
        adresa: '',
        telefon: '',
    });

    const [error, setError] = useState<string>('');

    useEffect(() => {
        if (clientId) {
            const fetchEmployee = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/Clienti/get/${clientId}`);
                    if (response.ok) {
                        const data = await response.json();
                        setClient(data);
                    } else {
                        setError('Failed to fetch employee data.');
                    }
                } catch (err) {
                    setError('An error occurred while fetching the employee data.');
                }
            };

            fetchEmployee();
        } else {
            console.log(clientId);
            setError('Employee ID is missing.');
        }
    }, [clientId]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setClient((prevEmployee) => ({ ...prevEmployee, [name]: value }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/Clienti/update/${clientId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(client),
            });

            if (response.ok) {
                navigate('/clients');
            } else {
                const errorData = await response.json();
                setError(errorData.message || 'Failed to update employee.');
            }
        } catch (err) {
            setError('An error occurred while updating the employee.');
        }
    };

    return (
        <div className="update-employee-container">

            <form onSubmit={handleSubmit} className="update-employee-form">
                <label>
                    Nume:
                    <input
                        type="text"
                        name="nume"
                        value={client.nume}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Prenume:
                    <input
                        type="text"
                        name="prenume"
                        value={client.prenume || ''}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Email:
                    <input
                        type="email"
                        name="email"
                        value={client.email}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Adresa:
                    <input
                        type="text"
                        name="adresa"
                        value={client.adresa}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Număr Telefon:
                    <input
                        type="text"
                        name="telefon"
                        value={client.telefon}
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

export default UpdateClients;
