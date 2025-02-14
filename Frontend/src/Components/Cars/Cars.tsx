import React, {useEffect, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';

import InsertUpdate from "../Buttons/Insert & Update/Insert&Update";
import BackToDashboard from "../Buttons/BackToDashboard/BackToDashboard";
import "./Cars.css";
import Update from "../Buttons/Update/Update";


interface SearchResult {
    id: number;
    marca: string;
    model: string;
    anFabricatie: string;
    numar_Inmatriculare: string;
    asigurare: string;
    serieSasiu: string;
}

interface FilterOption {
    name: string;
    path: string;
}

const Cars: React.FC = () => {
    const navigate = useNavigate();
    const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
    const [error, setError] = useState<string>('');
    const [isSearched, setIsSearched] = useState<boolean>(false);
    const [filtersVisible, setFiltersVisible] = useState<boolean>(false);
    const [price, setPrice] = useState<number | string>("");
    const [firstName, setFirstName] = useState<number | string>("");
    const [lastName, setLastName] = useState<number | string>("");
    const [fullName, setFullName] = useState('');

    // Fetch the data when the component is mounted (display table by default)
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/Masini/get`);
            const results: SearchResult[] = await response.json();
            setSearchResults(results);
            setError('');
            setIsSearched(true);
            // try {
            //     const response = await fetch(`http://localhost:8080/Masini/get`);
            //     if (response.ok) {
            //         const results: SearchResult[] = await response.json();
            //         setSearchResults(results);
            //         setError('');
            //         setIsSearched(true);
            //     } else {
            //         const errorData = await response.json();
            //         setError(errorData.message || 'Search failed.');
            //         setSearchResults([]);
            //         setIsSearched(false);
            //     }
            // } catch (err) {
            //     setError('An error occurred during the search.');
            //     setSearchResults([]);
            //     setIsSearched(false);
            // }
        };
        fetchData();
    }, []);

    // const handleDelete = async (id: number) => {
    //     // const confirmDelete = window.confirm('Are you sure you want to delete this record?');
    //     // if (!confirmDelete) return;
    //     // try {
    //     //     const response = await fetch(`http://localhost:8080/Masini/delete/${id}`, {
    //     //         method: 'DELETE',
    //     //     });
    //     //
    //     //     if (response.ok) {
    //     //         // Remove the deleted item from the local state
    //     //         setSearchResults((prevResults) => prevResults.filter((item) => item.id !== id));
    //     //         alert('Record deleted successfully.');
    //     //     } else {
    //     //         const errorData = await response.json();
    //     //         alert(`Failed to delete: ${errorData.message}`);
    //     //     }
    //     // } catch (err) {
    //     //     alert('An error occurred while trying to delete the record.');
    //     // }
    //     const response = await fetch(`http://localhost:8080/Masini/delete/${id}`, {
    //         method: 'DELETE',
    //     });
    //     setSearchResults((prevResults) => prevResults.filter((item) => item.id !== id));
    // };

    const handleDelete = async (id: number, button: HTMLButtonElement) => {
        try {
            const response = await fetch(`http://localhost:8080/Angajati/delete/${id}`, {
                method: "DELETE",
            });

            if (response.ok) {
                setSearchResults((prevResults) => prevResults.filter((item) => item.id !== id));
                button.textContent = "Deleted";
                button.style.color = "gray";
                button.disabled = true;
            } else {
                alert("Failed to delete the record.");
            }
        } catch (error) {
            console.error("Error deleting record:", error);
            alert("An error occurred while deleting the record.");
        }
    };

    const handleInitialClick = (id: number, e: React.MouseEvent<HTMLButtonElement>) => {
        const button = e.currentTarget;

        button.textContent = "Click again to confirm";
        button.style.color = "red";

        const confirmDelete = async () => {
            await handleDelete(id, button);
            button.removeEventListener("click", confirmDelete);
        };

        button.addEventListener("click", confirmDelete);

        setTimeout(() => {
            button.textContent = "Delete";
            button.style.color = "black";

            button.removeEventListener("click", confirmDelete);
            button.addEventListener("click", (event) => handleInitialClick(id, event as unknown as React.MouseEvent<HTMLButtonElement>));
        }, 3000);
    };

    const handleSearch = () => {
        const [lastName, firstName] = fullName.split(' ');
        console.log(firstName);
        navigate(`/cars/filter3/${lastName}/${firstName}`);
    };

    return (
        <div>
            <h1>Sectiunea masinilor</h1>

            <div className = "filters-cars">
                <h1 className="h1-employees">Filtre pentru tabela masinilor</h1>
                <h2 className="h2-employees">Afiseaza masinile care au fost reparate pentru cel putin X lei:</h2>
                <input className="input-employees"
                       type="number"
                       placeholder="Enter price"
                       value={price}
                       onChange={(e) => setPrice(e.target.value)}
                />
                <Link to={`/cars/filter1/${price}`}>
                    <button className="button-employees">Search</button>
                </Link>

                <h2 className="h2-employees">Afiseaza masinile carora nu li s-au facut reparatii:</h2>
                <Link to={`/cars/filter2/`}>
                    <button className="button-cars">Search</button>
                </Link>

                <h2 className="h2-employees">Cauta clientii carora le apartin masinile:</h2>
                <input
                    className="input-employees"
                    type="text"
                    placeholder="Enter Full Name"
                    value={fullName}
                    onChange={(e) => setFullName(e.target.value)}
                />
                {/*<Link to={`/cars/filter3/${lastName}/${firstName}`}>*/}
                    <button className="button-employees" onClick={handleSearch}>Search</button>
                {/*</Link>*/}

            </div>


            <Link to="/cars/insert">
                <InsertUpdate/>
            </Link>
            <div className="select-container with-background">
                <BackToDashboard/>

                {filtersVisible && (
                    <div className="filters-container">
                        <p>Filters will be added here...</p>
                    </div>
                )}

                <div>
                    {isSearched && searchResults.length > 0 && (
                        <table className="data-table">
                            <thead>
                            <tr>
                                <th>Marca</th>
                                <th>Model</th>
                                <th>An Fabricatie</th>
                                <th>Numar Inmatriculare</th>
                                <th>Asigurare</th>
                                <th>Serie Sasiu</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            {searchResults.map((result, index) => (
                                <tr key={index}>
                                    <td>{result.marca}</td>
                                    <td>{result.model}</td>
                                    <td>{result.anFabricatie}</td>
                                    <td>{result.numar_Inmatriculare}</td>
                                    <td>{result.asigurare}</td>
                                    <td>{result.serieSasiu}</td>
                                    <td>
                                        <Link to={`/cars/update/${result.id}`}>
                                            <Update/>
                                        </Link>
                                    </td>
                                    <td>
                                        <button
                                            className="delete-button"
                                            onClick={(e) => handleInitialClick(result.id, e)}
                                        >
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    )}
                    {isSearched && searchResults.length === 0 && <p>No results available</p>}
                </div>
            </div>
        </div>
    );
};

export default Cars;
