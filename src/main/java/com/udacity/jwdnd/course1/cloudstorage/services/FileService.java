package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    public void store(MultipartFile file, User user) {

        try {
            File newFile = File.builder()
                    .fileData(file.getBytes())
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .fileName(file.getOriginalFilename())
                    .userId(1)
                    .build();

            fileMapper.insert(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> getAll(User user) {
        return fileMapper.getFiles(user.getUserId());
    }

}
