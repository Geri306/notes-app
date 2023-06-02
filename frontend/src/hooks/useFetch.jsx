import {useEffect, useState} from "react";
import axios from "axios";

export const useFetch = (url, loggedIn, setRoles) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = async () => {
        const cred = localStorage.getItem("credentials")
        const requestOptions = loggedIn ? {
            headers: {
                Authorization: `Basic ${cred}`
            }
        } : {
            headers: {
                Authorization: `Basic sfsdgkljdsfj`
            }
        };

        try {
            setLoading(true);
            const response = await axios.get("http://localhost:9000/api/v1/notes"/*, requestOptions*/);
            if (response.status !== 200) {
                throw new Error(response.status + ", " + response.statusText);
            }
            const {data} = response
            setLoading(false);
            setData(data);
            setError(null);
        } catch (err) {
            setError(`Could not fetch data. ${err}`)
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchData();
    }, [url]);

    return {data, loading, error, fetchData};
};
