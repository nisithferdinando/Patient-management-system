import {createSlice} from "@reduxjs/toolkit";
import {replace} from "react-router-dom";

const mandatoryFields = ["state", "firstName", "lastName", "ward", "category", "docId", "gender", "status", "age"];
const camelToSentence = (str) =>
    str.replace(/([A-Z])/g, ' $1').replace(/^./, (s) => s.toUpperCase());
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


const initialState = {
    errors: {},
};
const doctorFormSlice = createSlice({
    name: "doctorForm",
    initialState,
    reducers: {
        validateDoctorForm: (state, action) => {
            const doctor = action.payload;
            const errors = {};
            for (let field of mandatoryFields) {
                if (!doctor[field] || String(doctor[field]).trim() === "") {
                    errors[field] = `${camelToSentence(field)} is required`
                }
            }
            if (doctor.email && !emailRegex.test(doctor.email)) {
                errors.email = "Email format is invalid";
            }
            state.errors = errors;
        },
        clearErrors: (state, action) => {
            const field = action.payload;
            if (field) {
                delete state.errors[field];
            } else {
                state.errors = {};
            }
        }
    }
});

export const {validateDoctorForm, clearErrors} = doctorFormSlice.actions;
export default doctorFormSlice.reducer;