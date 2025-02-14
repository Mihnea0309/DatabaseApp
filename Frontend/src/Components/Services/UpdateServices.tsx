import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

interface Client {
    id: number;
    tip: string;
    pret: number | null;
    durata: string;
}

const UpdateServices: React.FC = () => {
    const { serviceId } = useParams<{ serviceId: string }>();
    const navigate = useNavigate();

    const [service, setService] = useState<Client>({
        id: 0,
        tip: '',
        pret: 0,
        durata: '',
    });

    const [error, setError] = useState<string>('');

    useEffect(() => {
        //if (pieceId) {
        const fetchPiece = async () => {
            const response = await fetch(`http://localhost:8080/Servicii/get/${serviceId}`);
            const data = await response.json();
            setService(data);
            // try {
            //     const response = await fetch(`http://localhost:8080/Piese/get/${pieceId}`);
            //     if (response.ok) {
            //         const data = await response.json();
            //         setPiece(data);
            //     } else {
            //         setError('Failed to fetch employee data.');
            //     }
            // } catch (err) {
            //     setError('An error occurred while fetching the employee data.');
            // }
        };

        fetchPiece();
        //} else {
        //    console.log(pieceId);
        //   setError('Employee ID is missing.');
        //}
    }, [serviceId]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setService((prevService) => ({ ...prevService, [name]: value }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        // try {
        //     const response = await fetch(`http://localhost:8080/Piese/update/${pieceId}`, {
        //         method: 'PUT',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //         body: JSON.stringify(piece),
        //     });
        //
        //     if (response.ok) {
        //         navigate('/pieces');
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to update employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while updating the employee.');
        // }
        const response = await fetch(`http://localhost:8080/Servicii/update/${serviceId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(service),
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
                        name="tip"
                        value={service.tip}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Pret:
                    <input
                        type="number"
                        name="pret"
                        value={service.pret || ''}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Durata:
                    <input
                        type="text"
                        name="durata"
                        value={service.durata}
                        onChange={handleInputChange}
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

export default UpdateServices;
