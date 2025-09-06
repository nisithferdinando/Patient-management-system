import React, {useEffect, useRef} from 'react'
import {useSelector} from "react-redux";

const PatientContactInfo = ({dropDownData, onRefsReady, onInputChange, editPatient}) => {

    const errors = useSelector(state => state.patientForm.errors);

    const refs = {
        contactNo: useRef(null),
        email: useRef(null),
        addressNo: useRef(null),
        address: useRef(null),
        stateName: useRef(null),
        country: useRef(null),
        emergencyContactNo: useRef(null),
    }

    useEffect(() => {
        onRefsReady('patientContactInfo', refs)
    }, []);

    useEffect(() => {
        if (editPatient) {
            Object.entries(refs).forEach(([key, ref]) => {
                if (ref.current && editPatient[key] !== undefined) {
                    ref.current.value = editPatient[key];
                }
            });
        }
    }, [editPatient]);

    const handleInputChange = (fieldName) => {
        if (onInputChange) {
            onInputChange(fieldName);
        }
    }

    return (
        <div>
            <div className="space-y-6">
                <h3 className="text-lg font-semibold text-gray-900 mb-4">Patient Contact Information</h3>
                <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Contact Number*
                        </label>
                        <input
                            type="tel"
                            ref={refs.contactNo}
                            onChange={() => handleInputChange('contactNo')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.contactNo && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.contactNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Email
                        </label>
                        <input
                            type="email"
                            ref={refs.email}
                            onChange={() => handleInputChange('email')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.email && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.email}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Address No
                        </label>
                        <input
                            type="text"
                            ref={refs.addressNo}
                            onChange={() => handleInputChange('addressNo')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.addressNo && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.addressNo}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Address
                        </label>
                        <input
                            type="text"
                            ref={refs.address}
                            onChange={() => handleInputChange('address')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.address && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.address}</p>}
                    </div>

                </div>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            State
                        </label>
                        <input
                            type="text"
                            ref={refs.stateName}
                            onChange={() => handleInputChange('stateName')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.stateName && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.stateName}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Country
                        </label>
                        <input
                            type="text"
                            ref={refs.country}
                            onChange={() => handleInputChange('country')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.country && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.country}</p>}
                    </div>
                    <div>
                        <label className="block text-base font-semibold text-gray-700 mb-2">
                            Emergency Contact No*
                        </label>
                        <input
                            type="text"
                            ref={refs.emergencyContactNo}
                            onChange={() => handleInputChange('emergencyContactNo')}
                            className="w-full border border-slate-300 rounded-md shadow-sm py-3 px-3 focus:outline-none  focus:ring-slate-500 focus:ring-0 focus:border-slate-500"
                        />
                        {errors.emergencyContactNo && <p className="text-red-500 text-base mt-2 pl-1 ">{errors.emergencyContactNo}</p>}
                    </div>
                </div>

            </div>
        </div>
    )
}
export default PatientContactInfo