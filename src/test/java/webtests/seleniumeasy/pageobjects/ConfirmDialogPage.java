package webtests.seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://the-internet.herokuapp.com/javascript_alerts")
public class ConfirmDialogPage extends PageObject {

    public static final By LAUNCH_CONFIRM = By.cssSelector("button[onclick='jsConfirm()']");

    public void openConfirm() {
        $(LAUNCH_CONFIRM).waitUntilEnabled().click();
    }
}
