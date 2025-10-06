import React, {useEffect, useState} from 'react'
import {getKeyData} from "../dropdown/keyValueData.js";
import axiosInstance from "../util/axiosInstance.js";
import ReactLoader from "../components/ReactLoader.jsx";
import {Modal, Pagination, Table} from "semantic-ui-react";
import search from "../assets/search.jpg";
import AddFood from "../components/Patients/AddFood.jsx";
import {useDispatch} from "react-redux";
import {clearErrors} from "../slice/patientFoodFormSlice.js";

const Food = () => {

    const [formData, setFormData] = useState({
        patientRegNo: "", roomNo: "", mealType: "", mealDate: "", active: ""
    });
    const [food, setFood] = useState([]);
    const [open, setOpen] = useState(false);
    const [mealType, setMealType] = useState([]);
    const [active, setActive] = useState([]);
    const [loading, setLoading] = useState(false);
    const [activePage, setActivePage] = useState(1);
    const itemsPerPage = 8;
    const totalPages = Math.ceil(food.length / itemsPerPage);
    const [editFood, setEditFood] = useState(null);
    const dispatch = useDispatch();

    const handlePaginationChange = (e, {activePage}) => {
        setActivePage(activePage);
    };
    const searchFood = food.slice(
        (activePage - 1) * itemsPerPage,
        activePage * itemsPerPage
    )
    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((data) => ({
            ...data, [name]: value,
        }))
    };

    useEffect(() => {
        (async () => {
            const mealType = await getKeyData("meal_type")
            setMealType(mealType);
            const active = await getKeyData("active");
            setActive(active);
        })()
    }, []);

    const handleSearch = async (e) => {
        e.preventDefault()
        setLoading(true);
        const mealSearch = {
            patientRegNo: formData.patientRegNo || null,
            roomNo: formData.roomNo === "" ? -2 : parseInt(formData.roomNo),
            mealType: formData.mealType === "" ? -2 : parseInt(formData.mealType),
            mealDate: formData.mealDate ? new Date(formData.mealDate).toISOString().split("T")[0] : null,
            active: formData.active === "" ? -2 : parseInt(formData.active),
        }
        try {
            const response = await axiosInstance.post("/food/search", mealSearch);
            await new Promise(resolve => setTimeout(resolve, 700));
            setFood(response.data);
        } catch (error) {
            console.error('Error:', error);
        }
        setLoading(false);
    };
    console.log("food", food);

    const handleRowDoubleClick = async (foodItem) => {
        const foodDetails = {
            id: foodItem.id,
            patientId: foodItem.patientId,
        }
        console.log("food details", foodDetails);
        try {
            const response = await axiosInstance.post("/food/get", foodDetails);
            setEditFood(response.data);
            dispatch(clearErrors());
            setOpen(true);
        } catch (error) {
            console.error('Error:', error);
        }
    }

    const handleReset = () => {
        setFormData({
            patientRegNo: "",
            roomNo: "",
            mealType: "",
            mealDate: "",
            active: ""
        })
    };
    return (<div>
        <div className="flex justify-end mt-4">
            <button className="ui primary button" onClick={() => setOpen(true)}>Add Meal</button>
        </div>
        <Modal
            open={open}
            onClose={() => {
                dispatch(clearErrors());
                setOpen(false);
                setEditFood(null);
            }}
            size="large"
            closeIcon
        >
            <Modal.Header>New Food</Modal.Header>
            <Modal.Content>
                <AddFood
                    editFood={editFood}
                    onClose={() => {
                        setOpen(false)
                        setEditFood(null);
                    }}
                    onSave={handleSearch}
                />
            </Modal.Content>

        </Modal>
        <div className="mt-4 ml-12 min-w-7xl bg-white p-6 rounded-xl shadow-md">
            <p className="text-blue-900 text-2xl font-semibold mb-4">Search Patient Meals</p>
            <form className="space-y-4 mt-4">
                <div className="grid grid-cols-1 md:grid-cols-5 gap-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-1">
                            Patient Reg No
                        </label>
                        <input
                            type="text"
                            name="patientRegNo"
                            placeholder="Enter Patient Reg No"
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            value={formData.patientRegNo}
                        />
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-1">
                            Room No
                        </label>
                        <input
                            type="number"
                            name="roomNo"
                            placeholder="Enter Room No"
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            value={formData.roomNo}
                            onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-1">
                            Meal Type
                        </label>
                        <select
                            name="mealType"
                            onChange={handleChange}
                            value={formData.mealType}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                        >
                            <option value="">Select Meal Type</option>
                            {mealType.map((mealType) => (<option key={mealType.id} value={mealType.value}>
                                    {mealType.valueName}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-1">
                            Meal Date
                        </label>
                        <input
                            type="date"
                            name="mealDate"
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            placeholder="Enter Meal Date"
                            onChange={handleChange}
                            value={formData.mealDate}
                        />
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-1">
                            Active
                        </label>
                        <select
                            name="active"
                            onChange={handleChange}
                            value={formData.active}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                        >
                            <option value="">Select Active</option>
                            {active.map((active) => (
                                <option key={active.id} value={active.value}>
                                    {active.valueName}
                                </option>
                            ))}
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
                ) : (
                    <Table>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Patient Reg No</Table.HeaderCell>
                                <Table.HeaderCell>Patient Name</Table.HeaderCell>
                                <Table.HeaderCell>Room No</Table.HeaderCell>
                                <Table.HeaderCell>Meal Type</Table.HeaderCell>
                                <Table.HeaderCell>Meal</Table.HeaderCell>
                                <Table.HeaderCell>Date</Table.HeaderCell>
                                <Table.HeaderCell>Active</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        <Table.Body>
                            {searchFood.length === 0 ? (
                                <Table.Row>
                                    <Table.Cell colSpan="7" textAlign="center">
                                        <div className="flex justify-center items-center ">
                                            <img src={search} className="w-72 h-72"/>
                                        </div>
                                    </Table.Cell>
                                </Table.Row>
                            ) : (
                                searchFood.map((food) => (
                                    <Table.Row key={food.id} onDoubleClick={() => {
                                        handleRowDoubleClick(food)
                                    }}
                                               style={{cursor: 'pointer'}}>
                                        <Table.Cell>{food.patientRegNo}</Table.Cell>
                                        <Table.Cell>{food.patientName}</Table.Cell>
                                        <Table.Cell>{food.roomNo}</Table.Cell>
                                        <Table.Cell>{food.mealTypeName}</Table.Cell>
                                        <Table.Cell>{food.mealName}</Table.Cell>
                                        <Table.Cell>{food.mealDate ? food.mealDate.split("T")[0] : ""}</Table.Cell>
                                        <Table.Cell>{food.activeValue}</Table.Cell>
                                    </Table.Row>
                                ))
                            )
                            }
                        </Table.Body>
                    </Table>
                )
                }
                {food.length > itemsPerPage && (

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
    </div>)
}
export default Food
