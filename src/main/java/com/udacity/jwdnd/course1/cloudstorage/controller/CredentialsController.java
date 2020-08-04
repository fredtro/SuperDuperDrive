package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/credentials")
public class CredentialsController {

    private final CredentialsService credentialsService;

    @PostMapping
    public String createOrUpdate(Credentials credential, RedirectAttributes model) {

        if (credential.getCredentialId() != null) {
            credentialsService.update(credential);
            model.addFlashAttribute("success", "Credentials updated!");
        }else{
            credentialsService.create(credential);
            model.addFlashAttribute("success", "Credentials created!");
        }

        return "redirect:/home#nav-credentials";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer credentialsId, RedirectAttributes model) {
        credentialsService.delete(credentialsId);
        model.addFlashAttribute("warning", "Credentials deleted!");

        return "redirect:/home#nav-credentials";
    }


}
