package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("files", fileService.getAll());
        model.addAttribute("notes", noteService.getAll());
        model.addAttribute("credentials", credentialsService.getAll());
        return "home";
    }
}
