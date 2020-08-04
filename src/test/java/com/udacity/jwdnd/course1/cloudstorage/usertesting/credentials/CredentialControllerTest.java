package com.udacity.jwdnd.course1.cloudstorage.usertesting.credentials;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.usertesting.AbstractBaseTest;
import com.udacity.jwdnd.course1.cloudstorage.usertesting.login.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class CredentialControllerTest extends AbstractBaseTest {

    private CredentialPage credentialPage;

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        LoginPage loginPage = new LoginPage(driver);

        createTestUser();
        loginPage.login("johndoe", "password");
        driver.get("http://localhost:" + port + "/home");
        credentialPage = new CredentialPage(driver);
        credentialPage.getNavTabButton().click();
    }

    @Test
    void testCreate() throws InterruptedException {
        Credentials credentials = getTestCredentials();
        doCreateCredentials(credentials);

        List<WebElement> credentialRows = driver.findElement(By.id("credentialTable")).findElements(By.cssSelector("tbody tr"));
        Assertions.assertEquals(credentialRows.size(), 1);

        WebElement credentialRow = credentialRows.get(0);
        Assertions.assertEquals(credentialRow.findElement(By.cssSelector("th")).getAttribute("innerText"), credentials.getUrl());
    }

    private Credentials getTestCredentials() {
        return Credentials.builder()
                    .password("password")
                    .username("username")
                    .url("http://localhost")
                    .build();
    }

    @Test
    void testEdit(){

        Credentials credentials = getTestCredentials();
        doCreateCredentials(credentials);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#credentialTable > tbody > tr > td:nth-child(1) > button"))
        ).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        credentialPage.getInputCredentialUrl().clear();
        credentialPage.getInputCredentialUrl().sendKeys("https://udacity.com");
        credentialPage.getSubmitButton().submit();

        String url = driver.findElement(By.cssSelector("#credentialTable > tbody > tr > th"))
                .getAttribute("innerText");

        Assertions.assertEquals("https://udacity.com", url);
    }

    private void doCreateCredentials(Credentials credentials) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-new"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        credentialPage.createCredentials(credentials);
    }
}
