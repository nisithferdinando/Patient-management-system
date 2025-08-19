import React, {useEffect, useState} from 'react'
import PatientInfo from "./PatientInfo.jsx";
import PatientContactInfo from "./PatientContactInfo.jsx";
import PatientRelationInfo from "./PatientRelationInfo.jsx";
import {getKeyData} from "../../dropdown/keyValueData.js";
import {ChevronLeft, ChevronRight} from "lucide-react";
import axiosInstance from "../../util/axiosInstance.js";
import {toast} from "react-toastify";
import {useDispatch, useSelector} from "react-redux";
import {validatePatientForm, clearErrors} from "../../slice/patientFormSlice.js";
import {store} from "../../store.js";
import ReactLoader from "../ReactLoader.jsx";

const AddPatient = ({editPatient, onClose, onSave}) => {

    const dispatch = useDispatch();
    const errors = useSelector(state => state.patientForm.errors);

    const [currentTab, setCurrentTab] = useState(0);
    const [dropDownData, setDropDownData] = useState({
        state: [],
        active: [],
        gender: [],
        relationState: [],
    });
    const [loading, setLoading] = useState(false);
    const [componentRefs, setComponentRefs] = useState({});

    const handleRefsReady = (componentName, refs) => {
        setComponentRefs(prev => ({
            ...prev,
            [componentName]: refs
        }))
        dispatch(clearErrors());
    }

    useEffect(() => {
        (async () => {
            try {
                const [state, gender, active, relationState] = await Promise.all([
                    getKeyData("state"),
                    getKeyData("gender"),
                    getKeyData("active"),
                    getKeyData("state"),
                ])
                setDropDownData({
                    state,
                    gender,
                    active,
                    relationState,
                })
            } catch (error) {
                console.log(error);
            }
        })()
    }, [])

    useEffect(() => {
        if (editPatient) {

        }

    }, []);

    const tabs = [
        {id: 0, title: 'Personal Info', component: PatientInfo},
        {id: 1, title: 'Patient Contact Info', component: PatientContactInfo},
        {id: 2, title: 'Patient Relation Info', component: PatientRelationInfo},
    ]

    const collectFormData = () => {
        const formData = {}

        Object.values(componentRefs).forEach(componentRef => {
            Object.keys(componentRef).forEach(fieldName => {
                const ref = componentRef[fieldName]
                if (ref && ref.current) {
                    // Get the value based on input type
                    if (ref.current.type === 'checkbox') {
                        formData[fieldName] = ref.current.checked
                    } else if (ref.current.type === 'radio') {
                        if (ref.current.checked) {
                            formData[fieldName] = ref.current.value
                        }
                    } else {
                        formData[fieldName] = ref.current.value
                    }
                }
            })
        })

        if (!formData.regDate) {
            formData.regDate = new Date().toISOString().split('T')[0]
        }
        if (formData.regTime && formData.regTime.length === 5) {
            formData.regTime = `${formData.regTime}:00`;
        }
        return formData
    }

    const getTabFields = (tabIndex) => {
        switch (tabIndex) {
            case 0:
                return [
                    'state', 'firstName', 'lastName', 'dateOfBirth', 'age', 'gender', 'active'
                ];
            case 1:
                return [
                    'contactNo', 'emergencyContactNo', 'email', 'addressNo', 'address', 'stateName', 'country'
                ];
            case 2:
                return [
                    'relationState', 'relationName', 'relationNo', 'regDate', 'regTime'
                ];
            default:
                return [];
        }
    };

    const validateCurrentTab = (formData, tabIndex) => {
        const tabFields = getTabFields(tabIndex);
        const tabData = {};

        tabFields.forEach(field => {
            if (formData.hasOwnProperty(field)) {
                tabData[field] = formData[field];
            }
        });

        return tabData;
    };

    const handleNext = async () => {
        try {
            dispatch(clearErrors());
            const formData = collectFormData();

            const currentTabData = validateCurrentTab(formData, currentTab);
            dispatch(validatePatientForm(currentTabData));

            setTimeout(() => {
                const currentErrors = store.getState().patientForm.errors;
                const tabFields = getTabFields(currentTab);
                const tabErrors = {};

                tabFields.forEach(field => {
                    if (currentErrors[field]) {
                        tabErrors[field] = currentErrors[field];
                    }
                });

                if (Object.keys(tabErrors).length === 0) {
                    dispatch(clearErrors());
                    setCurrentTab(prevTab => prevTab + 1);
                } else {
                    console.log('Current tab validation failed:', tabErrors);
                }
            }, 0);

        } catch (error) {
            console.error('Error in handleNext:', error);
            toast.error("An error occurred while validating the form");
        }
    };

    const handleBack = () => {
        if (currentTab > 0) {
            setCurrentTab(currentTab - 1);
            dispatch(clearErrors());
        }
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            dispatch(clearErrors());
            const formData = collectFormData();
            if (editPatient && editPatient.id) {
                formData.id = editPatient.id;
            }
            dispatch(validatePatientForm(formData));

            setTimeout(async () => {
                const lastError = store.getState().patientForm.errors;
                if (Object.keys(lastError).length > 0) {
                    const currentTabFields = getTabFields(currentTab);
                    const currentTabHasErrors = currentTabFields.some(field => lastError[field]);

                    if (currentTabHasErrors) {
                        toast.error("Please fill the required fields before submitting");
                    } else {
                        toast.error("Please fill the required fields before submitting");
                        const allTabs = [0, 1, 2];
                        for (let tabIndex of allTabs) {
                            const tabFields = getTabFields(tabIndex);
                            const hasTabErrors = tabFields.some(field => lastError[field]);
                            if (hasTabErrors) {
                                setCurrentTab(tabIndex);
                                break;
                            }
                        }
                    }
                    return;
                }
                try {
                    setLoading(true);
                    if (editPatient) {
                        formData.id = editPatient.id;
                        formData.regNo = editPatient.regNo;
                        console.log("Submitting UPDATE with regNo:", formData.regNo);
                        const response = await axiosInstance.put(`/patient/update/${editPatient.id}`, formData);
                        await new Promise(resolve => setTimeout(resolve, 700));
                        toast.success("Successfully updated patient");
                    } else {
                        await axiosInstance.post("/patient/add", formData);
                        await new Promise(resolve => setTimeout(resolve, 400));
                        toast.success("Patient is added successfully.");
                    }
                    dispatch(clearErrors());
                    setCurrentTab(0);
                    setComponentRefs({});
                    onSave?.(e);
                    onClose?.();
                } catch (error) {
                    console.error('Submission error:', error);
                    toast.error("Submission failed");
                } finally {
                    setLoading(false);
                }
            }, 200);

        } catch (error) {
            console.error('Error in handleSubmit:', error);
            toast.error("An error occurred while submitting the form");
            setLoading(false);
        }
    };

    useEffect(() => {
        dispatch(clearErrors());
    }, [dispatch]);

    useEffect(() => {

        dispatch(clearErrors());
    }, [currentTab, dispatch]);

    const getAllComponents = () => {
        return tabs.map((tab, index) => {
            const Component = tab.component
            return (
                <div
                    key={tab.id}
                    style={{display: currentTab === index ? 'block' : 'none'}}
                >
                    <Component
                        dropDownData={dropDownData}
                        onRefsReady={handleRefsReady}

                        onInputChange={(fieldName) => {
                            if (fieldName && errors[fieldName]) {
                                dispatch(clearErrors(fieldName));
                            }
                        }}
                        editPatient={editPatient}
                    />
                </div>
            )
        })
    }

    return (
        <div className="max-w-7xl mx-auto p-6">
            {loading ? (
                <div className="flex justify-center items-center p-40">
                    <ReactLoader loading={loading}/>
                </div>
            ) : (
                <div className="mb-8">
                    <div className="px-6 border-b border-gray-200">
                        <div className="flex space-x-8">
                            {tabs.map((tab) => (
                                <button
                                    key={tab.id}
                                    onClick={() => setCurrentTab(tab.id)}
                                    className={`pb-2 px-1 border-b-2 font-medium text-lg ${
                                        currentTab === tab.id
                                            ? 'border-blue-500 text-blue-600'
                                            : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                                    }`}
                                    disabled={false}
                                >
                                    {tab.title}
                                </button>
                            ))}
                        </div>
                    </div>

                    <div className="px-6 py-6">
                        <div className="space-y-6">
                            {getAllComponents()}
                        </div>
                    </div>

                    <div className="px-6 py-4 border-t border-gray-200 flex justify-between">
                        <button
                            type="button"
                            onClick={handleBack}
                            disabled={currentTab === 0}
                            className={`ui button ${
                                currentTab === 0
                                    ? 'bg-gray-100 text-gray-400 cursor-not-allowed'
                                    : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                            }`}
                        >
                            Back
                        </button>

                        {currentTab === tabs.length - 1 ? (
                            <button
                                type="button"
                                onClick={handleSubmit}
                                disabled={loading}
                                class="ui primary button">
                                {editPatient ? "Update Patient" : "Add Patient"}
                            </button>
                        ) : (
                            <button
                                type="button"
                                onClick={handleNext}
                                className="ui primary button"
                            >
                                Next
                            </button>
                        )}
                    </div>
                </div>
            )}
        </div>
    )
}
export default AddPatient