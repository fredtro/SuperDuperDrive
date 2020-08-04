package com.udacity.jwdnd.course1.cloudstorage.usertesting.credentials;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CredentialPage {

    @FindBy(id = "credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id = "credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id = "credentialSubmit")
    private WebElement submitButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navTabButton;

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createCredentials(Credentials credentials) {
        inputCredentialUsername.sendKeys(credentials.getUsername());
        inputCredentialPassword.sendKeys(credentials.getPassword());
        inputCredentialUrl.sendKeys(credentials.getUrl());
        submitButton.submit();
    }
}
