package seleniumeasy;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.actions.FormPage;
import seleniumeasy.actions.NavigateActions;
import seleniumeasy.pageobjects.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a series of exercises designed to explore how to use
 * Serenity BDD to test various kinds of HTML elements
 */
@RunWith(SerenityRunner.class)
public class WhenInteractingWithInputForms {

    @Managed()//(driver = "chrome", uniqueSession = true)
    WebDriver driver;

    @Steps
    NavigateActions navigate;
    /**
     * ***** DEMO WEB PAGE NOT WORKING ****************
     * Basic form fields:
     * Enter a message and check that the message is correctly displayed
     * https://www.seleniumeasy.com/test/basic-first-form-demo.html
     */
    SingleInputFieldForm singleInputFieldForm;

    @Test
    public void basicForms() {
//        singleInputFieldForm.open();
        navigate.toTheSingleInputFieldForm();

        String inputMessage = "Hi there!";
        singleInputFieldForm.enterMessage(inputMessage);

        singleInputFieldForm.showMessage();

        assertThat(singleInputFieldForm.displayedMessage()).isEqualTo(inputMessage);

    }

    /**
     * ***** DEMO WEB PAGE NOT WORKING ****************
     * Basic form fields (part 2)
     * Enter two values and calculate the result
     * https://www.seleniumeasy.com/test/basic-first-form-demo.html
     */
    TwoInputFieldForm twoInputFieldForm;

    @Test
    public void basicFormsWithMultipleFields() {
//        twoInputFieldForm.open();
        navigate.toTheTwoInputFieldForm();

        twoInputFieldForm.enterA("2");
        twoInputFieldForm.enterB("3");

        twoInputFieldForm.getTotal();

        assertThat(twoInputFieldForm.displayedTotal()).isEqualTo("5");
    }

    /**
     * Checkboxes
     * Check that one checkbox is correctly set
     * https://the-internet.herokuapp.com/checkboxes
     */
    SingleCheckboxForm singleCheckboxForm;

    @Test
    public void singleCheckbox() {
//        singleCheckboxForm.open();
        navigate.toTheSingleCheckboxForm();

        if (!singleCheckboxForm.selectionForCheckboxAtPosition("1")) {
            singleCheckboxForm.setCheckboxAtPosition("1");
        }

        assertThat(singleCheckboxForm.selectionForCheckboxAtPosition("1")).isTrue();

    }

    /**
     * Checkboxes
     * Check that all checkboxes in the form are correctly set
     * https://the-internet.herokuapp.com/checkboxes
     */

    MultipleCheckboxForm multipleCheckboxForm;

    @Test
    public void multipleCheckboxes() {
        multipleCheckboxForm.open();

        multipleCheckboxForm.selectAllCheckboxes();

        List<WebElementFacade> checkboxes = multipleCheckboxForm.getAllCheckboxes();
        Serenity.reportThat("Check that all checkboxes are selected",
                () ->
                        assertThat(checkboxes).allMatch(
                                checkbox -> checkbox.isSelected()
                        )
        );
    }

    /**
     * Radio buttons
     * Check that a message appears when you click the radio button
     * https://demoqa.com/radio-button
     */
    RadioButtonsForm radioButtonsForm;

    @Test
    public void radioButtons() {
//        radioButtonsForm.open();
        navigate.to(FormPage.RadioButtonsForm);

        String option = "Impressive";

        radioButtonsForm.selectOption(option);

        assertThat(radioButtonsForm.displayedMessageFor(option)).isEqualTo("You have selected " + option);
    }

    /**
     * ***** DEMO WEB PAGE NOT WORKING ****************
     * Radio buttons
     * Check that a message appears when you click the radio button
     * https://www.seleniumeasy.com/test/basic-radiobutton-demo.html"
     */
    MultipleRadioButtonForm multipleRadioButtonForm;

    @Test
    public void multipleRadioButtons() {

//        multipleRadioButtonForm.open();
        navigate.to(FormPage.MultipleRadioButtonForm);

        multipleRadioButtonForm.selectGender("Female");
        multipleRadioButtonForm.selectAgeGroup("15 - 50");
        multipleRadioButtonForm.getValues();

        assertThat(multipleCheckboxForm.getResult())
                .contains("Sex : Female")
                .contains("Age Group: 15 - 50");

    }

    /**
     * Dropdown lists
     * https://www.techlistic.com/p/selenium-practice-form.html
     */
    SelectListForm selectListForm;

    @Test
    public void selectList() {
        selectListForm.open();

        //check that the selected value is the default value
        String defaultContinent = "Asia";
        assertThat(selectListForm.selectedContinent()).isEqualTo(defaultContinent);

        List<String> allContinents = selectListForm.getAllContinents();
        String expectedContinent = allContinents.get(3);
        selectListForm.selectContinent(3);

        assertThat(selectListForm.selectedContinent()).isEqualTo(expectedContinent);
    }

    /**
     * Multi-Select Dropdown lists
     * https://www.techlistic.com/p/selenium-practice-form.html
     */

    MultiSelectListForm multiSelectListForm;

    @Test
    public void multiSelectList() {

        multiSelectListForm.open();

        //check that nothing is selected
        assertThat(multiSelectListForm.selectedCommands()).isEmpty();

        List<String> availableCommands = multiSelectListForm.getAllCommands();

        //select first and last commands
        multiSelectListForm.selectCommands(0, availableCommands.size() - 1);

        assertThat(multiSelectListForm.selectedCommands()).containsExactly(
                availableCommands.get(0),
                availableCommands.get(availableCommands.size() - 1));
    }

    /**
     * Hover over one image and get caption
     * "https://the-internet.herokuapp.com/hovers"
     */
    HoverPage hoverPage;

    @Test
    public void hoverOverOneFigure() {
//        hoverPage.open();
        navigate.to(FormPage.HoverPage);

        hoverPage.hoverOverFigure(1);
        hoverPage.captionForFigure(1).shouldBeVisible();
        hoverPage.captionForFigure(1).shouldContainText("user1");
    }

    /**
     * Hover over all images in the page and get caption
     * "https://the-internet.herokuapp.com/hovers"
     */
    @Test
    public void hoverOverAllFigures() {
//        hoverPage.open();
        navigate.to(FormPage.HoverPage);

        int numberOfFigures = hoverPage.getNumberOfFigures();
        for (int i = 1; i <= numberOfFigures; i++) {
            hoverPage.hoverOverFigure(i);
            hoverPage.captionForFigure(i).shouldBeVisible();
            hoverPage.captionForFigure(i).shouldContainText("user" + i);
        }
    }

    DragAndDropPage dragAndDropPage;

    /**
     * ***** DRAG-N-DROP ACTION NOT WORKING ****************
     * Drag and drop one image into another
     * "https://the-internet.herokuapp.com/drag_and_drop"
     */
    @Test
    public void dragAndDropFigures() {
//        dragAndDropPage.open();
        navigate.to(FormPage.DragAndDropPage);

        assertThat(dragAndDropPage.headerForFigure(1)).isEqualTo("A");

        dragAndDropPage.moveOneFigureIntoAnother(1, 2);

        assertThat(dragAndDropPage.headerForFigure(1)).isEqualTo("B");

    }
}
