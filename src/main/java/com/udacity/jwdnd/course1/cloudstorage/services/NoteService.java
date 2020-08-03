package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserService userService;

    public void create(Note note) {
        note.setUserId(userService.getCurrentUser().getUserId());
        noteMapper.insert(note);
    }

    public List<Note> getAll() {
        return noteMapper.getAll(userService.getCurrentUser().getUserId());
    }

    public void update(Note note) {
        note.setUserId(userService.getCurrentUser().getUserId());
        noteMapper.update(note);
    }

    public void delete(Integer noteId) {
        noteMapper.delete(noteId);
    }

}
