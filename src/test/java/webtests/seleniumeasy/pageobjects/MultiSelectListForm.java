package webtests.seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;

@DefaultUrl("https://www.techlistic.com/p/selenium-practice-form.html")
public class MultiSelectListForm extends PageObject {

    private static final By COMMANDS_LIST = By.cssSelector("#selenium_commands");

    public List<String> selectedCommands() {
        return $(COMMANDS_LIST).getSelectedValues();
    }

    public List<String> getAllCommands() {
        return $(COMMANDS_LIST).getSelectOptions();
    }

    public void selectCommands(int... commands) {
        for(int command : commands) {
            $(COMMANDS_LIST).select().byIndex(command);
        }
    }
}
