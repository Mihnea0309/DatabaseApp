import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

interface Car {
    id: number;
    model: string;
    marca: string | null;
    anFabricatie: string;
    numar_Inmatriculare: string;
    asigurare: string;
    serieSasiu: string;
}

const UpdateCars: React.FC = () => {
    const { carId } = useParams<{ carId: string }>();
    const navigate = useNavigate();

    const [car, setCar] = useState<Car>({
        id: 0,
        model: '',
        marca: '',
        anFabricatie: '',
        numar_Inmatriculare: '',
        asigurare: '',
        serieSasiu: ''
    });

    const [error, setError] = useState<string>('');

    useEffect(() => {
        if (carId) {
            const fetchEmployee = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/Masini/get/${carId}`);
                    if (response.ok) {
                        const data = await response.json();
                        setCar(data);
                    } else {
                        setError('Failed to fetch employee data.');
                    }
                } catch (err) {
                    setError('An error occurred while fetching the employee data.');
                }
            };

            fetchEmployee();
        } else {
            console.log(carId);
            setError('Employee ID is missing.');
        }
    }, [carId]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setCar((prevCar) => ({ ...prevCar, [name]: value }));
    };


    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        // try {
        //     const response = await fetch(`http://localhost:8080/Masini/update/${carId}`, {
        //         method: 'PUT',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //         body: JSON.stringify(car),
        //     });
        //
        //     if (response.ok) {
        //         navigate('/cars');
        //     } else {
        //         const errorData = await response.json();
        //         setError(errorData.message || 'Failed to update employee.');
        //     }
        // } catch (err) {
        //     setError('An error occurred while updating the employee.');
        // }
        const response = await fetch(`http://localhost:8080/Masini/update/${carId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(car),
        });
        const errorMessage = await response.text();
        alert(errorMessage);
    };


    return (
        <div className="update-employee-container">

            <form onSubmit={handleSubmit} className="update-employee-form">
                <label>
                    Marca:
                    <input
                        type="text"
                        name="marca"
                        value={car.marca || ''}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Model:
                    <input
                        type="text"
                        name="model"
                        value={car.model}
                        onChange={handleInputChange}
                    />
                </label>

                <label>
                    An Fabricatie:
                    <input
                        type="number"
                        name="anFabricatie"
                        value={car.anFabricatie}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Numar de inmatriculare:
                    <input
                        type="text"
                        name="numar_Inmatriculare"
                        value={car.numar_Inmatriculare}
                        onChange={handleInputChange}
                        required
                    />
                </label>

                <label>
                    Asigurare:
                    <select
                        className="form-select"
                        name="asigurare"
                        value={car.asigurare}
                        onChange={handleInputChange}
                        required
                    >
                        <option value="">Selecteaza o optiune</option>
                        <option value="Da">Da</option>
                        <option value="Nu">Nu</option>
                    </select>
                </label>

                <label>
                    Serie Sasiu:
                    <input
                        type="text"
                        name="serieSasiu"
                        value={car.serieSasiu}
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

export default UpdateCars;
