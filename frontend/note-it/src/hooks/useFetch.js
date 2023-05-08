import {useEffect, useState} from "react";

export const useFetch = (url) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = async () => {
        try {
            setLoading(true);
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(response.status + ", " + response.statusText);
            }
            const json = await response.json();
            setLoading(false);
            setData(json);
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
