package com.udacity.jwdnd.course1.cloudstorage.controller.login;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;

    @Autowired
    private UserService userService;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);

        createTestUser();
    }

    private void createTestUser() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("password")
                .build();

        if (userService.getUser(user.getUsername()) == null) {
            userService.createUser(user);
        }
    }

    @Test
    void testLogin_withBadCredentials_hasError(){
        loginPage.login("somebodyelse", "anotherpassword");

        assertNotNull(loginPage.getErrorMessage());
        assertEquals(loginPage.getErrorMessage().getText(), "Invalid username or password");
    }

    @Test
    void testLogin_withCredentials_isOk(){
        loginPage.login("johndoe", "password");
        assertEquals(driver.getCurrentUrl(), "http://localhost:" + port + "/home");
    }
}
