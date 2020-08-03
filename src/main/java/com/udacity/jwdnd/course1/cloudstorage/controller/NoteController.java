package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public String createOrUpdate(Note note, RedirectAttributes model) {

        if (note.getNoteId() != null) {
            noteService.update(note);
            model.addFlashAttribute("success", "Note updated!");
        }else{
            noteService.create(note);
            model.addFlashAttribute("success", "Note created!");
        }

        return "redirect:/home#nav-notes";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer noteId, RedirectAttributes model) {
        noteService.delete(noteId);
        model.addFlashAttribute("warning", "Note deleted!");

        return "redirect:/home#nav-notes";
    }


}
