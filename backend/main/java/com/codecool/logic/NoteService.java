package com.codecool.logic;

import com.codecool.data.Label;
import com.codecool.persistence.entity.Note;
import com.codecool.persistence.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public Note addNewEmptyNote() {
        return noteRepository.save(new Note());
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void resetSequence() {
        noteRepository.resetSequence();
    }

    public Note updateNote(Note oldNote, Note newNote) {
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

    public Note formatDate(Note note) {
        LocalDateTime old = note.getModified_L();
        String formatted = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(old);
        note.setModified(formatted);
        return note;
    }
}
