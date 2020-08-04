package com.udacity.jwdnd.course1.cloudstorage.usertesting.notes;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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

class NotesControllerTest extends AbstractBaseTest {

    private NotesPage notesPage;

    @Autowired
    private NoteMapper noteMapper;

    @BeforeEach
    public void beforeEach() throws InterruptedException {
        driver.get("http://localhost:" + port + "/login");
        LoginPage loginPage = new LoginPage(driver);

        createTestUser();
        loginPage.login("johndoe", "password");
        driver.get("http://localhost:" + port + "/home");
        notesPage = new NotesPage(driver);
        notesPage.getNavTabButton().click();
        noteMapper.deleteAll();
    }

    @Test
    void testCreate() {
        Note note = getTestNote();
        doCreateNote(note);

        List<WebElement> credentialRows = driver.findElement(By.id("notesTable")).findElements(By.cssSelector("tbody tr"));
        Assertions.assertTrue(credentialRows.size() > 0);

        WebElement credentialRow = credentialRows.get(0);
        Assertions.assertEquals(credentialRow.findElement(By.cssSelector("th")).getAttribute("innerText"), note.getNoteTitle());
    }

    private Note getTestNote() {
        return Note.builder()
                    .noteTitle("title")
                    .noteDescription("description")
                    .build();
    }

    @Test
    void testEdit(){
        Note note = getTestNote();
        doCreateNote(note);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#notesTable > tbody > tr > td:nth-child(1) > button"))
        ).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        notesPage.getInputNoteTitle().clear();
        notesPage.getInputNoteTitle().sendKeys("NewTitle");
        notesPage.getSubmitButton().submit();

        String url = driver.findElement(By.cssSelector("#notesTable > tbody > tr > th"))
                .getAttribute("innerText");

        Assertions.assertEquals("NewTitle", url);
    }

    @Test
    void testDelete(){
        Note note = getTestNote();
        doCreateNote(note);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#notesTable > tbody > tr > td:nth-child(1) > form > button"))
        ).click();

        List<WebElement> credentialRows = driver.findElement(By.id("notesTable")).findElements(By.cssSelector("tbody tr"));
        Assertions.assertEquals(0, credentialRows.size());
    }

    private void doCreateNote(Note note) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-new"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description"))).sendKeys(note.getNoteDescription());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title"))).sendKeys(note.getNoteTitle());
        notesPage.getSubmitButton().submit();
    }
}
