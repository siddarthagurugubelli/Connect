import { Route, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import { AuthProvider } from './context/AuthContext';
import Register from './pages/Register';
import Login from './pages/Login'
import Home from './pages/Home';
import Profile from './pages/Profile';
import Chat from "./pages/Chat"
import { useEffect, useState } from 'react';
import {PrivateRoute} from "./components/PrivateRoute"
import Accounts from './pages/Accounts';

function App() {

  return (
    <>
      <AuthProvider>
        <Routes>
          <Route path='/' element={<Register />} />
          <Route path='/login' element={<Login />} />
          <Route element={<PrivateRoute/>}>
            <Route exact path='/home' element={<Home/>}/>
            <Route path='/profile/:userId' element={<Profile />} />
            <Route path='/chat' element={<Chat />} />
            <Route path='/accounts' element={<Accounts />} />
          </Route>
        </Routes>
      </AuthProvider>

    </>
  );
}

export default App;
