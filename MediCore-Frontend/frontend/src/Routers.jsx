import React from 'react';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/Login.jsx';
import Register from './pages/Register.jsx';
import DashboardLayout from './components/DashboardLayout.jsx';
import Home from './pages/Home.jsx';
import Doctors from './pages/Doctors.jsx';
import Patients from "./pages/Patients.jsx";
import Admission from './pages/Admission.jsx'
import PatientRoom from "./pages/PatientRoom.jsx";
import Food from "./pages/Food.jsx";

const Routers = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>

                <Route path="/" element={<DashboardLayout/>}>
                    <Route path="home" element={<Home/>}/>
                    <Route path="doctors" element={<Doctors/>}/>
                    <Route path="patients" element={<Patients/>}/>
                    <Route path="/addmission" element={<Admission/>}/>
                    <Route path="/patients-rooms" element={<PatientRoom/>}/>
                    <Route path="/food" element={<Food/>}/>
                </Route>

                <Route path="/" element={<Login/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default Routers;
