import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

interface Client {
    id: number;
    descriere: string;
    costManopera: number | null;
    dataInceput: string;
    dataFinalizare: string;
}

const UpdateRepairs: React.FC = () => {
    const { repairId } = useParams<{ repairId: string }>();
    const navigate = useNavigate();

    const [repair, setRepair] = useState<Client>({
        id: 0,
        descriere: '',
        costManopera: 0,
        dataInceput: '',
        dataFinalizare: ''
    });

    const [error, setError] = useState<string>('');

    useEffect(() => {
        //if (pieceId) {
        const fetchPiece = async () => {
            const response = await fetch(`http://localhost:8080/Reparatii/get/${repairId}`);
            const data = await response.json();
            setRepair(data);
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
    }, [repairId]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setRepair((prevService) => ({ ...prevService, [name]: value }));
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
        const response = await fetch(`http://localhost:8080/Reparatii/update/${repairId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(repair),
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
                        name="descriere"
                        value={repair.descriere}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Pret:
                    <input
                        type="number"
                        name="costManopera"
                        value={repair.costManopera || ''}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Durata:
                    <input
                        type="date"
                        name="dataInceput"
                        value={repair.dataInceput}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Durata:
                    <input
                        type="date"
                        name="durata"
                        value={repair.dataFinalizare}
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

export default UpdateRepairs;
