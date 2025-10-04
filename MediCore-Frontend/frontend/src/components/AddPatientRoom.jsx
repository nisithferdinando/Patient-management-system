import React, {useEffect, useState} from 'react'
import {getKeyData} from "../dropdown/keyValueData.js";
import {admissionRegNo, doctorWards, roomData} from "../dropdown/dropDownData.js";
import AsyncSelect from "react-select/async";
import {useDispatch, useSelector} from "react-redux";
import {clearErrors, validatePatientRoomForm} from "../slice/patientRoomFormSlice.js";
import {store} from "../store.js";
import {Table, TableHeader, TableHeaderCell} from "semantic-ui-react";
import axiosInstance from "../util/axiosInstance.js";
import {toast} from "react-toastify";

const AddPatientRoom = ({editPaientRoom, onClose, onSave}) => {

    const dispatch = useDispatch();
    const errors = useSelector((state) => state.patientRoomForm.errors);

    const [patientRoom, setPatientRoom] = useState({
        patientRegNo: "",
        admissionId: "",
        patientId: "",
        ward: "",
        roomCategory: "",
        room: "",
        roomType: "",
        roomNo: "",
        bedNo: "",
        roomStatus: "2",
        startDate: "",
        endDate: "",
        active: "1",
    });
    const [ward, setward] = useState([]);
    const [roomCategory, setRoomCategory] = useState([]);
    const [roomType, setRoomType] = useState([]);
    const [roomNo, setRoomNo] = useState([]);
    const [active, setActive] = useState([]);
    const [roomStatus, setRoomStatus] = useState([]);
    const [roomNoOptions, setRoomNoOptions] = useState([]);
    const [bedRoomOptions, setBedRoomOptions] = useState([]);
    const [patientRoomList, setPatientRoomList] = useState([]);
    const [editingIndex, setEditingIndex] = useState(null);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        const {name, value} = e.target;
        let val = value;
        if (["patientId", "admissionId", "roomCategory", "roomType", "ward", "room", "roomNo", "bedNo", "roomStatus", "active"].includes(name)) {
            val = value === "" ? "" : Number(value);
        }
        if (name === "roomCategory") {
            const categoryValue = Number(value);
            if (categoryValue === 2) {
                setPatientRoom((prev) => ({
                    ...prev, roomCategory: categoryValue, roomType: 8
                }));

            } else {
                setPatientRoom((prev) => ({
                    ...prev, roomCategory: categoryValue, roomType: "",
                }));
            }
            dispatch(clearErrors("roomCategory"));
            return;
        }
        setPatientRoom({
            ...patientRoom, [name]: val,
        })
        dispatch(clearErrors(name));
    };
    useEffect(() => {
        if (editPaientRoom && Array.isArray(editPaientRoom)) {
            const roomList = editPaientRoom.map((room) => ({
                ...room,
                startDate: room.startDate ? room.startDate.split("T")[0] : null,
                endDate: room.endDate ? room.endDate.split("T")[0] : null,
            }))
            setPatientRoomList(roomList);
            resetForm();
            setEditingIndex(null);

        }
        resetForm();
        setEditingIndex(null);
    }, [editPaientRoom]);
    const filteredRoomOptions = patientRoom.roomCategory === 2 ?
        roomType.filter((item) => item.value === 8) :
        roomType.filter((item) => item.value !== 8)

    useEffect(() => {
        (async () => {
            const active = await getKeyData("active");
            setActive(active);
            const ward = await doctorWards();
            setward(ward);
            const roomCategory = await getKeyData("room_category");
            setRoomCategory(roomCategory);
            const roomType = await getKeyData("room_type");
            setRoomType(roomType);
            const roomStatus = await getKeyData("room_status");
            setRoomStatus(roomStatus);

        })()
    }, [])

    const admissionRegNoOptions = async (regNo) => {
        return await admissionRegNo(regNo);
    }

    const handlePatientSelected = (selected) => {
        if (selected) {
            setPatientRoom({
                ...patientRoom,
                admissionId: selected.admissionId,
                patientId: selected.patientId,
                patientRegNo: selected.patientRegNo,
                state: selected.state,
                stateValue: selected.stateValue,
                firstName: selected.firstName,
                lastName: selected.lastName,
            });
            dispatch(clearErrors("patientRegNo"));
        } else {
            setPatientRoom({
                ...patientRoom,
                admissionId: "",
                patientId: "",
                patientRegNo: "",
                room: "",
                state: "",
                stateValue: "",
                firstName: "",
                lastName: "",

            });
        }
    };
    console.log("room", patientRoom);

    const handleAdd = () => {
        dispatch(validatePatientRoomForm(patientRoom));
        setIsSubmitting(true);
    };
    useEffect(() => {
        if (!isSubmitting) return;
        if (errors && Object.keys(errors).length === 0) {
            setPatientRoomList((prev) => {
                if (editingIndex !== null) {
                    const updated = [...prev];
                    updated[editingIndex] = patientRoom;
                    return updated;
                }
                return [...prev, patientRoom];
            });
            resetForm();
            setEditingIndex(null);
        }
        setIsSubmitting(false);
    }, [isSubmitting]);

    const handleEdit = async (index) => {
        const selectedRoom = patientRoomList[index];
        setPatientRoom(selectedRoom);
        setEditingIndex(index);
        if (selectedRoom.roomCategory && selectedRoom.roomType && selectedRoom.ward) {
            const rooms = await roomData(
                selectedRoom.roomCategory,
                selectedRoom.roomType,
                selectedRoom.ward
            );
            setRoomNoOptions(rooms);

            const beds = rooms
                .filter((item) => item.roomNo === Number(selectedRoom.roomNo))
                .map((item) => ({bedNo: item.bedNo}));
            setBedRoomOptions(beds);
        }
    };

    const handleDelete = (index) => {
        setPatientRoomList(patientRoomList.filter((_, i) => i !== index));
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        const currentErrors = store.getState().patientRoomForm.errors;
        const hasErrors = Object.keys(currentErrors).length > 0
        if (hasErrors) {
            return;
        }
        setLoading(true);
        try {
            if (editPaientRoom) {
                const response = await axiosInstance.put("/room/update", patientRoomList);
                await new Promise(resolve => setTimeout(resolve, 500));
                toast.success("Room updated successfully.");
            } else {
                const response = await axiosInstance.post("/room/add", patientRoomList);
                await new Promise(resolve => setTimeout(resolve, 400));
                resetForm();
                toast.success("Room added successfully.");
                setPatientRoomList([]);
            }
            onSave?.(e);
            onClose?.();
        } catch (error) {
            toast.error("Please try again later.");
            console.error(error);
        } finally {
            setLoading(false);
        }
    }
    const resetForm = () => {
        setPatientRoom({
            patientRegNo: "",
            admissionId: "",
            patientId: "",
            ward: "",
            roomCategory: "",
            roomType: "",
            roomNo: "",
            bedNo: "",
            roomStatus: "2",
            startDate: "",
            endDate: "",
            active: "1",
        });
        dispatch(clearErrors());
    };

    useEffect(() => {
        const fetchRoom = async () => {
            if (!patientRoom.roomCategory || !patientRoom.roomType || !patientRoom.ward) return;

            const rooms = await roomData(patientRoom.roomCategory, patientRoom.roomType, patientRoom.ward);
            setRoomNoOptions(rooms);

            if (editingIndex === null) {
                if (patientRoom.roomNo !== "" || patientRoom.bedNo !== "") {
                    setPatientRoom((prev) => ({
                        ...prev,
                        roomNo: "",
                        bedNo: ""
                    }));
                    setBedRoomOptions([]);
                }
            } else {
                const beds = rooms
                    .filter((item) => item.roomNo === Number(patientRoom.roomNo))
                    .map((item) => ({bedNo: item.bedNo}));
                setBedRoomOptions(beds);
            }
        };
        fetchRoom();
    }, [patientRoom.roomCategory, patientRoom.roomType, patientRoom.ward]);


    const handleRoomNoChange = (e) => {
        handleChange(e);
        const selectedRoomNo = Number(e.target.value);
        const selectedRoom = roomNoOptions.find((item) => item.roomNo === selectedRoomNo);
        const beds = selectedRoom ? [{bedNo: selectedRoom.bedNo}] : [];
        setBedRoomOptions(beds);
        setPatientRoom((prev) => ({
            ...prev, bedNo: "",
            room: selectedRoom ? selectedRoom.roomId : "",
        }));
    }
    const getLabel = (list, value, idField = "value", labelField = "valueName") => {
        if (!value) return "";
        const item = list.find((i) => i[idField] === Number(value) || i[idField] === value);
        return item ? item[labelField] : value;
    };
    return (<div>
        <div className="mt-4">
            <form className="space-y-8 mt-4">
                <div className="grid grid-cols-1 md:grid-cols-3 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Patient Registration No*
                        </label>
                        <AsyncSelect
                            cacheOptions
                            defaultOptions
                            loadOptions={admissionRegNoOptions}
                            onChange={handlePatientSelected}
                            placeholder="Search Patient Registration No"
                            isClearable
                            value={patientRoom.patientRegNo ? {
                                label: `${patientRoom.patientRegNo}`, value: patientRoom.admissionId,
                            } : null}
                        />
                        {errors.patientRegNo &&
                            <p className="text-red-500 text-base mt-2 pl-1"> {errors.patientRegNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Room Category*
                        </label>
                        <select
                            name="roomCategory"
                            value={patientRoom.roomCategory}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Category</option>
                            {roomCategory.map((roomCategory) => (
                                <option key={roomCategory.id} value={roomCategory.value}>
                                    {roomCategory.valueName}
                                </option>))}
                        </select>
                        {errors.roomCategory &&
                            <p className="text-red-500 text-base mt-2 pl-1">{errors.roomCategory}</p>}
                    </div>
                    <div>
                        <lable className="block text-base font-semibold text-gray-700 mb-2">
                            Ward*
                        </lable>
                        <select
                            name="ward"
                            value={patientRoom.ward}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Ward</option>
                            {ward.map((ward) => (<option key={ward.wardId} value={ward.wardId}>
                                {ward.wardName}
                            </option>))}
                        </select>
                        {errors.ward && <p className="text-red-500 text-base mt-2 pl-1"> {errors.ward}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Room Type*
                        </label>
                        <select
                            name="roomType"
                            value={patientRoom.roomType}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                            disabled={patientRoom.roomCategory === 2}
                        >
                            <option value="">Select Room Type</option>
                            {filteredRoomOptions.map((roomType) => (
                                <option key={roomType.id} value={roomType.value}>
                                    {roomType.valueName}
                                </option>))}
                        </select>
                        {errors.roomType && <p className="text-red-500 text-base mt-2 pl-1"> {errors.roomType}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Room No*
                        </label>
                        <select
                            name="roomNo"
                            value={patientRoom.roomNo}
                            onChange={handleRoomNoChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value=""> Select Room No</option>
                            {roomNoOptions.map((roomOption) => (
                                <option key={roomOption.roomId} value={roomOption.roomNo}>
                                    {roomOption.roomNo}
                                </option>
                            ))}
                        </select>
                        {errors.roomNo && <p className="text-red-500 text-base mt-2 pl-1"> {errors.roomNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Bed No*
                        </label>
                        <select
                            name="bedNo"
                            value={patientRoom.bedNo}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value=""> Select Bed No</option>
                            {bedRoomOptions.map((bed, index) => (
                                <option key={index} value={bed.bedNo}>
                                    {bed.bedNo}
                                </option>
                            ))}
                        </select>
                        {errors.bedNo && <p className="text-red-500 text-base mt-2 pl-1"> {errors.bedNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Room Status*
                        </label>
                        <select
                            name="roomStatus"
                            value={patientRoom.roomStatus}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value=""> Select Room No</option>
                            {roomStatus.map((roomStatus) => (<option key={roomStatus.id} value={roomStatus.value}>
                                {roomStatus.valueName}
                            </option>))}
                        </select>
                        {errors.roomStatus && <p className="text-red-500 text-base mt-2 pl-1"> {errors.roomStatus}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Active*
                        </label>
                        <select
                            name="active"
                            value={patientRoom.active}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Active</option>
                            {active.map((active) => (<option key={active.id} value={active.value}>
                                {active.valueName}
                            </option>))}
                        </select>
                        {errors.active && <p className="text-red-500 text-base mt-2 pl-1"> {errors.active}</p>}
                    </div>
                    <div>
                        <label>
                            Start Date*
                        </label>
                        <input
                            name="startDate"
                            type="date"
                            onChange={handleChange}
                            value={patientRoom.startDate}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500 mt-2"
                        />
                        {errors.startDate && <p className="text-red-500 text-base mt-2 pl-1"> {errors.startDate}</p>}
                    </div>
                    <div>
                        <label>
                            End Date*
                        </label>
                        <input
                            name="endDate"
                            type="date"
                            value={patientRoom.endDate}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500 mt-2"
                        />
                        {errors.endDate && <p className="text-red-500 text-base mt-2 pl-1"> {errors.endDate}</p>}
                    </div>
                </div>
                <div className="flex gap-4">
                    <button
                        type="button"
                        className="ui primary button"
                        onClick={handleAdd}
                    >
                        {editingIndex !== null ? "Update" : "Add"}
                    </button>
                    <button
                        type="button"
                        className="ui button"
                        onClick={resetForm}
                    >
                        Reset
                    </button>

                </div>
            </form>
            <div className="mt-6">
                <h3 className="text-lg font-bold mb-3">Assigned Rooms </h3>
                <div className="max-h-64 overflow-y-auto border rounded-md shadow-sm">
                    <table className="ui celled table" style={{whiteSpace: "nowrap"}}>
                        <thead>
                        <tr>
                            <th>Reg No</th>
                            <th>Ward</th>
                            <th>Room Category</th>
                            <th>Room Type</th>
                            <th>Room No</th>
                            <th>Bed No</th>
                            <th>Status</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Active</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {patientRoomList.length === 0 ? (
                            <tr>
                                <td colSpan="11" style={{textAlign: "center"}}>No Rooms Added</td>
                            </tr>
                        ) : (
                            patientRoomList.map((room, index) => (
                                <tr key={index}>
                                    <td>{room.patientRegNo}</td>
                                    <td>{getLabel(ward, room.ward, "wardId", "wardName")}</td>
                                    <td>{getLabel(roomCategory, room.roomCategory)}</td>
                                    <td>{getLabel(roomType, room.roomType)}</td>
                                    <td>{room.roomNo}</td>
                                    <td>{room.bedNo}</td>
                                    <td>{getLabel(roomStatus, room.roomStatus)}</td>
                                    <td>{room.startDate}</td>
                                    <td>{room.endDate}</td>
                                    <td>{getLabel(active, room.active)}</td>
                                    <td>
                                        <button
                                            type="button"
                                            className="ui tiny button"
                                            onClick={() => handleEdit(index)}
                                        >
                                            Edit
                                        </button>
                                        {editingIndex !== null && (
                                            <button
                                                type="button"
                                                className="ui tiny red button"
                                                onClick={() => handleDelete(index)}
                                                disabled={editingIndex !== null}
                                            >
                                                Delete
                                            </button>
                                        )}

                                    </td>
                                </tr>
                            ))
                        )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
            <div className="flex justify-end mt-4">
                <button className="ui primary button" type="submit" onClick={handleSubmit}>
                    {editPaientRoom ? "Update" : "Submit"}
                </button>
            </div>

        </div>
    </div>)
}
export default AddPatientRoom
 