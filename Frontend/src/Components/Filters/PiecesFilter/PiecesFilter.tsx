import React, { useEffect, useState } from 'react';
import './PiecesFilter.css';
import {useNavigate} from "react-router-dom";

// interface Employee {
//     nume: string;
//     prenume: string;
//     dataFinalizare: string;
// }

const PiecesFilter: React.FC = () => {
    const navigate = useNavigate();
    const [pieces, setPieces] = useState<any[]>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        fetchCars();
    }, []);

    const fetchCars = async () => {
        try {
            const pret = '1000';
            const response = await fetch(`http://localhost:8080/reparatii_piese/get/filter`);
            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setPieces(data);
                setError('');
            } else {
                setError('There was an issue fetching the data.');
            }
        } catch (err) {
            setError('An error occurred while fetching the data.');
        }
    };

    const handleGoBack = () => {
        navigate(-1);
    };


    return (
        <div>
            <h2>Stocul pieselor si cantitatea folosita intr-o reparatie</h2>

            <button type="button" className="back-button" onClick={handleGoBack}>Go Back</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            <table className="filter-table">
                <thead>
                <tr>
                    <th>Piesa</th>
                    <th>Cantitatea din stoc</th>
                    <th>Cantitatea folosita</th>
                    <th>Descriere reparatie</th>
                </tr>
                </thead>
                <tbody>
                {pieces.map((pieces, index) => (
                    <tr key={index}>
                        <td>{pieces[0]}</td>
                        <td>{pieces[1]}</td>
                        <td>{pieces[2]}</td>
                        <td>{pieces[3]}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default PiecesFilter;
