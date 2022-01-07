package webtests.seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.time.Duration;

@DefaultUrl("https://omayo.blogspot.com/")
public class AlertMessagePage extends SeleniumEasyForm {

    private static final By DISAPPEARING_MESSAGE = By.cssSelector("#deletesuccess");

    public WebElementState disappearingMessage() {
        return $(DISAPPEARING_MESSAGE);
    }

    public void waitForMessageToDisappear() {
        withTimeoutOf(Duration.ofSeconds(25)).waitForElementsToDisappear(DISAPPEARING_MESSAGE);
    }
}
