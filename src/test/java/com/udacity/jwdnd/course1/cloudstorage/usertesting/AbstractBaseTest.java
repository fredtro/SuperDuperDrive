package com.udacity.jwdnd.course1.cloudstorage.usertesting;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractBaseTest {

    @LocalServerPort
    protected Integer port;

    protected static WebDriver driver;

    @Autowired
    protected UserService userService;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    protected User createTestUser() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("password")
                .build();

        if (userService.getUser(user.getUsername()) == null) {
            userService.createUser(user);
        }

        return user;
    }

}
