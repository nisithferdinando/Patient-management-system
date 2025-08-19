import axiosInstance from "../util/axiosInstance.js";

export const doctorCategory = async () => {
    try {
        const response = await axiosInstance.get("/category/all");
        return response.data;
    } catch (error) {
        console.error('Error:', error);
        return [];
    }
};

export const doctorWards = async () => {
    try {
        const response = await axiosInstance.get("/ward/all");
        return response.data;
    } catch (error) {
        console.error('Error:', error);
        return [];
    }
}

export const nameSearchDropdown = async (value, schema, tableName) => {
    try {
        const response = await axiosInstance.get("/dropdown/names", {
            params: {
                schema,
                tableName,
                search: value,
            }
        });

        return response.data.map(item => ({
            label: item.fullName,
            value: item.id,
        }));
    } catch (error) {
        console.error("Name dropdown error:", error);
        return [];
    }
};

export const patientRegNoDropdown = async (regNo) => {
    try {
        const response = await axiosInstance.get("/patient/dropdown/search",
            {
                params: {regNo}
            });
        return response.data.map(item => ({
            label: `${item.regNo}-${item.firstName} ${item.lastName}`,
            value: item.patientId,
            patientRegNo: item.regNo,
            patientId: item.patientId,
            firstName: item.firstName,
            lastName: item.lastName,
            state: item.state,
        }))
    } catch (error) {
        console.error("Error:", error);
        return [];
    }
};

export const doctorByCategory = async (categoryId) => {
    try {
        const response = await axiosInstance.get("/doctor/get/doctors",
            {
                params: {category: categoryId},
            });
        return response.data.map(item => ({
            label: `${item.firstName} ${item.lastName}`,
            value: item.id,
            state: item.state,
            docId: item.docId,
            ...item,
        }));
    } catch (error) {
        console.error("Error:", error);
        return [];
    }
};