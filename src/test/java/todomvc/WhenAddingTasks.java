package todomvc;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenAddingTasks {

    @Managed
    WebDriver driver;

    @Steps
    TodoListActions todoList;

    @BeforeEach
    public void openWeb() {
        todoList.openPageNamed("home");
    }

    @Test
    public void addingASingleTask() {
        // Add "Feed The Cat" to the list
        todoList.addTask("Feed the cat");

        // Check that "Feed The Cat" appears in the list
        Serenity.reportThat("The number of tasks displayed should be 1",
                () -> assertThat(todoList.tasks().size()).isEqualTo(1)
        );
        Serenity.reportThat("The task displayed should be correct",
                () -> assertThat(todoList.tasks()).containsExactly("Feed the cat")
        );
    }

    @Test
    public void addingMultipleTasks() {
        // Add "Feed The Cat" and "Walk the dog" to the list
        List<String> newTasks = Arrays.asList("Feed the cat", "Walk the dog");
        todoList.addTasks(newTasks);
        // Check that they all appear in the list
        Serenity.reportThat("The number of tasks displayed should be " + newTasks.size(),
                () -> assertThat(todoList.tasks().size()).isEqualTo(newTasks.size())
        );
        Serenity.reportThat("The task displayed should be correct",
                () -> assertThat(todoList.tasks()).containsExactlyElementsOf(newTasks)
        );
    }

}
