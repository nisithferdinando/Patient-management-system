import React from "react";
import AsyncSelect from "react-select/async";
import { patientRegNoDropdown } from "./dropDownData";

const PatientRegNoDropdown = ({ value, onChange, placeholder }) => {
    const loadOptions = (inputValue) => patientRegNoDropdown(inputValue);

    return (
        <AsyncSelect
            cacheOptions
            defaultOptions
            loadOptions={loadOptions}
            onChange={onChange}
            value={value}
            isClearable
            placeholder={placeholder || "Search Reg No..."}
        />
    );
};

export default PatientRegNoDropdown;
