package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public void create(Credentials credentials) {
        encrypPassword(credentials);
        credentials.setUserId(userService.getCurrentUser().getUserId());

        credentialsMapper.insert(credentials);
    }

    public List<Credentials> getAll() {
        return credentialsMapper.getAll(userService.getCurrentUser().getUserId());
    }

    public void update(Credentials credentials) {
        encrypPassword(credentials);
        credentials.setUserId(userService.getCurrentUser().getUserId());

        credentialsMapper.update(credentials);
    }

    public void delete(Integer credentialsId) {
        credentialsMapper.delete(credentialsId);
    }

    private void encrypPassword(Credentials credentials) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);

        credentials.setPassword(encryptedPassword);
        credentials.setKey(encodedKey);
    }
}
