package webtests.serenityswag.authentication;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webtests.serenityswag.inventory.InventoryPage;

import static org.assertj.core.api.Assertions.assertThat;
import static webtests.serenityswag.authentication.User.STANDARD_USER;

@RunWith(SerenityRunner.class)
public class WhenLoggingOn {

    @Managed
    WebDriver driver;

    @Steps
    LoginActions login;

    InventoryPage inventoryPage;

    @Test
    public void usersCanLogOnViaTheHomePage() {

        login.as(STANDARD_USER);

        //include arbitrary messages in the report
        Serenity.recordReportData()
                .withTitle("User credentials")
                .andContents("User: " + STANDARD_USER);

        // Should see product catalog
                //include assertion in the report
        Serenity.reportThat("The inventory page should be displayed with the correct title",
                () -> assertThat(inventoryPage.getHeading()).isEqualToIgnoringCase("Products")
        );
    }
}
