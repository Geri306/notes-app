package com.codecool.notes.api.controller;

//import com.codecool.notes.api.controller.dto.Mapper;
import com.codecool.notes.api.exception.note.NoteNotFoundException;
import com.codecool.notes.logic.note.NoteService;
import com.codecool.notes.persistence.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
//    private final Mapper mapper;

    public List<Note> findAll() {
        return noteService.findAll()
                .stream()
                .map(noteService::formatDate)
                .toList();
//        return noteService.findAll()
//                .stream()
//                .map(mapper::toDto)
//                .map(noteService::formatDate)
//                .collect(toList());
    }

    public Note findById(Long id) throws NoteNotFoundException {
        Note note = noteService.findById(id);
        return noteService.formatDate(note);
    }

    public Note addNewEmptyNote() {
        return noteService.addNewEmptyNote();
    }

    public void deleteAll() {
        noteService.deleteAll();
        noteService.resetSequence();
    }

    public void deleteById(Long id) {
        noteService.deleteById(id);
    }

    public Note updateNote(Note newNote, Long oldNoteId) throws NoteNotFoundException {
        Note oldNote = noteService.findById(oldNoteId);
        return noteService.updateNote(newNote, oldNote);

    }

    public Note assignNextLabelToNote(Long id) throws NoteNotFoundException {
        Note note = noteService.findById(id);
        Note updatedNote = noteService.assignNextLabelToNote(note);
        return noteService.save(updatedNote);
    }
}
