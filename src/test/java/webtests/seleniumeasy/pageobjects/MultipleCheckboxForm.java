package webtests.seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import java.util.ArrayList;
import java.util.List;

@DefaultUrl("https://the-internet.herokuapp.com/checkboxes")
public class MultipleCheckboxForm extends PageObject {
    private static final String CHECKBOXES = "input[type='checkbox']";

    public List<WebElementFacade> getAllCheckboxes() {
        List<WebElementFacade> checkboxes = new ArrayList<>();
//        findAll(CHECKBOXES).forEach(
//              checkbox -> checkboxes.add(checkbox)
//        );
        findAll(CHECKBOXES).addAll(checkboxes);
        return checkboxes;
    }

    public void selectAllCheckboxes() {
        findAll(CHECKBOXES).forEach(
                checkbox -> {
                    if(!checkbox.isSelected()) {checkbox.click();}
                }
        );
    }

    public String getResult() {
        return $(".groupradiobutton").getText();
    }
}
