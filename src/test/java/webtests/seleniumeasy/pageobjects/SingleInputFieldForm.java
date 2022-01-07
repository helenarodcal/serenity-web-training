package webtests.seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.seleniumeasy.com/test/basic-first-form-demo.html")
public class SingleInputFieldForm extends SeleniumEasyForm {
    public void enterMessage(String inputMessage) {
//        find("#user-message").sendKeys(inputMessage);
        $("#user-message").type(inputMessage);
    }

    public void showMessage() {
//        $("//button[.='Show Message']").click();
        $(FormButton.withLabel("Show Message")).click();
    }

    public String displayedMessage() {
        return $("#display").getText();
    }
}
