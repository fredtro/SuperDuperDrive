package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final EncryptionService encryptionService;

    @GetMapping
    public String getHomePage(Model model) {
        List<Credentials> credentials = credentialsService.getAll();
        credentials.forEach(c -> c.setPasswordPlainText(encryptionService.decryptValue(c.getPassword(), c.getKey())));

        model.addAttribute("files", fileService.getAll());
        model.addAttribute("notes", noteService.getAll());
        model.addAttribute("credentials", credentials);
        return "home";
    }
}
