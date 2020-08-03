package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("fileUpload") MultipartFile file) {
        fileService.store(file);

        return "redirect:/home";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        fileService.delete(id);

        return "redirect:/home";
    }

    @GetMapping("/{id}/download")
    @ResponseBody
    public ResponseEntity<Resource> download(@PathVariable Integer id) {
        File file = fileService.getFile(id);
        Resource resource = new ByteArrayResource(file.getFileData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"" + file.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(resource);
    }

}
