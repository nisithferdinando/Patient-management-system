import React, {useEffect, useState} from "react";
import {Modal, Pagination, Table} from "semantic-ui-react";
import axiosInstance from "../util/axiosInstance.js";
import {doctorCategory, doctorWards} from "../dropdown/dropDownData.js";
import {getKeyData} from "../dropdown/keyValueData.js"
import AddDoctor from "../components/AddDoctor.jsx";
import {useDispatch} from "react-redux";
import {clearErrors} from "../slice/doctorFormSlice.js";
import ReactLoader from "../components/ReactLoader.jsx";
import {FileQuestion, SearchX, SearchXIcon} from "lucide-react";
import search from "../assets/search.jpg"
import NameSelectdropdown from "../dropdown/NameSelectdropdown.jsx";

const Doctors = () => {

    const dispatch = useDispatch();
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        ward: "",
        category: "",
        active: "",
    });
    const [doctors, setDoctors] = useState([]);
    const [categoryOptions, setCategoryOptions] = useState([]);
    const [wards, setwards] = useState([]);
    const [active, setActive] = useState([]);
    const [open, setOpen] = useState(false);
    const [editDoctor, setEditDoctor] = useState(null);
    const [activePage, setActivePage] = useState(1);
    const [loading, setLoading] = useState(false);
    const itemsPerPage = 8;
    const totalPages = Math.ceil(doctors.length / itemsPerPage);

    const handlePaginationChange = (e, {activePage}) => {
        setActivePage(activePage);
    };

    const searchDoctor = doctors.slice(
        (activePage - 1) * itemsPerPage,
        activePage * itemsPerPage
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
            const categories = await doctorCategory();
            setCategoryOptions(categories);
            const wards = await doctorWards();
            setwards(wards);
            const active = await getKeyData("active");
            setActive(active);

        })();
    }, []);

    const handleSearch = async (e) => {
        e.preventDefault();
        setLoading(true);
        const formDataClean = {
            firstName: formData.firstName || null,
            lastName: formData.lastName || null,
            wardId: formData.ward === "" ? -2 : parseInt(formData.ward),
            categoryId: formData.category === "" ? -2 : parseInt(formData.category),
            active: formData.active === "" ? -2 : parseInt(formData.active),
        }
        try {
            const response = await axiosInstance.post("/doctor/search", formDataClean);
            await new Promise(resolve => setTimeout(resolve, 700));
            setDoctors(response.data);
            setActivePage(1);
        } catch (error) {
            console.error('Error:', error);
        }
        setLoading(false);
    }

    const handleRowDoubleClick = async (docId) => {
        try {
            const response = await axiosInstance.get(`/doctor/${docId}`);
            setEditDoctor(response.data);
            dispatch(clearErrors());
            setOpen(true);
        } catch (error) {
            console.error('Error:', error);
            alert("Failed to fetch doctors");
        }
    };

    const handleReset = () => {
        setFormData({
            firstName: "",
            lastName: "",
            ward: "",
            category: "",
            active: "",
        });
    }

    return (
        <div>
            <div className="flex justify-end mt-4">
                <button class="ui primary button" onClick={() => setOpen(true)}>Add Doctor</button>
            </div>
            <Modal
                open={open}
                onClose={() => {
                    dispatch(clearErrors());
                    setOpen(false);
                    setEditDoctor(null);
                }}
                size="large"
                closeIcon>
                <Modal.Header>{editDoctor ? "Edit Doctor" : "New Doctor"}</Modal.Header>
                <Modal.Content>
                    <AddDoctor
                        editDoctor={editDoctor}
                        onClose={() => {
                            setOpen(false)
                            setEditDoctor(null)
                        }}
                        onSave={handleSearch}
                    />
                </Modal.Content>
            </Modal>
            <div className="mt-4 ml-12 min-w-7xl bg-white p-6 rounded-xl shadow-md">
                <p className="text-blue-900 text-2xl font-semibold mb-4">Search Doctors</p>

                <form className="space-y-4 mt-4">
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                First Name
                            </label>
                            <input
                                type="text"
                                name="firstName"
                                placeholder="Enter first name"
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
                                placeholder="Enter last name"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Ward
                            </label>
                            <select
                                name="ward"
                                value={formData.ward}
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                                <option value=""> Select ward</option>
                                {wards.map((ward) => (
                                    <option key={ward.wardId} value={ward.wardId}>
                                        {ward.wardName}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Category
                            </label>
                            <select
                                name="category"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.category}
                                onChange={handleChange}>
                                <option value="">Select Category</option>
                                {categoryOptions.map((category) => (
                                    <option key={category.categoryId} value={category.categoryId}>
                                        {category.categoryName}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Active
                            </label>
                            <select
                                name="active"
                                value={formData.active}
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500">
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
                        <button type="button" className="ui button" onClick={handleReset}>Reset</button>

                    </div>
                </form>
                <div className="mt-8">
                    {loading ? (
                            <div className="flex justify-center items-center p-20">
                                <ReactLoader loading={loading}/>
                            </div>) :
                        (<Table>
                            <Table.Header>
                                <Table.Row
                                >
                                    <Table.HeaderCell>Name</Table.HeaderCell>
                                    <Table.HeaderCell>Category</Table.HeaderCell>
                                    <Table.HeaderCell> Doctor Status</Table.HeaderCell>
                                    <Table.HeaderCell>Ward</Table.HeaderCell>
                                    <Table.HeaderCell>Available</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {searchDoctor.length === 0 ?
                                    (
                                        <Table.Row>
                                            <Table.Cell colSpan="5" textAlign="center">
                                                <div className="flex justify-center items-center ">
                                                    <img src={search} className="w-72 h-72"/>
                                                </div>
                                            </Table.Cell>
                                        </Table.Row>
                                    ) :
                                    (
                                        searchDoctor.map((doctor) => (
                                            <Table.Row key={doctor.id}
                                                       onDoubleClick={() => handleRowDoubleClick(doctor.id)}
                                                       style={{cursor: "pointer"}}
                                            >
                                                <Table.Cell>{doctor.fullName}</Table.Cell>
                                                <Table.Cell>{doctor.categoryName}</Table.Cell>
                                                <Table.Cell>{doctor.statusName}</Table.Cell>
                                                <Table.Cell>{doctor.wardName}</Table.Cell>
                                                <Table.Cell>
                                                <span
                                                    className={`text-white px-2 py-1 rounded-md text-sm font-medium ${doctor.active === 1 ? "bg-blue-500" : "bg-slate-500"}`}>
                                                {doctor.active === 1 ? "Available" : "Not Available"}
                                        </span>
                                                </Table.Cell>

                                            </Table.Row>
                                        ))
                                    )
                                }
                            </Table.Body>
                        </Table>)
                    }
                    {doctors.length > itemsPerPage && (
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
    );
};

export default Doctors;
