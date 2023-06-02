import {useEffect, useState} from "react";
import axios from "axios";

export const useFetch = (url) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = async () => {
        try {
            setLoading(true);
            const response = await axios.get("http://localhost:9000/api/v1/notes");
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
