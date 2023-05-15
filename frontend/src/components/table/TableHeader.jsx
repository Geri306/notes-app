import React from "react";

export default function TableHeader({notes}) {
    return (
        <tr>
            {notes && notes.length !== 0 &&
                Object
                    .keys(notes[0])
                    .map((objectKey, i) =>
                        i === 0
                            ? (<th key={i}>#</th>)
                            : (<th key={i}>
                                {objectKey.charAt(0)
                                        .toUpperCase() +
                                    objectKey.slice(1)
                                }
                            </th>)
                    )}
        </tr>
    );
}

