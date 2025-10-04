import {createSlice} from "@reduxjs/toolkit";

const mandatoryFields = [
    "patientRegNo", "ward", "roomCategory", "roomType", "roomNo", "bedNo", "roomStatus", "startDate", "endDate", "active"
]
const camelToSentence = (str) =>
    str.replace(/([A-Z])/g, ' $1').replace(/^./, (s) => s.toUpperCase());
const initialState = {
    errors: {},
    isValidating: false
};
const patientRoomFormSlice = createSlice({

        name: "patientRoomForm",
        initialState,
        reducers: {
            validatePatientRoomForm: (state, action) => {
                const patientRoom = action.payload;
                const errors = {};
                state.isValidating = true;

                for (let field of mandatoryFields) {
                    if (!patientRoom[field] || String(patientRoom[field]).trim() === "") {
                        errors[field] = `${camelToSentence(field)} is required`;
                    }
                }
                state.errors = errors;
                state.isValidating = false;
            },
            clearErrors: (state, action) => {
                const field = action.payload;
                if (field) {
                    if (state.errors[field]) {
                        delete state.errors[field];
                    }
                } else {
                    state.errors = {};
                }
            },
            clearFieldErrors: (state, action) => {
                const field = action.payload;
                if (field && state.errors[field]) {
                    delete state.errors[field];
                }
            }
        }
    }
);
export const {validatePatientRoomForm, clearErrors, clearFieldErrors} = patientRoomFormSlice.actions;
export default patientRoomFormSlice.reducer;

