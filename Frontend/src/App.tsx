import React, {useEffect, useState} from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import './App.css';
import Login from "./Components/Login/LoginComp";
import Dashboard from './Components/Dashboard/Dashboard';
import Signin from "./Components/Signin/Signin";
import Employees from "./Components/Employees/Employees";
import Clients from "./Components/Clients/Clients";
import Cars from "./Components/Cars/Cars";
import Pieces from "./Components/Pieces/Pieces";
import Repairs from "./Components/Repairs/Repairs";
import Services from "./Components/Services/Services";
import InsertEmployees from "./Components/Employees/InsertEmployees";
import UpdateEmployee from "./Components/Employees/UpdateEmployee";
import InsertClients from "./Components/Clients/InsertClients";
import UpdateClients from "./Components/Clients/UpdateClients";
import EmployeesFilter from "./Components/Filters/EmployeesFilter/EmployeesFilter";
import ClientsFilter from "./Components/Filters/ClientsFilter/ClientsFilter";
import CarsFilter from "./Components/Filters/CarsFilter/CarsFilter";
import PiecesFilter from "./Components/Filters/PiecesFilter/PiecesFilter";
import EmployeesFilter2 from "./Components/Filters/EmployeesFilter/EmployeesFilter2";
import ClientsFilter2 from "./Components/Filters/ClientsFilter/ClientsFilter2";
import EmployeesFilter3 from "./Components/Filters/EmployeesFilter/EmployeesFilter3";
import CarsFilter2 from "./Components/Filters/CarsFilter/CarsFilter2";
import ClientsFilter3 from "./Components/Filters/ClientsFilter/ClientsFilter3";
import RepairsFilter from "./Components/Filters/RepairsFilter/RepairsFilter";
import InsertCars from "./Components/Cars/InsertCars";
import UpdateCars from "./Components/Cars/UpdateCars";
import CarsFilter3 from "./Components/Filters/CarsFilter/CarsFilter3";
import InsertPieces from "./Components/Pieces/InsertPieces";
import UpdatePieces from "./Components/Pieces/UpdatePieces";
import ServicesFilter from "./Components/Filters/ServicesFilter/ServicesFilter";
import InsertServices from "./Components/Services/InsertServices";
import UpdateServices from "./Components/Services/UpdateServices";
import InsertRepairs from "./Components/Repairs/InsertRepairs";
import UpdateRepairs from "./Components/Repairs/UpdateRepairs";

const App: React.FC = () => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

    useEffect(() => {
        const storedAuth = localStorage.getItem("isAuthenticated");
        if (storedAuth === "true") {
            setIsAuthenticated(true);
        }
    }, []);

    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
        localStorage.setItem("isAuthenticated", "true");
    };

    const handleLogout = () => {
        setIsAuthenticated(false);
        localStorage.removeItem("isAuthenticated");
    };

    return (
        <Router>
            <Routes>
                <Route path="/" element={isAuthenticated ? <Navigate to="/dashboard" /> : <Login onLoginSuccess={handleLoginSuccess} />} />
                <Route path="/dashboard" element={isAuthenticated ? <Dashboard onLogout={handleLogout}/> : <Navigate to="/" />} />
                <Route path="/employees" element={<Employees />} />
                <Route path="/clients" element={<Clients />} />
                <Route path="/cars" element={<Cars/>} />
                <Route path="/services" element={<Services/>} />
                <Route path="/pieces" element={<Pieces/>} />
                <Route path="/repairs" element={<Repairs/>} />
                <Route path="/employees/insert" element={<InsertEmployees />} />
                <Route path="/signin" element={<Signin />}/>
                <Route path="/employees/update/:employeeId" element={<UpdateEmployee/>} />
                <Route path="/clients/insert" element={<InsertClients/>}/>
                <Route path="/clients/update/:clientId" element={<UpdateClients/>}/>
                <Route path="/employees/filter1/:year" element={<EmployeesFilter/>} />
                <Route path="/clients/filter1/:insurance" element={<ClientsFilter/>} />
                <Route path="/cars/filter1/:price" element={<CarsFilter/>} />
                <Route path="/pieces/filter1" element={<PiecesFilter/>}/>
                <Route path="/employees/filter2/:hours" element={<EmployeesFilter2/>} />
                <Route path="/clients/filter2" element={<ClientsFilter2/>} />
                <Route path="/employees/filter3/:salary" element={<EmployeesFilter3 />} />
                <Route path="/cars/filter2" element={<CarsFilter2 />}/>
                <Route path="/clients/filter3/:cost" element={<ClientsFilter3 />} />
                <Route path="/repairs/filter1/:year" element={<RepairsFilter/>}/>
                <Route path="/cars/insert" element={<InsertCars/>}/>
                <Route path="/cars/update/:carId" element={<UpdateCars/>} />
                <Route path="/cars/filter3/:lastName/:firstName" element={<CarsFilter3/>}/>
                <Route path="/pieces/insert" element={<InsertPieces/>} />
                <Route path="/pieces/update/:pieceId" element={<UpdatePieces />}/>
                <Route path="/services/filter1" element={<ServicesFilter />} />
                <Route path="/services/insert" element={<InsertServices/>}/>
                <Route path="/services/update/:serviceId" element={<UpdateServices />} />
                <Route path="/repairs/insert" element={<InsertRepairs />}/>
                <Route path="/repairs/update/:repairId" element={<UpdateRepairs/>} />
            </Routes>
        </Router>
    );
};

export default App;
