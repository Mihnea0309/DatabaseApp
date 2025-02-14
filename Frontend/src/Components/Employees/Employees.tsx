import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Employees.css';
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
    numarTelefon: string;
    functie: string;
    salariu: string;
}

interface FilterOption {
    name: string;
    path: string;
}

const Employees: React.FC = () => {
    const navigate = useNavigate();
    const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
    const [error, setError] = useState<string>('');
    const [isSearched, setIsSearched] = useState<boolean>(false);
    const [filtersVisible, setFiltersVisible] = useState<boolean>(false);
    const [year, setYear] = useState<number | string>("");
    const [hours, setHours] = useState<number | string>("");
    const [salary, setSalary] = useState<number | string>("");

    // Fetch the data when the component is mounted (display table by default)
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`http://localhost:8080/Angajati/get`);
            const results: SearchResult[] = await response.json();
            setSearchResults(results);
            setError('');
            setIsSearched(true);
            console.log(results);
        };
        fetchData();
    }, []);

    // Function to handle delete with confirmation
    // const handleDelete = async (id: number) => {
    //     const response = await fetch(`http://localhost:8080/Angajati/delete/${id}`, {
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


    return (
        <div>
            <h1>Sectiunea angajatilor</h1>

            <div className = "filters-employees">
                <h1 className="h1-employees">Filtre pentru tabela angajatilor</h1>
                <h2 className="h2-employees">Afiseaza angajatii care au terminat reparatiile inainte de anul:</h2>
                <input className="input-employees"
                    type="number"
                    placeholder="Enter year"
                    value={year}
                    onChange={(e) => setYear(e.target.value)}
                />
                <Link to={`/employees/filter1/${year}`}>
                    <button className="button-employees">Search</button>
                </Link>

                <h2 className="h2-employees">Afiseaza salariile angajitilor care au petrecut cel putin X ore adunate in reparatii:</h2>
                <input className="input-employees"
                    type="number"
                    placeholder="Enter hours"
                    value={hours}
                    onChange={(e) => setHours(e.target.value)}
                />
                <Link to={`/employees/filter2/${hours}`}>
                    <button className="button-employees">Search</button>
                </Link>

                <h2 className="h2-employees">Afiseaza angajatii care nu lucreaza la reparatii si au salariul minim de:</h2>
                <input className="input-employees"
                    type="number"
                    placeholder="Enter salary"
                    value={salary}
                    onChange={(e) => setSalary(e.target.value)}
                />
                <Link to={`/employees/filter3/${salary}`}>
                    <button className="button-employees">Search</button>
                </Link>

            </div>

            <Link to="/employees/insert">
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
                                <th>Prenume</th>
                                <th>CNP</th>
                                <th>Email</th>
                                <th>Adresă</th>
                                <th>Număr Telefon</th>
                                <th>Funcție</th>
                                <th>Salariu</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            {searchResults.map((result, index) => (
                                <tr key={index}>
                                    <td>{result.nume}</td>
                                    <td>{result.prenume}</td>
                                    <td>{result.cnp}</td>
                                    <td>{result.email}</td>
                                    <td>{result.adresa}</td>
                                    <td>{result.numarTelefon}</td>
                                    <td>{result.functie}</td>
                                    <td>{result.salariu}</td>
                                    <td>
                                        <Link to={`/employees/update/${result.id}`} className="update-link">
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

export default Employees;
