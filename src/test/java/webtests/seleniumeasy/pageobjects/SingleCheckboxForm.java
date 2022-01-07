package webtests.seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://the-internet.herokuapp.com/checkboxes")
public class SingleCheckboxForm extends PageObject {
    private static final String CHECKBOX_ELEMENT = "//input[@type='checkbox'][{0}]";

    public void setCheckboxAtPosition(String position) {
        $(CHECKBOX_ELEMENT, position).click();
    }

    public boolean selectionForCheckboxAtPosition(String position) {
        return $(CHECKBOX_ELEMENT, position).isSelected();
    }
}
