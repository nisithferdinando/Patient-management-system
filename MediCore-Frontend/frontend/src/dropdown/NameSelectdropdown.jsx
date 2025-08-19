import React from 'react'
import AsyncSelect from "react-select/async";
import {nameSearchDropdown} from "./dropDownData.js";

const NameSelectdropdown = ({value, schema, tableName, placeholder, onChange}) => {

    const loadOptions=(value)=>
        nameSearchDropdown(value, schema, tableName);
    return (
        <div>
           <AsyncSelect
           cacheOptions
           defaultOptions
          loadOptions={loadOptions}
           onChange={onChange}
           value={value}
           isClearable
           placeholder={placeholder|| "search..."}
           />

        </div>
    )
}
export default NameSelectdropdown
