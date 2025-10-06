import React, {useEffect, useState} from 'react'
import {getKeyData} from "../../dropdown/keyValueData.js";
import ReactLoader from "../ReactLoader.jsx";
import {foodDetails, patientFoodRegNo} from "../../dropdown/dropDownData.js";
import AsyncSelect from "react-select/async";
import {useDispatch, useSelector} from "react-redux";
import {clearErrors, validateFoodForm} from "../../slice/patientFoodFormSlice.js";
import {store} from "../../store.js";
import axiosInstance from "../../util/axiosInstance.js";
import {toast} from "react-toastify";

const AddFood = ({editFood, onClose, onSave}) => {

    const [loading, setLoading] = useState(false);
    const [food, setFood] = useState({
        patientId: "",
        patientRegNo: "",
        roomId: "",
        roomNo: "",
        mealType: "",
        mealId: "",
        mealCode: "",
        mealName: "",
        mealDate: "",
        active: "1",
        status: "2",
    });
    const [active, setActive] = useState([]);
    const [mealType, setMealType] = useState([]);
    const dispatch = useDispatch();
    const errors = useSelector((state) => state.patientFoodForm.errors);

    const handleChange = (e) => {
        const {name, value} = e.target;
        let val = value;
        if (["patientId", "roomId", "roomNo", "mealType", "active", "status"].includes(name)) {
            val = value === "" ? "" : Number(value);
        }
        if (name === "mealType") {
            setFood({
                ...food, [name]: val, mealId: "", mealCode: "", mealName: "",
            });
        } else {
            setFood({
                ...food, [name]: val
            });
        }
        dispatch(clearErrors(name));
    };

    useEffect(() => {
        if (editFood) {
            setFood({
                id: editFood.id,
                patientRegNo: editFood.patientRegNo,
                patientId: editFood.patientId,
                roomId: editFood.roomId,
                roomNo: editFood.roomNo,
                mealId: editFood.mealId,
                mealType: editFood.mealType,
                mealCode: editFood.mealCode,
                mealName: editFood.mealName,
                mealDate: editFood.mealDate ? editFood.mealDate.split('T')[0] : "",
                active: editFood.active,
                status: editFood.status,
            })
        }
    }, [editFood]);

    useEffect(() => {
        (async () => {
            const active = await getKeyData("active");
            setActive(active);
            const mealType = await getKeyData("meal_type");
            setMealType(mealType);
        })()
    }, [])

    const handleResetFood = () => {
        setFood({
            patientId: "",
            patientRegNo: "",
            roomId: "",
            roomNo: "",
            mealId: "",
            mealType: "",
            mealCode: "",
            mealName: "",
            mealDate: "",
            active: "",
            status: "2"
        })
    };

    const loadPatientRegNo = async () => {
        return await patientFoodRegNo();
    }
    const loadFoodDetails = async () => {
        return await foodDetails(food.mealType);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        dispatch(validateFoodForm(food));
        const currentErrors = store.getState().patientFoodForm.errors;
        const hasErrors = Object.keys(currentErrors).length > 0;
        if (hasErrors) {
            console.log(currentErrors);
            return;
        }
        setLoading(true);
        try {
            if (editFood) {
                await axiosInstance.put(`food/update/${editFood.id}`, food);
                await new Promise(resolve => setTimeout(resolve, 500));
                toast.success("Food updated successfully.");
                handleResetFood();
            } else {
                const response = await axiosInstance.post("/food/add", food);
                await new Promise(resolve => setTimeout(resolve, 400));
                handleResetFood();
                toast.success("Successfully added food");
            }
            onSave?.(e);
            onClose?.();
        } catch (error) {
            console.log(error);
            toast.error("Please, try again later");
        } finally {
            setLoading(false);
        }
    }
    const handlePatientSelect = (selected) => {
        if (selected) {
            setFood({
                ...food,
                patientId: selected.patientId,
                patientRegNo: selected.patientRegNo,
                roomId: selected.roomId,
                roomNo: selected.roomNo,
            });
            dispatch(clearErrors('patientRegNo'))
            dispatch(clearErrors("roomNo"));
        } else {
            setFood({
                ...food, patientId: "", patientRegNo: "", roomId: "", roomNo: "",
            })
        }
    };
    const handleFoodSelect = (selected) => {
        if (selected) {
            setFood({
                ...food, mealId: selected.mealId, mealCode: selected.mealCode, mealName: selected.mealName,
            });
            dispatch(clearErrors("mealName"));
            dispatch(clearErrors("mealCode"))
        } else {
            setFood({
                ...food, mealId: "", mealCode: "", mealName: "",
            })
        }
    }

    return (<div>
        {loading ? (<div className="flex justify-center items-center p-40">
            <ReactLoader loading={loading}/>
        </div>) : (<div className="mt-4">
            <form className="space-y-8 mt-4">
                <div className="grid grid-cols-1 md:grid-cols-3 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Patient Registration No *
                        </label>
                        <AsyncSelect
                            cacheOptions
                            defaultOptions
                            loadOptions={loadPatientRegNo}
                            onChange={handlePatientSelect}
                            placeholder="Search Reg No"
                            isClearable
                            value={food.patientRegNo ? {
                                label: food.patientRegNo, value: food.patientId, patientId: food.patientId,
                            } : null}
                        />
                        {errors.patientRegNo &&
                            <p className="text-red-500 text-base mt-2 pl-1">{errors.patientRegNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Room No
                        </label>
                        <input
                            name="roomNo"
                            value={food.roomNo}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-0 focus:border-slate-500"
                            disabled={true}
                        />
                        {errors.roomNo && <p className="text-red-500 text-base mt-2 pl-1">{errors.roomNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Meal Type *
                        </label>
                        <select
                            name="mealType"
                            value={food.mealType}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value=""> Select Meal Type</option>

                            {mealType.map((mealType) => (<option key={mealType.id} value={mealType.value}>
                                {mealType.valueName}
                            </option>))}
                        </select>
                        {errors.mealType && <p className="text-red-500 text-base mt-2 pl-1">{errors.mealType}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-2 space-x-4">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Meal Name *
                        </label>
                        <AsyncSelect
                            cacheOptions
                            defaultOptions
                            loadOptions={loadFoodDetails}
                            onChange={handleFoodSelect}
                            placeholder="Search Meal Name"
                            isClearable
                            key={food.mealType}
                            isDisabled={!food.mealType}
                            value={food.mealName ? {
                                label: food.mealName, value: food.mealName, mealId: food.mealId,
                            } : null}
                        />
                        {errors.mealName && <p className="text-red-500 text-base mt-2 pl-1">{errors.mealName}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Meal Code *
                        </label>
                        <input
                            name="mealCode"
                            value={food.mealCode}
                            onChange={handleChange}
                            disabled={true}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.mealCode && <p className="text-red-500 text-base mt-2 pl-1">{errors.mealCode}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Meal Date *
                        </label>
                        <input
                            name="mealDate"
                            type="date"
                            value={food.mealDate}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.mealDate && <p className="text-red-500 text-base mt-2 pl-1">{errors.mealDate}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Active *
                        </label>
                        <select
                            name="active"
                            value={food.active}
                            onChange={handleChange}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            {active.map((active) => (<option key={active.id} value={active.value}>
                                {active.valueName}
                            </option>))}
                        </select>
                        {errors.active && <p className="text-red-500 text-base mt-2 pl-1">{errors.active}</p>}
                    </div>
                </div>
                <div className="flex pt-4 space-x-4">
                    <button class="ui primary button"
                            onClick={handleSubmit}>
                        Submit
                    </button>
                    <button className="ui button"
                            type="button"
                            onClick={handleResetFood}>
                        Rest
                    </button>

                </div>
            </form>
        </div>)}
    </div>)
}
export default AddFood
