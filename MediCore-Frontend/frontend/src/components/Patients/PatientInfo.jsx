import React, {useEffect, useRef} from 'react'
import {useSelector} from "react-redux";

const PatientInfo = ({dropDownData, onRefsReady, onInputChange, editPatient}) => {

    const errors = useSelector(state => state.patientForm.errors);

    const refs = {
        state: useRef(null),
        firstName: useRef(null),
        lastName: useRef(null),
        dateOfBirth: useRef(null),
        age: useRef(null),
        gender: useRef(null),
        active: useRef(null)
    }
    useEffect(() => {
        onRefsReady('patientInfo', refs)
        const dropdown = dropDownData?.state?.length && dropDownData?.gender?.length && dropDownData?.active?.length;

        if (editPatient && dropdown) {
            Object.entries(refs).forEach(([key, ref]) => {
                if (ref.current && editPatient[key] !== undefined) {
                    let value = editPatient[key];
                    if (key === 'dateOfBirth' && value) {
                        value = value.split('T')[0];
                    }
                    ref.current.value = value;
                }
            });
        }
    }, [editPatient, dropDownData]);

    const handleInputChange = (fieldName) => {
        if (onInputChange) {
            onInputChange(fieldName);
        }
    }

    return (
        <div>
            <div className="mt-4 space-y-6">
                <h3 className="text-lg font-semibold text-gray-900 mb-4">Personal Information</h3>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            State*
                        </label>
                        <select
                            ref={refs.state}
                            onChange={() => handleInputChange('state')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500">
                        >
                            <option value=""> Select State</option>
                            {
                                dropDownData.state?.map(item => (
                                    <option key={item.id} value={item.value}>
                                        {item.valueName}
                                    </option>
                                ))
                            }
                        </select>
                        {errors.state && <p className="text-red-500 text-base mt-2 pl-1">{errors.state}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            First Name*
                        </label>
                        <input
                            type="text"
                            ref={refs.firstName}
                            onChange={() => handleInputChange('firstName')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.firstName && <p className="text-red-500 text-base mt-2 pl-1">{errors.firstName}</p>}
                    </div>

                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Last Name*
                        </label>
                        <input
                            type="text"
                            ref={refs.lastName}
                            onChange={() => handleInputChange('lastName')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.lastName && <p className="text-red-500 text-base mt-2 pl-1">{errors.lastName}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Date of Birth*
                        </label>
                        <input
                            type="date"
                            ref={refs.dateOfBirth}
                            onChange={() => handleInputChange('dateOfBirth')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.dateOfBirth && <p className="text-red-500 text-base mt-2 pl-1">{errors.dateOfBirth}</p>}
                    </div>

                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Age*
                        </label>
                        <input
                            type="number"
                            ref={refs.age}
                            onChange={() => handleInputChange('age')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.age && <p className="text-red-500 text-base mt-2 pl-1">{errors.age}</p>}
                    </div>

                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Gender*
                        </label>
                        <select
                            ref={refs.gender}
                            onChange={() => handleInputChange('gender')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Gender</option>
                            {dropDownData.gender?.map((item) => (
                                <option key={item.id} value={item.value}>
                                    {item.valueName}
                                </option>
                            ))}
                        </select>
                        {errors.gender && <p className="text-red-500 text-base mt-2 pl-1">{errors.gender}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Active*
                        </label>
                        <select
                            ref={refs.active}
                            onChange={() => handleInputChange('active')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"

                        >
                            <option value="">Select Status</option>
                            {dropDownData.active?.map((item) => (
                                <option key={item.id} value={item.value}>
                                    {item.valueName}
                                </option>
                            ))}
                        </select>
                        {errors.active && <p className="text-red-500 text-base mt-2 pl-1">{errors.active}</p>}
                    </div>

                </div>
            </div>
        </div>
    )
}
export default PatientInfo