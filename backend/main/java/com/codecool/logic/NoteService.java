package com.codecool.logic;

import com.codecool.data.Label;
import com.codecool.persistence.entity.Note;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class NoteService {
    public Note getUpdatedNote(Note oldNote, Note newNote) {
        newNote.setId(oldNote.getId());
        return newNote;
    }

    public Note getNoteWithNextLabel(Note note) {
        int limit = Label.values().length;
        int nextIndex = Label.valueOf(note.getLabel().toString()).ordinal() + 1;
        Label nextLabel = Label.values()[nextIndex % limit];
        note.setLabel(nextLabel);
        return note;
    }
}