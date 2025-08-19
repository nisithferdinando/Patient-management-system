import {createSlice} from "@reduxjs/toolkit";

const mandatoryFields = ["patientRegNo", "state", "firstName", "lastName", "admitCategory", "doctorCategory", "doctor", "admitStatus", "disease", "active"];
const camelToSentence = (str) =>
    str.replace(/([A-Z])/g, ' $1').replace(/^./, (s) => s.toUpperCase());
const initialState = {
    errors: {},
};

const admissionFormSlice = createSlice({
    name: "admissionForm",
    initialState,
    reducers: {
        validateAdmissionForm: (state, action) => {
            const admission = action.payload;
            const errors = {};
            for (let field of mandatoryFields) {
                if (!admission[field] || String(admission[field]).trim() === "") {
                    errors[field] = `${camelToSentence(field)} is required`;
                }
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
export const {validateAdmissionForm, clearErrors} = admissionFormSlice.actions;
export default admissionFormSlice.reducer;