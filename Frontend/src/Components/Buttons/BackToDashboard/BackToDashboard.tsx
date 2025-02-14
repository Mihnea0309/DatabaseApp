import React, {useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './BackToDashboard.css';


const BackToDashboard: React.FC = () => {
    const navigate = useNavigate();
    const handleBackToDashboard = () => {
        navigate('/dashboard');
    };

    return (
        <button className="back-button" onClick={handleBackToDashboard}>
            Back to Dashboard
        </button>
    );
};

export default BackToDashboard;
