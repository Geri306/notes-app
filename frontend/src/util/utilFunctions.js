import axiosInstance from "./apiClient.js";

export function parseLabels(label) {
    return label === "GREEN"
        ? "🟢"
        : label === "YELLOW"
            ? "🟡"
            : "🔴";
}

export async function updateNoteInDb(wantedId, updatedNote) {
    try {
        await axiosInstance.put(`api/v1/notes/${wantedId}`, updatedNote);
    } catch (err) {
        handleError("Error during update: " + err)
    }

}

export function handleError(message, error) {
    console.error(message + error)
}




