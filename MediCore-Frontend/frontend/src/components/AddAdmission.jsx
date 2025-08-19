import React, {useEffect, useState} from 'react'
import {getKeyData} from "../dropdown/keyValueData.js";
import ReactLoader from "./ReactLoader.jsx";
import {doctorByCategory, patientRegNoDropdown} from "../dropdown/dropDownData.js";
import AsyncSelect from "react-select/async";
import {doctorCategory} from "../dropdown/dropDownData.js";
import axiosInstance from "../util/axiosInstance.js";
import {toast} from "react-toastify";
import {useDispatch, useSelector} from "react-redux";
import {clearErrors, validateAdmissionForm} from "../slice/AdmissionFormSlice.js";
import {store} from "../store.js";

const AddAdmission = ({editAdmission, onClose, onSave}) => {

    const [admission, setAdmission] = useState({
        patientId: "",
        patientRegNo: "",
        firstName: "",
        lastName: "",
        state: "",
        admitStatus: "",
        doctor: "",
        doctorId: "",
        admitCategory: "",
        disease: "",
        doctorCategory: "",
        active: "1",
    });

    const [state, setState] = useState([]);
    const [admitStatus, setAdmitStatus] = useState([]);
    const [admitCategory, setAdmitCategory] = useState([]);
    const [active, setActive] = useState([]);
    const [loading, setLoading] = useState(false);
    const [category, setcategory] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const dispatch = useDispatch();
    const errors = useSelector((state) => state.admissionForm.errors);
    const handleChange = async (e) => {
        const {name, value} = e.target;
        let val = value;
        if (["state", "patientId", "admitStatus", "admitCategory", "doctor", "doctorCategory", "active"].includes(name)) {
            val = value === "" ? "" : Number(value);
        }
        if (name === "doctor") {
            const selectedDoctor = doctors.find((doc) => doc.id === val);
            setAdmission((prev) => ({
                ...prev, doctor: val, doctorId: selectedDoctor ? selectedDoctor.docId : "",
            }));
            dispatch(clearErrors("doctor"));
            return;
        }

        if (name === "doctorCategory") {
            setAdmission((prev) => ({
                ...prev, [name]: val, doctor: "", doctorId: null,
            }));

            if (val !== "") {
                const doctor = await doctorByCategory(val);
                setDoctors(doctor);
            } else {
                setDoctors([]);
            }
            return;
        }
        setAdmission({
            ...admission, [name]: val
        });
        dispatch(clearErrors(name));
    };

    useEffect(() => {
        if (editAdmission) {
            setAdmission({
                patientRegNo: editAdmission.patientRegNo,
                patientId: editAdmission.patientId,
                firstName: editAdmission.firstName || "",
                lastName: editAdmission.lastName || "",
                state: editAdmission.state,
                admitStatus: editAdmission.admitStatus,
                admitCategory: editAdmission.admitCategory,
                doctor: editAdmission.doctor,
                doctorCategory: editAdmission.doctorCategory,
                active: editAdmission.active,
                doctorId: editAdmission.doctorId,
                disease: editAdmission.disease || "",
            });
            if (editAdmission.doctorCategory) {
                doctorByCategory(editAdmission.doctorCategory).then(setDoctors);
            }
        }
    }, [editAdmission]);

    useEffect(() => {
        (async () => {

            const state = await getKeyData("state");
            setState(state);
            const admitStatus = await getKeyData("admit");
            setAdmitStatus(admitStatus);
            const admitCategory = await getKeyData("admit_category");
            setAdmitCategory(admitCategory);
            const active = await getKeyData("active");
            setActive(active);
            const category = await doctorCategory();
            setcategory(category);
        })();
    }, []);

    const loadPatientOptions = async (regNo) => {
        return await patientRegNoDropdown(regNo);
    };

    const handlePatientSelect = (selected) => {
        if (selected) {
            setAdmission({
                ...admission,
                patientId: selected.patientId,
                patientRegNo: selected.patientRegNo,
                firstName: selected.firstName,
                lastName: selected.lastName,
                state: selected.state,
            });
            dispatch(clearErrors("patientRegNo"));
            dispatch(clearErrors("state"));
            dispatch(clearErrors("firstName"));
            dispatch(clearErrors("lastName"));
        } else {
            setAdmission({
                ...admission, patientId: "", patientRegNo: "", firstName: "", lastName: "", state: "",
            });
        }
        console.log(selected);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        dispatch(validateAdmissionForm(admission));
        const currentErrors = store.getState().admissionForm.errors;
        const error = Object.keys(currentErrors).length > 0;
        if (error) {
            return;
        }
        setLoading(true);
        try {
            if (editAdmission) {
                const editAdmissionData = {
                    ...admission,
                    doctorId: admission.doctor ? admission.doctorId : null,
                }
                if (!editAdmissionData.doctor) {
                    editAdmissionData.doctorId = "";
                }
                const editdoctorResponse = await axiosInstance.put(`/admission/update/${editAdmission.admissionId}`, editAdmissionData);
                await new Promise(resolve => setTimeout(resolve, 700));
                console.log("editDoctor", editdoctorResponse);
                toast.success("Admission updated successfully");
                handleReset();
            } else {
                console.log("Submitting admission:", admission);
                const response = await axiosInstance.post("/admission/add", admission);
                await new Promise(resolve => setTimeout(resolve, 700));
                console.log(response.data);
                toast.success("Admission is added successfully");
                handleReset();
            }
            onSave?.(e);
            onClose?.();

        } catch (error) {
            console.log(error);
            toast.error("please try again");
        } finally {
            setLoading(false);
        }
    };

    const handleReset = () => {
        setAdmission({
            firstName: "",
            lastName: "",
            state: "",
            patientRegNo: "",
            admitCategory: "",
            admitStatus: "",
            doctorCategory: "",
            doctor: "",
            disease: "",
            active: "1",
        });
        setDoctors([]);
    };

    return (<div>
        {loading ? (<div className="flex justify-center items-center p-40">
            <ReactLoader loading={loading}/>
        </div>) : (<div className="mt-4">
            <form className="space-y-8 mt-4">
                <div className="grid grid-cols-1 md:grid-cols-4 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Patient Reg No *
                        </label>
                        <AsyncSelect
                            cacheOptions
                            defaultOptions
                            loadOptions={loadPatientOptions}
                            onChange={handlePatientSelect}
                            placeholder="Search Reg No"
                            isClearable
                            value={admission.patientRegNo ? {
                                label: admission.patientRegNo,
                                value: admission.patientRegNo,
                                patientId: admission.patientId,
                            } : null}
                        />
                        {errors.patientRegNo &&
                            <p className="text-red-500 text-base mt-2 pl-1">{errors.patientRegNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            State *
                        </label>
                        <select
                            name="state"
                            value={admission.state}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500">
                            <option value=""> Select State</option>
                            {state.map((state) => (<option key={state.id} value={state.value}>
                                {state.valueName}
                            </option>))}
                        </select>
                        {errors.state && <p className="text-red-500 text-base mt-2 pl-1">{errors.state}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            First Name *
                        </label>
                        <input
                            type="text"
                            name="firstName"
                            placeholder="First Name"
                            value={admission.firstName}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.firstName && <p className="text-red-500 text-base mt-2 pl-1">{errors.firstName}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Last Name *
                        </label>
                        <input
                            type="text"
                            name="lastName"
                            placeholder="Last Name"
                            value={admission.lastName}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.lastName && <p className="text-red-500 text-base mt-2 pl-1">{errors.lastName}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Admit Category*
                        </label>
                        <select
                            name="admitCategory"
                            value={admission.admitCategory}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Category</option>
                            {admitCategory.map((category) => (<option key={category.id} value={category.value}>
                                {category.valueName}
                            </option>))}
                        </select>
                        {errors.admitCategory &&
                            <p className="text-red-500 text-base mt-2 pl-1">{errors.admitCategory}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Doctor Category*
                        </label>
                        <select
                            name="doctorCategory"
                            value={admission.doctorCategory}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Category</option>
                            {category.map((category) => (<option key={category.categoryId} value={category.categoryId}>
                                {category.categoryName}
                            </option>))}
                        </select>
                        {errors.doctorCategory &&
                            <p className="text-red-500 text-base mt-2 pl-1">{errors.doctorCategory}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Doctor *
                        </label>
                        <select
                            name="doctor"
                            value={admission.doctor}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Doctor</option>
                            {doctors.map((doctor) => (<option key={doctor.id} value={doctor.id}>
                                {doctor.firstName} {doctor.lastName}
                            </option>))}
                        </select>
                        {errors.doctor && <p className="text-red-500 text-base mt-2 pl-1">{errors.doctor}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Admit Status*
                        </label>
                        <select
                            name="admitStatus"
                            value={admission.admitStatus}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Status</option>
                            {admitStatus.map((admitStatus) => (<option key={admitStatus.id} value={admitStatus.value}>
                                {admitStatus.valueName}
                            </option>))}
                        </select>
                        {errors.admitStatus && <p className="text-red-500 text-base mt-2 pl-1">{errors.admitStatus}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Disease *
                        </label>
                        <input
                            name="disease"
                            value={admission.disease}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                        </input>
                        {errors.disease && <p className="text-red-500 text-base mt-2 pl-1">{errors.disease}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Active*
                        </label>

                        <select
                            name="active"
                            value={admission.active}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Active</option>
                            {active.map((active) => (<option key={active.id} value={active.value}>
                                {active.valueName}
                            </option>))}
                        </select>
                        {errors.active && <p className="text-red-500 text-base mt-2 pl-1">{errors.active}</p>}
                    </div>
                </div>
                <div className="flex pt-4 space-x-4">
                    <button
                        type="button"
                        class="ui primary button"
                        onClick={handleSubmit}>
                        {editAdmission ? "Update Admission" : "Add Admission"}
                    </button>
                    <button
                        type="button"
                        className="ui button" onClick={handleReset}>
                        Reset
                    </button>
                </div>
            </form>

        </div>)}

    </div>)
}
export default AddAdmission
