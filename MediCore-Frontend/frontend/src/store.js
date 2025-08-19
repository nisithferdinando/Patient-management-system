import {configureStore} from "@reduxjs/toolkit";
import doctorFormReducer from './slice/doctorFormSlice.js'
import patientFormReducer from './slice/patientFormSlice.js'
import admissionFormReducer from './slice/admissionFormSlice.js'

export const store = configureStore({
    reducer: {
        doctorForm: doctorFormReducer,
        patientForm: patientFormReducer,
        admissionForm: admissionFormReducer,
    }
})