package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudStorageApplication implements ApplicationRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = User.builder()
				.password("password")
				.firstName("Initial")
				.lastName("User")
				.username("fred")
				.build();

		userService.createUser(user);
	}
}
