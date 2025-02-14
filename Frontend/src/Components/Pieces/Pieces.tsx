import React, {useEffect, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import InsertUpdate from "../Buttons/Insert & Update/Insert&Update";
import BackToDashboard from "../Buttons/BackToDashboard/BackToDashboard";
import "./Pieces.css";
import Update from "../Buttons/Update/Update";


interface SearchResult {
    id: number;
    nume: string;
    pret: string;
    stoc: string;
}

interface FilterOption {
    name: string;
    path: string;
}

const Pieces: React.FC = () => {
    const navigate = useNavigate();
    const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
    const [error, setError] = useState<string>('');
    const [isSearched, setIsSearched] = useState<boolean>(false);
    const [filtersVisible, setFiltersVisible] = useState<boolean>(false); // state pentru a comuta filtrele

    // Fetch the data when the component is mounted (display table by default)
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/Piese/get`);
            const results: SearchResult[] = await response.json();
            setSearchResults(results);
            setError('');
            setIsSearched(true);
            // try {
            //     const response = await fetch(`http://localhost:8080/Piese/get`);
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
    //     // if (!confirmDelete) return; // Stop if user cancels
    //     //
    //     // try {
    //     //     const response = await fetch(`http://localhost:8080/Piese/delete/${id}`, {
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
    //     const response = await fetch(`http://localhost:8080/Piese/delete/${id}`, {
    //         method: 'DELETE',
    //     });
    //     setSearchResults((prevResults) => prevResults.filter((item) => item.id !== id));
    //     alert('Record deleted successfully.');
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

    const filters: FilterOption[] = [
        { name: 'Afiseaza stocul pieselor si cantitatea folosita intr-o reparatie', path: '/pieces/filter1' },
    ];

    return (
        <div>
            <h1>Sectiunea pieselor</h1>

            <div className = "filters-pieces">
                <h1 className="h1-employees">Filtre pentru tabela pieselor</h1>

                <h2 className="h2-employees">Afiseaza stocul pieselor si cantitatea folosita intr-o reparatie:</h2>
                <Link to={`/pieces/filter1`}>
                    <button className="button-pieces">Search</button>
                </Link>

            </div>

            <Link to="/pieces/insert">
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
                                <th>Nume</th>
                                <th>Pret</th>
                                <th>Stoc</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            {searchResults.map((result, index) => (
                                <tr key={index}>
                                    <td>{result.nume}</td>
                                    <td>{result.pret}</td>
                                    <td>{result.stoc}</td>
                                    <td>
                                        <Link to={`/pieces/update/${result.id}`}>
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

export default Pieces;
