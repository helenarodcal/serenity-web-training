package webtests.lambdatest;

import net.serenitybdd.core.webdriver.enhancers.BeforeAWebdriverScenario;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.SupportedWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BeforeALambdaTestScenario implements BeforeAWebdriverScenario {
    @Override
    public DesiredCapabilities apply(EnvironmentVariables environmentVariables,
                                     SupportedWebDriver supportedWebDriver,
                                     TestOutcome testOutcome,
                                     DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("name", testOutcome.getCompleteName());
        return desiredCapabilities;
    }
}
