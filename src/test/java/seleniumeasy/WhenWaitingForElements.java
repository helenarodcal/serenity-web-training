package seleniumeasy;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumeasy.pageobjects.AlertMessagePage;
import seleniumeasy.pageobjects.ConfirmDialogPage;
import seleniumeasy.pageobjects.DynamicDataPage;
import seleniumeasy.pageobjects.ModalDialogPage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

@RunWith(SerenityRunner.class)
public class WhenWaitingForElements {

    @Managed(driver = "chrome")
    WebDriver driver;

    ConfirmDialogPage confirmDialogPage;

    /**
     * Open a page, wait for the alert to appear and click on it
     */
    @Test
    public void waitingForAlertDialog() {
        //open page and check no modals are present
        confirmDialogPage.open();
        assertThat(isConfirmPresent()).isFalse();

        //open modal and check it's present
        confirmDialogPage.openConfirm();
        assertThat(isConfirmPresent()).isTrue();

        //close modal and check it's no longer present
        Alert alert = driver.switchTo().alert();
        alert.accept();
        assertThat(isConfirmPresent()).isFalse();

    }

    /**
     * *********** DEMO WEB PAGE NOT WORKING *******************
     * Open a page, wait for a modal to appear and click on it
     */
    ModalDialogPage modalDialogPage;

    @Test
    public void waitingForAModalDialogWithImplicitWaits() {
        /*selenium implicit wait by default is 0
        serenity: to configure implicit wait --> serenity.conf: webdriver section: timeouts: implicitlywait (ms)
        this will slow things down because for the shouldNotBeVisible the test
        will wait the max implicitlywait before moving on */

        //open the page and check that the modal is not visible
        modalDialogPage.open();
        modalDialogPage.saveChangesButton().shouldNotBeVisible();

        //open the modal anc check that it's visible
        modalDialogPage.openModal();
        modalDialogPage.saveChangesButton().shouldBeVisible();

        //save the changes and check that the modal is no longer visible
        modalDialogPage.saveChanges();
        modalDialogPage.saveChangesButton().shouldNotBeVisible();


    }

    AlertMessagePage alertMessagePage;

    @Test
    public void waitingForAMessageToCloseWithFluentWaits() {
        //Serenity waitFor and selenium ExpectedConditions methods
        // use fluent waits --> default is 5 seconds
//        serenity: to configure fluent wait for all methods--> serenity.conf: webdriver section: timeouts: fluentwait (ms)
        //to configure different waits in different case: include the withTimeout(Duration.of...()) before the waitFor method

        alertMessagePage.open();

        //check that the message is present when loading page
        alertMessagePage.disappearingMessage()
                .shouldBeVisible()
                .shouldContainText("This text will disappear after 25 seconds");

        //wait until the message disappears
        alertMessagePage.waitForMessageToDisappear();

        //check that the message is not present anymore
        alertMessagePage.disappearingMessage().shouldNotBeVisible();

    }

    DynamicDataPage dynamicDataPage;

    @Test
    public void waitingForElementsToAppear() {
        dynamicDataPage.open();

        dynamicDataPage.start();

        dynamicDataPage.waitUntilLoadingFinishes();

        assertThat(dynamicDataPage.loadingMessage().isVisible()).isFalse();
        assertThat(dynamicDataPage.appearingMessage().isVisible()).isTrue();


    }

    private boolean isConfirmPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
