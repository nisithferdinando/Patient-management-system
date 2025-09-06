import React, {useEffect, useRef} from 'react'
import {useSelector} from "react-redux";

const PatientRelationInfo = ({dropDownData, onRefsReady, onInputChange, editPatient}) => {

    const errors = useSelector(state => state.patientForm.errors);

    const refs = {
        relationState: useRef(null),
        relationName: useRef(null),
        relationNo: useRef(null),
        regDate: useRef(null),
        regTime: useRef(null),
    }

    useEffect(() => {
        onRefsReady('patientRelationInfo', refs)
    }, []);

    useEffect(() => {
        const dropdown = dropDownData?.relationState?.length;
        if (editPatient && dropdown) {
            Object.entries(refs).forEach(([key, ref]) => {
                if (ref.current && editPatient[key] !== undefined) {
                    let value = editPatient[key];
                    if (key === 'regDate' && value) {
                        value = value.split('T')[0];
                    } else if (key === 'regTime' && value) {
                        if (value.length >= 8) {
                            value = value.substring(0, 5);
                        }
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
            <div className="space-y-6">
                <h3 className="text-lg font-semibold text-gray-900 mb-4 ">Patient Relation Information</h3>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            State*
                        </label>
                        <select
                            ref={refs.relationState}
                            onChange={() => handleInputChange('relationState')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        >
                            <option value="">Select Relation State</option>
                            {dropDownData.relationState?.map((item) => (
                                <option key={item.id} value={item.value}>
                                    {item.valueName}
                                </option>
                            ))}
                        </select>
                        {errors.relationState &&
                            <p className="text-red-500 text-base mt-2 pl-1 ">{errors.relationState}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Relation Name*
                        </label>
                        <input
                            type="text"
                            ref={refs.relationName}
                            onChange={() => handleInputChange('relationName')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.relationName &&
                            <p className="text-red-500 text-base mt-2 pl-1 ">{errors.relationName}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Relation Contact Number*
                        </label>
                        <input
                            type="tel"
                            ref={refs.relationNo}
                            onChange={() => handleInputChange('relationNo')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.relationNo && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.relationNo}</p>}
                    </div>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Admitted Date*
                        </label>
                        <input
                            type="date"
                            ref={refs.regDate}
                            onChange={() => handleInputChange('regDate')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.regDate && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.regDate}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Admitted Time*
                        </label>
                        <input
                            type="time"
                            ref={refs.regTime}
                            onChange={() => handleInputChange('regTime')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.regTime && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.regTime}</p>}
                    </div>
                </div>
            </div>
        </div>
    )
}
export default PatientRelationInfo