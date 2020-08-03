package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final UserService userService;

    public void create(Credentials credentials) {
        credentials.setUserId(userService.getCurrentUser().getUserId());
        credentialsMapper.insert(credentials);
    }

    public List<Note> getAll() {
        return credentialsMapper.getAll(userService.getCurrentUser().getUserId());
    }

    public void update(Credentials credentials) {
        credentials.setUserId(userService.getCurrentUser().getUserId());
        credentialsMapper.update(credentials);
    }

    public void delete(Integer credentialsId) {
        credentialsMapper.delete(credentialsId);
    }
}
