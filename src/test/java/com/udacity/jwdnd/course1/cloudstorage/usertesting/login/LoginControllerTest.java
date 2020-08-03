package com.udacity.jwdnd.course1.cloudstorage.usertesting.login;

import com.udacity.jwdnd.course1.cloudstorage.usertesting.AbstractBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginControllerTest extends AbstractBaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);

        createTestUser();
    }

    @Test
    void testLogin_withBadCredentials_hasError(){
        loginPage.login("somebodyelse", "anotherpassword");

        assertTrue(loginPage.getErrorMessage().isDisplayed());
        assertEquals(loginPage.getErrorMessage().getText(), "Invalid username or password");
    }

    @Test
    void testLogin_withCredentials_isOk(){
        loginPage.login("johndoe", "password");
        assertEquals(driver.getCurrentUrl(), "http://localhost:" + port + "/home");
    }
}
