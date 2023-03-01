import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

export const PrivateRoute = () => {
    const auth = localStorage.getItem("token"); 
    return auth !== null ? <Outlet /> : <Navigate to="/login" />;
}