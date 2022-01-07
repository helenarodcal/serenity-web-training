package webtests.seleniumeasy.pageobjects;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("https://the-internet.herokuapp.com/hovers")
public class HoverPage extends PageObject {

    public static final String ALL_FIGURES = "//*[@class='figure']";
    public static final String FIGURE_IMAGE = "(//*[@class='figure'])[{0}]";
    public static final String FIGURE_CAPTION = "(//*[@class='figcaption'])[{0}]";

    public void hoverOverFigure(int number) {
        WebElementFacade figure = $(FIGURE_IMAGE, number);

        //with Selenium
//        new Actions(getDriver()).moveToElement(figure).perform();

        //with Serenity
        withAction().moveToElement(figure).perform();
    }

    public WebElementState captionForFigure(int number) {
        return $(FIGURE_CAPTION, number);
    }

    public int getNumberOfFigures() {
        return $$(ALL_FIGURES).size();
    }
}
