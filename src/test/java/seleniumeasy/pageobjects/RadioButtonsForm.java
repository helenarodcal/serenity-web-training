package seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://demoqa.com/radio-button")
public class RadioButtonsForm extends PageObject {
    public void selectOption(String option) {

        $("//label[.='{0}']", option)
                .waitUntilEnabled()
                .click();

//        inRadioButtonGroup("like").selectByValue(option);
    }

    public String displayedMessageFor(String option) {
        return $("//p[contains(.,'You have selected')]").getText();
    }
}
