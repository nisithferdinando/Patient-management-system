import React, {useEffect, useState} from 'react'
import {getKeyData} from "../dropdown/keyValueData.js";
import {doctorCategory, doctorWards} from "../dropdown/dropDownData.js";
import {useDispatch, useSelector} from "react-redux";
import {clearErrors, validateDoctorForm} from "../slice/doctorFormSlice.js";
import axiosInstance from "../util/axiosInstance.js";
import {store} from "../store.js";
import {toast} from "react-toastify";
import ReactLoader from "./ReactLoader.jsx";

const AddDoctor = ({editDoctor, onClose, onSave}) => {

    const dispatch = useDispatch();
    const errors = useSelector((state) => state.doctorForm.errors);

    const [doctor, setDoctor] = useState({
        firstName: "",
        lastName: "",
        docId: "",
        email: "",
        category: "",
        ward: "",
        age: "",
        state: "",
        gender: "",
        active: "",
        status: "",
    });

    const [state, setState] = useState([]);
    const [category, setCategory] = useState([]);
    const [ward, setWard] = useState([]);
    const [active, setActive] = useState([]);
    const [gender, setGender] = useState([]);
    const [status, setStatus] = useState([]);
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        const {name, value} = e.target;
        let val = value;
        if (["category", "ward", "state", "gender", "status", "active", "age"].includes(name)) {
            val = value === "" ? "" : Number(value);
        }
        setDoctor({
            ...doctor,
            [name]: val
        });
        dispatch(clearErrors(name));
    }
    useEffect(() => {
        if (editDoctor) {
            setDoctor({
                firstName: editDoctor.firstName || "",
                lastName: editDoctor.lastName || "",
                docId: editDoctor.docId || "",
                email: editDoctor.email || "",
                category: editDoctor.category,
                ward: editDoctor.ward,
                age: editDoctor.age,
                state: editDoctor.state,
                gender: editDoctor.gender,
                status: editDoctor.status,
                active: editDoctor.active,
            });
        }
    }, [editDoctor]);

    useEffect(() => {
        (async () => {
            const state = await getKeyData("doc_state")
            setState(state);
            const active = await getKeyData("active");
            setActive(active);
            const status = await getKeyData("doc_status");
            setStatus(status);
            const gender = await getKeyData("gender");
            setGender(gender);
            const category = await doctorCategory();
            setCategory(category);
            const ward = await doctorWards();
            setWard(ward);
        })();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        dispatch(validateDoctorForm(doctor));
        setTimeout(async () => {
            const currentErros = store.getState().doctorForm.errors;
            const hasErrors = Object.keys(currentErros).length > 0;
            if (hasErrors) {
                console.log(currentErros);
                return;
            }
            setLoading(true);
            try {
                if (editDoctor) {
                    await axiosInstance.put(`/doctor/update/${editDoctor.id}`, doctor);
                    await new Promise(resolve => setTimeout(resolve, 500));
                    toast.success("Doctor updated successfully.");
                } else {
                    const response = await axiosInstance.post("/doctor/add", doctor);
                    await new Promise(resolve => setTimeout(resolve, 400));
                    console.log(response.data);
                    handleResetDoctor();
                    toast.success("Doctor is Added successfully.");
                }
                onSave?.(e);
                onClose?.();

            } catch (error) {
                console.log(error)
                toast.error("Please try again later");
            } finally {
                setLoading(false);
            }

        }, 100);

    };

    const handleResetDoctor = () => {
        setDoctor({
            firstName: "",
            lastName: "",
            docId: "",
            email: "",
            category: "",
            ward: "",
            state: "",
            gender: "",
            active: "",
            status: "",
            age: "",
        })
    };

    return (
        <div>
            {loading ? (
                <div className="flex justify-center items-center p-40">
                    <ReactLoader loading={loading}/>
                </div>) : (
                <div className="mt-4">
                    <form className="space-y-8 mt-4">
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-2 space-x-4">
                            <div>
                                <label className="block text-base font-semibold text-gray-700 mb-2">
                                    State *
                                </label>
                                <select
                                    name="state"
                                    value={doctor.state}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500">
                                    <option value=""> Select State</option>
                                    {state.map((state) => (
                                        <option key={state.id} value={state.value}>
                                            {state.valueName}
                                        </option>
                                    ))}
                                </select>
                                {errors.state && <p className="text-red-500 text-base mt-2 pl-1">{errors.state}</p>}
                            </div>
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    First Name*
                                </lable>
                                <input
                                    type="text"
                                    name="firstName"
                                    placeholder="First Name"
                                    value={doctor.firstName}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                />
                                {errors.firstName &&
                                    <p className="text-red-500 text-base mt-2 pl-1">{errors.firstName}</p>}

                            </div>
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Last Name*
                                </lable>
                                <input
                                    type="text"
                                    name="lastName"
                                    placeholder="Last Name"
                                    value={doctor.lastName}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                />
                                {errors.lastName &&
                                    <p className="text-red-500 text-base mt-2 pl-1">{errors.lastName}</p>}
                            </div>

                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-4 gap-2 space-x-4">
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Doctor ID*
                                </lable>
                                <input
                                    type="text"
                                    name="docId"
                                    placeholder="DOC0001"
                                    value={doctor.docId}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                />
                                {errors.docId && <p className="text-red-500 text-base mt-2 pl-1">{errors.docId}</p>}

                            </div>
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Doctor Category*
                                </lable>
                                <select
                                    name="category"
                                    value={doctor.category}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                >
                                    <option value="">Select Category</option>
                                    {category.map((category) => (
                                        <option key={category.categoryId} value={category.categoryId}>
                                            {category.categoryName}
                                        </option>
                                    ))}
                                </select>
                                {errors.category &&
                                    <p className="text-red-500 text-base mt-2 pl-1">{errors.category}</p>}

                            </div>
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Ward*
                                </lable>
                                <select
                                    name="ward"
                                    value={doctor.ward}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                >
                                    <option value="">Select Ward</option>
                                    {ward.map((ward) => (
                                        <option key={ward.wardId} value={ward.wardId}>
                                            {ward.wardName}
                                        </option>
                                    ))}
                                </select>
                                {errors.ward && <p className="text-red-500 text-base mt-2 pl-1">{errors.ward}</p>}

                            </div>
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Doctor Status*
                                </lable>
                                <select
                                    name="status"
                                    value={doctor.status}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                >
                                    <option value="">Select Status</option>
                                    {status.map((status) => (
                                        <option key={status.id} value={status.value}>
                                            {status.valueName}
                                        </option>
                                    ))}
                                </select>
                                {errors.status && <p className="text-red-500 text-base mt-2 pl-1">{errors.status}</p>}

                            </div>
                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-4 gap-2 space-x-4">
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Gender*
                                </lable>
                                <select
                                    name="gender"
                                    value={doctor.gender}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                >
                                    <option value="">Select Gender</option>
                                    {
                                        gender.map((gender) => (
                                            <option key={gender.id} value={gender.value}>
                                                {gender.valueName}
                                            </option>
                                        ))
                                    }
                                </select>
                                {errors.gender && <p className="text-red-500 text-base mt-2 pl-1">{errors.gender}</p>}

                            </div>
                            <div>
                                <label className="block text-base font-semibold text-gray-700 mb-2">
                                    Age*
                                </label>
                                <input
                                    type="number"
                                    name="age"
                                    value={doctor.age}
                                    onChange={handleChange}
                                    placeholder="Age"
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                />
                                {errors.age && <p className="text-red-500 text-base mt-2 pl-1">{errors.age}</p>}

                            </div>
                            <div>
                                <label className="block text-base font-semibold text-gray-700 mb-2">
                                    Email
                                </label>
                                <input
                                    type="text"
                                    name="email"
                                    value={doctor.email}
                                    onChange={handleChange}
                                    placeholder="Email"
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                />
                                {errors.email && <p className="text-red-500 text-base mt-2 pl-1">{errors.email}</p>}

                            </div>
                            <div>
                                <lable className="block text-base font-semibold text-gray-700 mb-2">
                                    Availability
                                </lable>
                                <select
                                    name="active"
                                    value={doctor.active}
                                    onChange={handleChange}
                                    className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                                >
                                    <option value="">Select Availability</option>
                                    {active.map((active) => (
                                        <option key={active.id} value={active.value}>
                                            {active.valueName}
                                        </option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <div className="flex pt-4 space-x-4">
                            <button class="ui primary button"
                                    onClick={handleSubmit}
                            >
                                {editDoctor ? "Update Doctor" : "Add Doctor"}
                            </button>
                            <button className="ui button"
                                    type="button"
                                    onClick={handleResetDoctor}
                            >
                                Reset
                            </button>
                        </div>

                    </form>
                </div>)}
        </div>
    )
}
export default AddDoctor
