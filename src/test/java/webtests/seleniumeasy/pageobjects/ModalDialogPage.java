package webtests.seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://www.seleniumeasy.com/test/bootstrap-modal-demo.html")
public class ModalDialogPage extends SeleniumEasyForm{

    private static final By LAUNCH_MODAL_BUTTON = By.linkText("Launch modal");
    private static final By SAVE_CHANGES_BUTTON = By.linkText("Save changes");

    public void openModal() {
        $(LAUNCH_MODAL_BUTTON).click();
    }

    //return the state of the save changes button to be able to check if it's present, visible...
    public WebElementState saveChangesButton() {
        return $(SAVE_CHANGES_BUTTON);
    }

    public void saveChanges() {
        $(SAVE_CHANGES_BUTTON).click();
    }
}
