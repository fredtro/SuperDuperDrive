package com.udacity.jwdnd.course1.cloudstorage.usertesting.notes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NotesPage {

    @FindBy(id = "note-title")
    private WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    private WebElement inputNoteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement submitButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navTabButton;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNote(Note note) {
        inputNoteDescription.sendKeys(note.getNoteDescription());
        inputNoteTitle.sendKeys(note.getNoteTitle());
    }
}
