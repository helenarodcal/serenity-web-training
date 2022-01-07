package webtests.seleniumeasy;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webtests.seleniumeasy.pageobjects.TwoInputFieldForm;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * *********** DEMO PAGE NOT WORKING *************
 *
 * Parameterized test (DATA DRIVEN)
 */
@RunWith(SerenityParameterizedRunner.class)
@Concurrent
@UseTestDataFrom("testdata/calculations.csv")
public class CalculationsTestDataFromFile {

    private String a;
    private String b;
    private String total;

    @Managed
    WebDriver driver;

    TwoInputFieldForm twoInputFieldForm;

    @Qualifier
    public String qualifier() { return a + " + " + b + " should equal " + total;}

    @Test
    public void shouldCalculateCorrectTotal() {
        twoInputFieldForm.open();

        twoInputFieldForm.enterA(a);
        twoInputFieldForm.enterB(b);
        twoInputFieldForm.getTotal();

        assertThat(twoInputFieldForm.displayedTotal()).isEqualTo(total);
    }
}
