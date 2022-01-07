package webtests.seleniumeasy.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class NavigateActions extends UIInteractionSteps {

    @Step
    public void to(FormPage formPage) {
        openPageNamed(formPage.name());
    }

    @Step
    public void toTheSingleInputFieldForm() {
//        openUrl("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        openPageNamed("singleInputFieldForm");
    }

    @Step
    public void toTheTwoInputFieldForm() {
        openPageNamed("twoInputFieldForm");
    }

    @Step
    public void toTheSingleCheckboxForm() {
        openPageNamed("singleCheckboxForm");
    }
}
