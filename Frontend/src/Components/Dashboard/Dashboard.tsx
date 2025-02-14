import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Dashboard.css';

interface DashboardProps {
    onLogout: () => void;
}

const Dashboard: React.FC<DashboardProps> = ({ onLogout }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("isAuthenticated");
        navigate('/');
    };

    return (
        <div>
            <h1>Bun venit!</h1>
            <Link to="/">
                <button className="logout-button" onClick={onLogout}>Logout</button>
            </Link>
            <div className="dashboard-container">
                <Link to="/employees">
                    <button className="buton1">Angajati</button>
                </Link>
                <p></p>
                <Link to="/clients">
                    <button className="buton2">Clienti</button>
                </Link>
                <p></p>
                <Link to="/cars">
                    <button className="buton3">Masini</button>
                </Link>
                <p></p>
                <Link to="/pieces">
                    <button className="buton4">Piese</button>
                </Link>
                <p></p>
                <Link to="/services">
                    <button className="buton5">Servicii</button>
                </Link>
                <p></p>
                <Link to="/repairs">
                    <button className="buton6">Reparatii</button>
                </Link>
            </div>
        </div>
    );
};

export default Dashboard;
