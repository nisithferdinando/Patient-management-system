import {createSlice} from '@reduxjs/toolkit'

const mandatoryFields = [
    "state", "firstName", "lastName", "contactNo", "active", "regDate", "regTime",
    "emergencyContactNo", "relationState", "relationName", "relationNo"
];

const camelToSentence = (str) =>
    str.replace(/([A-Z])/g, ' $1').replace(/^./, (s) => s.toUpperCase());

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const initialState = {
    errors: {},
    isValidating: false
};

const patientFormSlice = createSlice({
    name: "patientForm",
    initialState,
    reducers: {
        validatePatientForm: (state, action) => {
            const patient = action.payload;
            const errors = {};
            state.isValidating=true

            state.isValidating = true;

            for (let field of mandatoryFields) {
                if (!patient[field] || String(patient[field]).trim() === "") {
                    errors[field] = `${camelToSentence(field)} is required`;
                }
            }

            if (patient.email && !emailRegex.test(patient.email)) {
                errors.email = "Email format is invalid";
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

        clearFieldError: (state, action) => {
            const field = action.payload;
            if (field && state.errors[field]) {
                delete state.errors[field];
            }
        }
    }
});

export const {validatePatientForm, clearErrors, clearFieldError} = patientFormSlice.actions
export default patientFormSlice.reducer;