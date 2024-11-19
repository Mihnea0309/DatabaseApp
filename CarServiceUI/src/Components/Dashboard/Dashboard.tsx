import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

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
            <h1>Welcome to the Dashboard!</h1>

            <div>
                <Link to="/select">
                    <button>Select</button>
                </Link>
                <Link to="/insert">
                    <button>Insert</button>
                </Link>
                <Link to="/update">
                    <button>Update</button>
                </Link>
                <Link to="/delete">
                    <button>Delete</button>
                </Link>
            </div>
            <Link to="/">
                <button onClick={onLogout}>Logout</button>
            </Link>
        </div>
    );
};

export default Dashboard;
