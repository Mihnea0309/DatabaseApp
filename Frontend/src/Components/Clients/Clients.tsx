import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Clients.css';
import InsertUpdate from "../Buttons/Insert & Update/Insert&Update";
import BackToDashboard from "../Buttons/BackToDashboard/BackToDashboard";
import Update from "../Buttons/Update/Update";

interface SearchResult {
    id: number;
    nume: string;
    prenume: string | null;
    cnp: string;
    email: string;
    adresa: string;
    telefon: string;
}

interface FilterOption {
    name: string;
    path: string;
}

const Clients: React.FC = () => {
    const navigate = useNavigate();
    const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
    const [error, setError] = useState<string>('');
    const [isSearched, setIsSearched] = useState<boolean>(false);
    const [filtersVisible, setFiltersVisible] = useState<boolean>(false);
    const [insurance, setInsurance] = useState<number | string>("");
    const [cost, setCost] = useState<number | string>("");

    // Fetch the data when the component is mounted (display table by default)
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/Clienti/get`);
            const results: SearchResult[] = await response.json();
            setSearchResults(results);
            setError('');
            setIsSearched(true);
            // try {
            //     const response = await fetch(`http://localhost:8080/Clienti/get`);
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

    const handleBackToDashboard = () => {
        navigate('/dashboard');
    };

    const handleToggleFilters = () => {
        setFiltersVisible(prevState => !prevState); // Toggle filters visibility
    };

    // const handleDelete = async (id: number) => {
    //     // const confirmDelete = window.confirm('Are you sure you want to delete this record?');
    //     // if (!confirmDelete) return; // Stop if user cancels
    //     //
    //     // try {
    //     //     const response = await fetch(`http://localhost:8080/Clienti/delete/${id}`, {
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
    //     const response = await fetch(`http://localhost:8080/Clienti/delete/${id}`, {
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
        { name: 'Afiseaza clientii care au asigurare la masini', path: '/clients/filter1' },
        { name: 'Afiseaza suma totala pe care clientii trebuie sa o plateasca pe reparatie', path: '/clients/filter2' },
        { name: 'Afiseaza clientii care au platit minim 1000 de lei pe manopera', path: '/clients/filter3'}
    ];

    return (
        <div>
            <h1>Sectiunea clientilor</h1>

            <div className = "filters-clients">
                <h1 className="h1-employees">Filtre pentru tabela clientilor</h1>
                <h2 className="h2-employees">Afiseaza clientii care au sau nu au asigurare la masini:</h2>
                <select
                    className="form-select"
                    name="asigurare"
                    value={insurance}
                    onChange={(e) => setInsurance(e.target.value)}
                    required
                >
                    <option value="">Alege o optiune</option>
                    <option value="Da">Da</option>
                    <option value="Nu">Nu</option>
                </select>
                <Link to={`/clients/filter1/${insurance}`}>
                    <button className="button-employees">Search</button>
                </Link>

                <h2 className="h2-employees">Afiseaza suma totala pe care clientii trebuie sa o plateasca pe reparatie:</h2>
                <Link to={`/clients/filter2`}>
                    <button className="button-employees">Search</button>
                </Link>

                <h2 className="h2-employees">Afiseaza clientii care au platit minim X lei pe manopera:</h2>
                <input className="input-employees"
                       type="number"
                       placeholder="Enter cost"
                       value={cost}
                       onChange={(e) => setCost(e.target.value)}
                />
                <Link to={`/clients/filter3/${cost}`}>
                    <button className="button-employees">Search</button>
                </Link>

            </div>

            <Link to="/clients/insert">
                <InsertUpdate/>
            </Link>

            <div className="select-container with-background-clients">
                <BackToDashboard/>

                <div>
                    {isSearched && searchResults.length > 0 && (
                        <table className="data-table-clients">
                            <thead>
                            <tr>
                                <th>Nume</th>
                                <th>Prenume</th>
                                <th>Adresa</th>
                                <th>Telefon</th>
                                <th>Email</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            {searchResults.map((result, index) => (
                                <tr key={index}>
                                    <td>{result.nume}</td>
                                    <td>{result.prenume}</td>
                                    <td>{result.adresa}</td>
                                    <td>{result.telefon}</td>
                                    <td>{result.email}</td>
                                    <td>
                                        <Link to={`/clients/update/${result.id}`}>
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

export default Clients;
