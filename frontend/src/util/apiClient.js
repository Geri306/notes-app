import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:9000/"
});

axiosInstance.interceptors.request.use(
    (config) => {
        config.headers.Authorization = `Basic ${localStorage.getItem("credentials")}`;
        config.withCredentials = true;
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
