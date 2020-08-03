package com.udacity.jwdnd.course1.cloudstorage.usertesting;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.usertesting.login.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.usertesting.signup.SignupPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityTest extends AbstractBaseTest{

    @Test
    void testUnauthorizedAccess_shouldRedirect(){
        driver.get("http://localhost:" + port + "/home");

        //user should be reidrected to login
        assertEquals(driver.getCurrentUrl(), "http://localhost:" + port + "/login");
    }

    @Test
    void testSignup_Login_Logout_Userflow(){
        //signup & login
        doSignup();
        doLogin();
        assertEquals(driver.getCurrentUrl(), "http://localhost:" + port + "/home");

        //logout and try to re-access
        WebElement logoutButton = driver.findElement(By.id("logout-button"));
        logoutButton.submit();
        assertEquals(driver.getCurrentUrl(), "http://localhost:" + port + "/login");

        driver.get("http://localhost:" + port + "/home");
        assertEquals(driver.getCurrentUrl(), "http://localhost:" + port + "/login");
    }

    private void doLogin() {
        driver.get("http://localhost:" + port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "password");
    }

    private void doSignup() {
        driver.get("http://localhost:" + port + "/signup");
        SignupPage signupPage = new SignupPage(driver);

        User user = User.builder()
                .firstName("test")
                .lastName("user")
                .username("testuser")
                .password("password")
                .build();

        signupPage.signup(user);
    }
}
