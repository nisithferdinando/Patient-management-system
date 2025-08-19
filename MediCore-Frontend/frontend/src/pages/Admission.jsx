import React, {useEffect, useState} from 'react'
import {getKeyData} from "../dropdown/keyValueData.js";
import axiosInstance from "../util/axiosInstance.js";
import ReactLoader from "../components/ReactLoader.jsx";
import {Modal, Pagination, Table} from "semantic-ui-react";
import search from "../assets/search.jpg"
import AddAdmission from "../components/AddAdmission.jsx";
import {toast} from "react-toastify";
import {useDispatch} from "react-redux";
import {clearErrors} from "../slice/AdmissionFormSlice.js";


const Admission = () => {
    const [formData, setFormData] = useState({
        patientRegNo: "",
        admitStatus: "",
        admitCategory: "",
        firstName: "",
        lastName: "",
        createdAt: new Date().toISOString().slice(0, 10),
        active: "1",
    });
    const [admission, setAdmission] = useState([]);
    const [category, setCategory] = useState([]);
    const [status, setStatus] = useState([]);
    const [active, setActive] = useState([]);
    const [open, setOpen] = useState(false);
    const [editAdmission, setEditAdmission] = useState(null);
    const [loading, setLoading] = useState(false);
    const [activePage, setActivePage] = useState(1);
    const itemsPerPage = 8;
    const totalPages = Math.ceil(admission.length / itemsPerPage);
   const dispatch=useDispatch();

    const handlePaginationChange = (e, {activePage}) => {
        setActivePage(activePage);
    };

    const searchAdmission = admission.slice(
        (activePage - 1) * itemsPerPage,
        activePage * itemsPerPage
    );

    useEffect(() => {
        (async () => {
            const admitCategory = await getKeyData("admit_category");
            setCategory(admitCategory);
            const admitStatus = await getKeyData("admit");
            setStatus(admitStatus);
            const activeName = await getKeyData("active");
            setActive(activeName);
        })();
    }, []);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((data) => ({
            ...data,
            [name]: value,
        }))
    };

    const handleSearch = async (e) => {
        e.preventDefault();
        setLoading(true);
        const admissionSearch = {
            patientRegNo: formData.patientRegNo || null,
            admitStatus: formData.admitStatus === "" ? -2 : parseInt(formData.admitStatus),
            admitCategory: formData.admitCategory === "" ? -2 : parseInt(formData.admitCategory),
            firstName: formData.firstName || null,
            lastName: formData.lastName || null,
            createdAt: formData.createdAt || null,
            active: formData.active === "" ? -2 : parseInt(formData.active),
        }
        try {
            const admissionSearchResults = await axiosInstance.post("/admission/search", admissionSearch);
            await new Promise(resolve => setTimeout(resolve, 700));
            setAdmission(admissionSearchResults.data);
            setActivePage(1);
        } catch (error) {
            console.log(error);
        }
        setLoading(false);
    };


    const handleRowDoubleClick = async (admissionId) => {
        try {
            const response = await axiosInstance.get(`/admission/${admissionId}`);
            setEditAdmission(response.data);
            setOpen(true);
        } catch (error) {
            console.log(error);
            toast.error("loading failed");
        }
    };
    const handleReset = () => {
        setFormData({
            patientRegNo: "",
            admitStatus: "",
            admitCategory: "",
            firstName: "",
            lastName: "",
            createdAt: "",
            active: "1",
        });
    };

    return (
        <div>
            <div className="flex justify-end mt-4">
                <button class="ui primary button" onClick={() => setOpen(true)}>New Admission</button>
            </div>
            <Modal
                open={open}
                onClose={() => {
                    dispatch(clearErrors());
                    setOpen(false)
                    setEditAdmission(null);
                }}
                size="large"
                closeIcon
            >
                <Modal.Header>{editAdmission ? "Edit Admission" : "New Admission"}</Modal.Header>
                <Modal.Content>
                    <AddAdmission
                        editAdmission={editAdmission}
                        onClose={() => {
                            setOpen(false)
                            setEditAdmission(null);
                        }}
                        onSave={handleSearch}
                    />
                </Modal.Content>
            </Modal>
            <div className="mt-4 ml-12 min-w-7xl bg-white p-6 rounded-xl shadow-md">
                <p className="text-blue-900 text-2xl font-semibold mb-4">Search Admissions</p>
                <form className="space-y-4 mt-8">
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Patient Registration Number
                            </label>
                            <input
                                type="text"
                                name="patientRegNo"
                                placeholder="Enter Registration Number"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.patientRegNo}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                First Name
                            </label>
                            <input
                                type="text"
                                name="firstName"
                                placeholder="Enter First Name"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.firstName}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Last Name
                            </label>
                            <input
                                type="text"
                                name="lastName"
                                placeholder="Enter Last Name"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.lastName}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Admit Status
                            </label>
                            <select
                                name="admitStatus"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.admitStatus}
                                onChange={handleChange}>
                                <option value="">Select Status</option>
                                {
                                    status.map((status) => (
                                        <option key={status.id} value={status.value}>
                                            {status.valueName}
                                        </option>
                                    ))
                                }

                            </select>
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Admit Category
                            </label>
                            <select
                                name="admitCategory"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.admitCategory}
                                onChange={handleChange}>
                                <option value="">Select Category</option>
                                {
                                    category.map((category) => (
                                        <option key={category.id} value={category.value}>
                                            {category.valueName}
                                        </option>
                                    ))
                                }

                            </select>
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Created Date
                            </label>
                            <input
                                type="date"
                                name="createdAt"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.createdAt}
                                onChange={handleChange}>

                            </input>
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Active
                            </label>
                            <select
                                name="active"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.active}
                                onChange={handleChange}>
                                <option value="">Select Active</option>
                                {
                                    active.map((active) => (
                                        <option key={active.id} value={active.value}>
                                            {active.valueName}
                                        </option>
                                    ))
                                }

                            </select>
                        </div>
                    </div>
                    <div className="mt-4 py-4 space-x-4">
                        <button className="ui primary button" onClick={handleSearch}>Search</button>
                        <button type="button" className="ui button" onClick={handleReset}>Reset</button>

                    </div>
                </form>
                <div className="mt-8">
                    {loading ? (
                            <div className="flex justify-center items-center p-20">
                                <ReactLoader loading={loading}/>
                            </div>
                        ) :
                        (<Table>
                            <Table.Header>
                                <Table.Row>
                                    <Table.HeaderCell>Registration No</Table.HeaderCell>
                                    <Table.HeaderCell>Name</Table.HeaderCell>
                                    <Table.HeaderCell>Admission Category</Table.HeaderCell>
                                    <Table.HeaderCell>Admit Status</Table.HeaderCell>
                                    <Table.HeaderCell>Created Date</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {searchAdmission.length === 0 ?
                                    (
                                        <Table.Row>
                                            <Table.Cell colSpan="5" textAlign="center">
                                                <div className="flex justify-center items-center">
                                                    <img src={search} className="w-72 h-72"/>
                                                </div>
                                            </Table.Cell>
                                        </Table.Row>
                                    )
                                    : (
                                        searchAdmission.map((admission) => (
                                            <Table.Row key={admission.admissionId}
                                                       style={{cursor: 'pointer'}}
                                                       onDoubleClick={() => handleRowDoubleClick(admission.admissionId)}
                                            >
                                                <Table.Cell>{admission.regNo}</Table.Cell>
                                                <Table.Cell>{admission.fullName}</Table.Cell>
                                                <Table.Cell>{admission.admitCategoryName}</Table.Cell>
                                                <Table.Cell>{admission.admitStatusName}</Table.Cell>
                                                <Table.Cell>{admission.createdAt?.slice(0, 10)}</Table.Cell>
                                            </Table.Row>
                                        ))
                                    )
                                }
                            </Table.Body>
                        </Table>)
                    }
                    {
                        admission.length > itemsPerPage && (
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
                        )
                    }
                </div>
            </div>
        </div>
    )
}
export default Admission;
