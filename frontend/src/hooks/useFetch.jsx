import {useEffect, useState} from "react";
import axios from "axios";

export const useFetch = (URL) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = async () => {
        try {
            setLoading(true);
            const {data} = await axios.get(URL);
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
        fetchData()
    }, [URL]);

    return {data, loading, error, fetchData};
};
