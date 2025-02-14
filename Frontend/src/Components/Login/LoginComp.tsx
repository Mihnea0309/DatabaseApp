import React, { useState } from 'react';
import './LoginComp.css'
import {Link} from "react-router-dom";

interface LoginProps {
    onLoginSuccess: () => void;
}

const LoginPage: React.FC<LoginProps> = ({ onLoginSuccess }) => {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [error, setError] = useState<string>('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const data = {
            email: email,
            parolaSite: password,
        };

        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
                credentials: 'include',
            });

            if (response.ok) {
                onLoginSuccess();
            } else {
                const errorData = await response.json();
                setError(errorData.message || 'Invalid credentials');
            }
        } catch (err) {
            setError('An error occurred. Please try again later.');
        }
    };

    return (
        <div className="login-container">
            <h2 className="login">Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="email">
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="password">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {error && <p>{error}</p>}
                <button type="submit" className="butonLogin">Login</button>
                <Link to = "/signin">
                    <button className="signin">Create account</button>
                </Link>
            </form>
        </div>
    );
};

export default LoginPage;
