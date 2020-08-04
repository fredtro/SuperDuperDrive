package com.udacity.jwdnd.course1.cloudstorage.usertesting.signup;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.usertesting.AbstractBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupControllerTest extends AbstractBaseTest {

    private SignupPage signupPage;

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/signup");
        signupPage = new SignupPage(driver);
    }

    @Test
    void testUserSignup_withCredentials_isOk(){
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("password")
                .build();

        signupPage.signup(user);
        User dbUser = userService.getUser(user.getUsername());

        assertNotNull(dbUser);
        assertTrue(signupPage.getSuccessMessage().isDisplayed());
    }

    @Test
    void testUserSignup_withExistingUsername_hasError(){
        createTestUser();
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("password")
                .build();

        signupPage.signup(user);

        assertTrue(signupPage.getErrorMessage().isDisplayed());
        assertEquals(signupPage.getErrorMessage().getText(), "The username already exists.");
    }
}
