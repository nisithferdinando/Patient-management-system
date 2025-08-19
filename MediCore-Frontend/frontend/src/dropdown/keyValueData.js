import axiosInstance from "../util/axiosInstance.js";

export const getKeyData = async (keyValue) => {
    try {
        const response = await axiosInstance(`/key/key?key=${keyValue}`);
        return response.data;

    } catch (error) {
        console.error('Error:', error);
        return [];
    }
}