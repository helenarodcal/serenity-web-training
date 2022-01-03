package seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;

@DefaultUrl("https://www.techlistic.com/p/selenium-practice-form.html")
public class SelectListForm extends PageObject {

    private static final By CONTINENT_LIST = By.cssSelector("#continents");

    public String selectedContinent() {
        return $(CONTINENT_LIST).getSelectedValue();
    }

    public List<String> getAllContinents() {
        return $(CONTINENT_LIST).getSelectOptions();
    }

    public void selectContinent(int continent) {
        $(CONTINENT_LIST).waitUntilPresent()
                .select().byIndex(continent);
    }
}
