package seleniumeasy.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.interactions.Actions;

@DefaultUrl("https://the-internet.herokuapp.com/drag_and_drop")
public class DragAndDropPage extends PageObject {
    public static final String FIGURE_IMAGE = "(//*[@class='column'])[{0}]";
    public static final String FIGURE_IMAGE_HEADER = ".column:nth-child({0}) header";

    public String headerForFigure(int number) {
        return $(FIGURE_IMAGE_HEADER,number).getText();
    }

    public void moveOneFigureIntoAnother(int from, int to) {
        new Actions(getDriver()).dragAndDrop(
                $(FIGURE_IMAGE, from),
                $(FIGURE_IMAGE, to)
        ).build().perform();
//        withAction().dragAndDrop(
//                $(FIGURE_IMAGE, from),
//                $(FIGURE_IMAGE, to)
//                ).perform();
    }
}
