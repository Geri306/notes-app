package com.codecool.notes.logic;

import com.codecool.notes.data.Label;
import com.codecool.notes.persistence.entity.Note;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FormatService {
    public Note assignNextLabelToNote(Note note) {
        int limit = Label.values().length;
        int nextIndex = Label.valueOf(note.getLabel().toString()).ordinal() + 1;
        Label nextLabel = Label.values()[nextIndex % limit];
        note.setLabel(nextLabel);
        return note;
    }

    public Note formatDate(Note note) {
        LocalDateTime old = note.getModifiedLong();
        String formatted = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(old);
        note.setModified(formatted);
        return note;
    }

    public Note updateNote(Note oldNote, Note newNote) {
        newNote.setId(oldNote.getId());
        return newNote;
    }
}
