import React, { useState } from 'react';
import './Signin.css';

const Signin: React.FC = () => {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [error, setError] = useState<string>('');
    const [success, setSuccess] = useState<string>('');

    const handleSignUp = async (e: React.FormEvent) => {
        e.preventDefault();

        const data = {
            email: email,
            parolaSite: password,
        };

        try {
            const response = await fetch('http://localhost:8080/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                setSuccess('Account created successfully');
                setError('');
                setEmail('');
                setPassword('');
            } else {
                const errorData = await response.json();
                setError(errorData.message || 'Account creation failed');
            }
        } catch (err) {
            setError('An error occurred. Please try again later.');
        }
    };

    return (
        <div className="signin-container">
            <h2>Create Account</h2>
            <form onSubmit={handleSignUp}>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        className ="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <ul>
                        <li>At least one uppercase</li>
                        <li>At least one lowercase</li>
                        <li>At least a number</li>
                    </ul>
                </div>
                {error && <p className="error-message">{error}</p>}
                {success && <p className="success-message">{success}</p>}
                <button type="submit" className="buton">Sign Up</button>
            </form>
        </div>
    );
};

export default Signin;
