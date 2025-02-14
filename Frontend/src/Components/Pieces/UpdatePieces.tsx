import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

interface Client {
    id: number;
    nume: string;
    pret: number | null;
    stoc: number;
}

const UpdatePieces: React.FC = () => {
    const { pieceId } = useParams<{ pieceId: string }>();
    const navigate = useNavigate();

    const [piece, setPiece] = useState<Client>({
        id: 0,
        nume: '',
        pret: 0,
        stoc: 0,
    });

    const [error, setError] = useState<string>('');

    useEffect(() => {
        //if (pieceId) {
            const fetchPiece = async () => {
                const response = await fetch(`http://localhost:8080/Piese/get/${pieceId}`);
                const data = await response.json();
                setPiece(data);
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
    }, [pieceId]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setPiece((prevPiece) => ({ ...prevPiece, [name]: value }));
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
        const response = await fetch(`http://localhost:8080/Piese/update/${pieceId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(piece),
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
                        value={piece.nume}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Pret:
                    <input
                        type="number"
                        name="pret"
                        value={piece.pret || ''}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    Stoc:
                    <input
                        type="number"
                        name="stoc"
                        value={piece.stoc}
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

export default UpdatePieces;
