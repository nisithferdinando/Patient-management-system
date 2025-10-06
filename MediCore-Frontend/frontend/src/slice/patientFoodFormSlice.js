import {createSlice} from "@reduxjs/toolkit";

const mandatoryFields = ["patientRegNo", "roomNo", "mealType", "mealName", "mealCode", "mealDate", "active"];
const camelToSentence = (str) =>
    str.replace(/([A-Z])/g, ' $1').replace(/^./, (s) => s.toUpperCase());
const initialState = {
    errors: {},
};
const patientFoodFormSlice = createSlice({
    name: "foodForm",
    initialState,
    reducers: {
        validateFoodForm: (state, action) => {
            const patientFood = action.payload;
            const errors = {};
            for (let field of mandatoryFields) {
                if (!patientFood[field] || String(patientFood[field]).trim() === "") {
                    errors[field] = `${camelToSentence(field)} is Required`
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
export const {validateFoodForm, clearErrors} = patientFoodFormSlice.actions;
export default patientFoodFormSlice.reducer;