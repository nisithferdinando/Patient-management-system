import React from 'react'
import {ClipLoader} from "react-spinners";
import {InfinitySpin} from "react-loader-spinner";

const ReactLoader = ({loading}) => {
    return (
        <div>
            <InfinitySpin
            color={"#2563EB"}
            loading={loading}
            size={50}
            speedMultiplier={1}
            />
        </div>
    )
}
export default ReactLoader
