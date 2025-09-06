import React, {useEffect, useState} from 'react'
import {doctorWards} from "../dropdown/dropDownData.js";
import {getKeyData} from "../dropdown/keyValueData.js";
import axiosInstance from "../util/axiosInstance.js";
import ReactLoader from "../components/ReactLoader.jsx";
import {Table, TableHeaderCell} from "semantic-ui-react";
import search from "../assets/search.jpg";


const PatientRoom = () => {

    const [patientRoom, setPatientRoom] = useState([]);
    const [open, setOpen] = useState(false);
    const [wards, setwards] = useState([]);
    const [roomTypes, setRoomTypes] = useState([]);
    const [roomCategory, setRoomCategory] = useState([]);
    const [activePage, setActivePage] = useState(1);
    const [formData, setFormData] = useState({
        patientRegNo: "",
        ward: "",
        roomType: "",
        roomCategory: ""
    });

    const [loading, setLoading] = useState(false);
    const itemsPerPage = 8;
    const totalPages = Math.ceil(patientRoom.length / itemsPerPage);


    const handlePaginationChange = (e, {activePage}) => {
        setActivePage(activePage);
    };

    const searchPageResults = patientRoom.slice(
        (activePage - 1) * itemsPerPage,
        activePage * itemsPerPage
    );

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((data) => ({
            ...data,
            [name]: value,
        }));
    };

    useEffect(() => {
        (async () => {
            const wards = await doctorWards()
            setwards(wards);
            const roomTypes = await getKeyData("room_type");
            setRoomTypes(roomTypes);
            const roomCategory = await getKeyData("room_category");
            setRoomCategory(roomCategory);
        })();
    }, []);

    const handleSearch= async (e)=>{
        e.preventDefault();
        setLoading(true);
        const searchPatientRoom={
            patientRegNo: formData.patientRegNo || null,
            ward:formData.ward===""? -2 : parseInt(formData.ward),
            roomType:formData.ward===""? -2 : parseInt(formData.ward),
            roomCategory:formData.ward===""? -2 : parseInt(formData.ward),
        }
        try{
            const response= await axiosInstance.post("/room/search", searchPatientRoom);
            await new Promise(resolve => setTimeout(resolve,700));
            setPatientRoom(response.data);
            setActivePage(1);
        }
        catch(error){
            console.error('Error:', error);
        }
        setLoading(false);
    }

    const handleReset = () => {
       setFormData({
           patientRegNo: "",
           ward: "",
           roomType: "",
           roomCategory: ""
       });
    }

    return (
        <div>
            <div className="flex justify-end mt-4">
                <button className="ui primary button" onClick={() => setOpen(true)}>Add Rooms</button>
            </div>
            <div className="mt-4 ml-12 min-w-7xl bg-white p-6 rounded-xl shadow-md">
                <p className="text-blue-900 text-2xl font-semibold mb-4">Search Patient Rooms</p>
                <form className="space-y-4 mt-4">
                    <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Patient Reg No
                            </label>
                            <input
                                type="text"
                                name="patientRegNo"
                                placeholder="Enter Patient Reg No"
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                                value={formData.patientRegNo}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Ward
                            </label>
                            <select
                                name="ward"
                                value={formData.ward}
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            >
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
                                Room Type
                            </label>
                            <select
                                name="roomType"
                                value={formData.roomType}
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            >
                                <option value=""> Select Room Type</option>
                                {roomTypes.map((roomType) => (
                                    <option key={roomType.id} value={roomType.value}>
                                        {roomType.valueName}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div>
                            <label className="block text-base font-semibold text-gray-700 mb-1">
                                Room Category
                            </label>
                            <select
                                name="roomCategory"
                                value={formData.roomCategory}
                                onChange={handleChange}
                                className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            >
                                <option value=""> Select Room Category</option>
                                {roomCategory.map((roomCategory) => (
                                    <option key={roomCategory.id} value={roomCategory.value}>
                                        {roomCategory.valueName}
                                    </option>
                                ))}
                            </select>
                        </div>
                    </div>
                    <div className="mt-4 py-4 space-x-4">
                        <button class="ui primary button " onClick={handleSearch}> Search</button>
                        <button type="button" className="ui button" onClick={handleReset}>Reset</button>
                    </div>

                </form>
                <div className="mt-8">
                    {loading? (<div className="flex justify-center items-center p-20">
                        <ReactLoader loading={loading}/>
                        </div>
                        ) : ( <Table>
                            <Table.Header>
                                <Table.Row>
                                    <TableHeaderCell>Patient Reg No</TableHeaderCell>
                                    <TableHeaderCell>Name</TableHeaderCell>
                                    <TableHeaderCell>Patient Rooms</TableHeaderCell>
                                    <TableHeaderCell>Doctor</TableHeaderCell>
                                    <TableHeaderCell>Room Status</TableHeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                    {searchPageResults.length===0? (
                                        <Table.Row>
                                            <Table.Cell colSpan="5" textAlign="center">
                                                <div className="flex justify-center items-center ">
                                                    <img src={search} className="w-72 h-72"/>
                                                </div>
                                            </Table.Cell>
                                        </Table.Row>
                                    ): (
                                         patientRoom.map((patientRoom) => (
                                             <Table.Row key={patientRoom.patientId}>
                                                 <Table.Cell>{patientRoom.patientRegNo}</Table.Cell>
                                                 <Table.Cell>{patientRoom.fullName}</Table.Cell>
                                                 <Table.Cell>{patientRoom.rooms}</Table.Cell>
                                                 <Table.Cell>{patientRoom.doctor}</Table.Cell>
                                                 <Table.Cell >{patientRoom.roomStatusName}</Table.Cell>
                                             </Table.Row>
                                            ))
                                    )
                                    }
                            </Table.Body>
                        </Table>
                    )
                    }

                </div>
            </div>
        </div>
    )
}
export default PatientRoom;
