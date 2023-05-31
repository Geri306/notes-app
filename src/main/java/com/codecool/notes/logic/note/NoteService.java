package com.codecool.notes.logic.note;

import com.codecool.notes.api.exception.note.NoteNotFoundException;
import com.codecool.notes.persistence.entity.Note;
import com.codecool.notes.persistence.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMaintainer noteMaintainer;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note findById(Long id) throws NoteNotFoundException {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(
                                "note with id: '" + id + "' not found"));
    }

    public Note addNewEmptyNote() {
        return noteRepository.save(new Note());
    }

    public Note save(Note note) {
        return noteRepository.save(note);
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

    public Note updateNote(Note newNote, Note oldNote) {
        Note note = noteMaintainer.updateNote(newNote, oldNote);
        return noteRepository.save(note);
    }

    public Note assignNextLabelToNote(Note note) {
        return noteMaintainer.assignNextLabelToNote(note);
    }

    public Note formatDate(Note note) {
        return noteMaintainer.formatDate(note);
    }
}
