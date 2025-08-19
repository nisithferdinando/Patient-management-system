import React, {useEffect, useState} from 'react'
import {getKeyData} from "../dropdown/keyValueData.js";
import axiosInstance from "../util/axiosInstance.js";
import ReactLoader from "../components/ReactLoader.jsx";
import {Modal, Pagination, Table} from "semantic-ui-react";
import search from "../assets/search.jpg"
import AddPatient from "../components/Patients/AddPatient.jsx";
import {toast} from "react-toastify";


const Patients = () => {

    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        regNo: '',
        active: '',
    });

    const [active, setActive] = useState([]);
    const [open, setOpen] = useState(false);
    const [patients, setPatients] = useState([]);
    const [editPatient, setEditPatient] = useState(null);
    const [activePage, setActivePage] = useState(1);
    const [loading, setLoading] = useState(false);
    const itemsPage = 8;

    const totalPages = Math.ceil(patients.length / itemsPage);

    const handlePaginationChange = (e, {activePage}) => {
        setActivePage(activePage);
    };
    const searchPatient = patients.slice(
        (activePage - 1) * itemsPage,
        activePage * itemsPage,
    );

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((data) => ({
            ...data,
            [name]: value,
        }));
    }
    useEffect(() => {
        (async () => {
            const active = await getKeyData("active");
            setActive(active);
        })();
    }, []);

    const handleRowDoubleClick = async (id) => {
        try {
            const response = await axiosInstance.get(`/patient/${id}`);
            setEditPatient(response.data);
            setOpen(true);
        } catch (error) {
            console.log(error);
            toast.error("failed to get the patient");
        }
    };

    const handleSearch = async (e) => {
        e.preventDefault();
        setLoading(true);
        const patientSearch = {
            firstName: formData.firstName || null,
            lastName: formData.lastName || null,
            regNo: formData.regNo || null,
            active: formData.active === "" ? -2 : parseInt(formData.active),
        }
        try {
            const response = await axiosInstance.post("/patient/search", patientSearch);
            await new Promise(resolve => setTimeout(resolve, 700));
            setPatients(response.data);
            setActivePage(1);
        } catch (error) {
            console.log("error", error);
        }
        setLoading(false);
    }

    const handleRest = () => {
        setFormData({
            firstName: '',
            lastName: '',
            regNo: '',
            active: '',
        });
    };
    return (
        <div>
            <div className="flex justify-end mt-4">
                <button class="ui primary button" onClick={() => {
                    setOpen(true)
                }}>Add Patient
                </button>
            </div>
            <Modal
                open={open}
                onClose={() => {
                    setOpen(false);
                    setEditPatient(null);
                }}
                onSave={handleSearch}
                size="large"
                closeIcon
                style={{ marginTop: '7vh' }}
            >
                <Modal.Header>{editPatient ? "Edit Patient" : "Add Patient"}</Modal.Header>
                <Modal.Content>
                    <AddPatient
                        editPatient={editPatient}
                        onClose={() => {
                            setOpen(false)
                            setEditPatient(null);
                        }}
                        onSave={handleSearch}
                    />
                </Modal.Content>
            </Modal>
            <div className="mt-4 ml-12 min-w-7xl bg-white p-6 rounded-xl shadow-md">
                <p className="text-blue-900 text-2xl font-semibold mb-4">Search Patients</p>
                <form className="space-y-4 mt-4">
                    <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                First Name
                            </label>
                            <input
                                type="text"
                                name="firstName"
                                value={formData.firstName}
                                placeholder="First Name"
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 "
                            />
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Last Name
                            </label>
                            <input
                                type="text"
                                name="lastName"
                                value={formData.lastName}
                                placeholder="Last Name"
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 "
                            />
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Registration No
                            </label>
                            <input
                                type="text"
                                name="regNo"
                                value={formData.regNo}
                                placeholder="Registration No"
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 "
                            />
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Active
                            </label>
                            <select
                                name="active"
                                value={formData.active}
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:ring-blue-500 focus:border-blue-500">
                                <option value=""> Select Active</option>
                                {active.map((active) => (
                                    <option key={active.id} value={active.value}>
                                        {active.valueName}
                                    </option>
                                ))}

                            </select>
                        </div>
                    </div>
                    <div className="mt-4 py-4 space-x-4">
                        <button class="ui primary button" onClick={handleSearch}>Search</button>
                        <button type="button" class="ui button" onClick={handleRest}>Reset</button>
                    </div>
                </form>
                <div className="mt-8">
                    {loading ? (
                            <div className="flex justify-center items-center p-20">
                                <ReactLoader loading={loading}/>
                            </div>
                        )
                        : (
                            <Table>
                                <Table.Header>
                                    <Table.Row>
                                        <Table.HeaderCell>Registration No</Table.HeaderCell>
                                        <Table.HeaderCell>Name</Table.HeaderCell>
                                        <Table.HeaderCell>Gender</Table.HeaderCell>
                                        <Table.HeaderCell>Contact No</Table.HeaderCell>
                                        <Table.HeaderCell>Relation Name</Table.HeaderCell>
                                        <Table.HeaderCell>Active</Table.HeaderCell>
                                    </Table.Row>
                                </Table.Header>

                                <Table.Body>
                                    {searchPatient.length === 0 ? (
                                            <Table.Row>
                                                <Table.Cell colSpan="6" textAlign="center">
                                                    <div className="flex justify-center items-center">
                                                        <img src={search} className="w-72 h-72"/>
                                                    </div>
                                                </Table.Cell>
                                            </Table.Row>
                                        ) :
                                        (
                                            searchPatient.map((patient) => (
                                                <Table.Row key={patient.id}
                                                           onDoubleClick={() => handleRowDoubleClick(patient.id)}
                                                           style={{cursor: 'pointer'}}
                                                >
                                                    <Table.Cell>{patient.regNo}</Table.Cell>
                                                    <Table.Cell>{patient.fullName}</Table.Cell>
                                                    <Table.Cell>{patient.gender}</Table.Cell>
                                                    <Table.Cell>{patient.contactNo}</Table.Cell>
                                                    <Table.Cell>{patient.relativeName}</Table.Cell>
                                                    <Table.Cell>{patient.activeStatus}</Table.Cell>
                                                </Table.Row>
                                            ))
                                        )
                                    }
                                </Table.Body>
                            </Table>
                        )
                    }
                    {patients.length > itemsPage && (
                        <Pagination
                            activePage={activePage}
                            totalPages={totalPages}
                            onPageChange={handlePaginationChange}
                            siblingRange={1}
                            boundaryRange={1}
                            ellipsisItem={null}
                            firstItem={null}
                            lastItem={null}
                            pointing
                            secondary
                        />
                    )}
                </div>
            </div>
        </div>
    )
}
export default Patients
