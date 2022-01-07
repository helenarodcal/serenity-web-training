package webtests.seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@DefaultUrl("https://the-internet.herokuapp.com/dynamic_loading/2")
public class DynamicDataPage extends PageObject {

    private final static By START_BUTTON = By.xpath("//button[.='Start']");
    private static final By APPEARING_MESSAGE = By.cssSelector("#finish");
    private static final By LOADING_MESSAGE = By.cssSelector("#loading");


    public void start() {
        $(START_BUTTON).waitUntilClickable().click();
    }

    public void waitUntilLoadingFinishes() {
        withTimeoutOf(Duration.ofSeconds(20))
                .waitFor(
                        invisibilityOf($(LOADING_MESSAGE))
                );

//        Example when we have an object changing text
//        withTimeoutOf(Duration.ofSeconds(10))
//                .waitFor(
//                        invisibilityOfElementWithText(LOADING_MESSAGE, "Loading...")
//                );
    }

    public WebElementState loadingMessage() {
        return $(LOADING_MESSAGE);
    }

    public WebElementState appearingMessage() {
        return $(APPEARING_MESSAGE);
    }
}
