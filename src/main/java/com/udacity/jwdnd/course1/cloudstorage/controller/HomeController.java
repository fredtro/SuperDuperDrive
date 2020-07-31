package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final FileService fileService;
    private final UserService userService;

    @GetMapping()
    public String getHomePage(Model model) {
        model.addAttribute("files", fileService.getAll(userService.getCurrentUser()));

        return "home";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file) {
        fileService.store(file, userService.getCurrentUser());

        return "redirect:/home";
    }

}
