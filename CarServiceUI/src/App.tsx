import React, {useEffect, useState} from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import './App.css';
import Login from "./Components/Login/LoginComp";
import Dashboard from './Components/Dashboard/Dashboard';
import Delete from './Components/Delete/Delete';
import Insert from "./Components/Insert/Insert";
import Select from "./Components/Select/Select";
import Update from "./Components/Update/Update";
import Signin from "./Components/Signin/Signin";

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
        localStorage.setItem("isAuthenticated", "true");  // Save login status
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
                <Route path="/select" element={<Select />} />
                <Route path="/insert" element={<Insert />} />
                <Route path="/update" element={<Update />} />
                <Route path="/delete" element={<Delete />} />
                <Route path="/signin" element={<Signin />}/>
            </Routes>
        </Router>
    );
};

export default App;
