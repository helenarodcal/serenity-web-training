package todomvc;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;
import todomvc.domain.StatusList;
import todomvc.pageobjects.TodoListPage;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenCompletingATask {
    @Managed
    WebDriver driver;

    @Before
    public void openWeb() {
        todoList.openPageNamed("home");
    }

    @Steps
    TodoListActions todoList;

    @Test
    public void activeTasksShouldNotShowCompletedTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        List<String> newTasks = Arrays.asList("Feed the cat", "Walk the dog");
        todoList.addTasks(newTasks);

        // Complete "Feed the cat"
        todoList.completeTask(newTasks.get(0));

        // Filter by "Active"
        todoList.filterBy(StatusList.Active);

        // Check that only "Walk the dog" appears
        Serenity.reportThat("The number of tasks displayed should be 1",
                () -> assertThat(todoList.tasks().size()).isEqualTo(1)
        );
        Serenity.reportThat("The completed task should not be displayed",
                () -> assertThat(todoList.tasks()).doesNotContain(newTasks.get(0))
        );
        Serenity.reportThat("The remaining task should be displayed",
                () -> assertThat(todoList.tasks()).containsExactly(newTasks.get(1))
        );
    }

    @Test
    public void completedTasksShouldNotShowActiveTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        List<String> newTasks = Arrays.asList("Feed the cat", "Walk the dog");
        todoList.addTasks(newTasks);

        // Complete "Feed the cat"
        todoList.completeTask(newTasks.get(0));

        // Filter by "Completed"
        todoList.filterBy(StatusList.Completed);

        // Check that only "Feed the cat" appears
        Serenity.reportThat("The number of tasks displayed should be 1",
                () -> assertThat(todoList.tasks().size()).isEqualTo(1)
        );
        Serenity.reportThat("The active task should not be displayed",
                () -> assertThat(todoList.tasks()).doesNotContain(newTasks.get(1))
        );
        Serenity.reportThat("The completed task should be displayed",
                () -> assertThat(todoList.tasks()).containsExactly(newTasks.get(0))
        );

    }
}
