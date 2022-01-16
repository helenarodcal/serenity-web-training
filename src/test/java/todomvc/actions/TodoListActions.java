package todomvc.actions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import todomvc.domain.StatusList;
import todomvc.pageobjects.TodoListPage;

import java.util.List;

import static todomvc.pageobjects.TodoListPage.*;

public class TodoListActions extends UIInteractionSteps {
    @Step("The user adds the task: {0}")
    public void addTask(String newTask) {
        find(INPUT_TASK).typeAndEnter(newTask);
    }

    public List<String> tasks() {
        return findAll(TASK_LIST).textContents();
    }

    public void addTasks(List<String> newTasks) {
        newTasks.forEach(
                this::addTask
        );
    }

    @Step("The user completes the task: {0}")
    public void completeTask(String task) {
        findBy(COMPLETE_TASK_CHECKBOX, task).click();
    }

    @Step("The user filters the list by {0}")
    public void filterBy(StatusList status) {
        findBy(FILTER_BUTTON, status).click();
    }

    @Step("The user deletes the task: {0}")
    public void deleteTasks(String task) {
        WebElementFacade taskItem = findBy(TASK_ITEM,task);
        withAction().moveToElement(taskItem).perform();
        findBy(DELETE_TASK_BUTTON, task).click();
    }
}
