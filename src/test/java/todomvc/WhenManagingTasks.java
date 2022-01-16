package todomvc;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;
import todomvc.domain.StatusList;
import todomvc.pageobjects.TodoListPage;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static todomvc.domain.StatusList.*;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class WhenManagingTasks {

    private final List<String> initialTaskList;
    private final StatusList status;
    private final String completedTask;
    private final List<String> displayedTasksList;

    public WhenManagingTasks(List<String> initialTaskList, String completedTask,
                             StatusList status, List<String> displayedTasksList) {
        this.initialTaskList = initialTaskList;
        this.completedTask = completedTask;
        this.status = status;
        this.displayedTasksList = displayedTasksList;
    }

    @Managed
    WebDriver driver;

    @TestData(columnNames = "Tasks created, Completed task, Filter, Tasks displayed")
    public static Collection<Object[]> testData() {
        return asList(
                new Object[][]{
                        {asList("Feed the cat", "Walk the dog"), "Feed the cat", Completed, singletonList("Feed the cat")},
                        {asList("Feed the cat", "Walk the dog", "Buy milk"), "Feed the cat", Active, asList("Walk the dog", "Buy milk")},
                        {asList("Feed the cat", "Walk the dog"), "Feed the cat", All, asList("Feed the cat", "Walk the dog")}
                }
        );
    }

    TodoListPage todoListPage;

    @Before
    public void openWeb() {
        todoListPage.open();
    }

    @Qualifier
    public String qualifier() {
        return "The user add the tasks " + initialTaskList.toString() + ". Then completes task "
                + completedTask + " and filters by " + status + ". The list displayed should be correct";
    }

    @Steps
    TodoListActions todoList;

    @Test
    @Title("Different actions on tasks and task list are executed (create, complete, delete, filter")
    public void whenManagingTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        todoList.addTasks(initialTaskList);

        // Complete "Feed the cat"
        todoList.completeTask(completedTask);

        // Filter by "Active"
        todoList.filterBy(status);

        // Check that only "Walk the dog" appears
        Serenity.reportThat("The number of tasks displayed should be " + displayedTasksList.size(),
                () -> assertThat(todoList.tasks().size()).isEqualTo(displayedTasksList.size())
        );
        Serenity.reportThat("The task list displayed should be correct",
                () -> assertThat(todoList.tasks()).containsExactlyElementsOf(displayedTasksList)
        );

    }

}
