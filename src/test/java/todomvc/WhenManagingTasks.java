package todomvc;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;
import todomvc.domain.StatusList;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

class WhenManagingTasks {

    @Managed
    WebDriver driver;

    @Steps
    TodoListActions todoList;

    @ParameterizedTest(name = "Should display all {1} tasks")
    @CsvSource(
            {"Feed the cat; Walk the dog, Feed the cat, Active, Walk the dog",
                    "Feed the cat; Walk the dog, Feed the cat, Completed, Feed the cat",
                    "Feed the cat; Walk the dog, Feed the cat, All, Feed the cat; Walk the dog"}
    )
    void whenManagingTasks(String initialTaskList, String completedTask,
                           String status, String displayedTasksList) {
        
        todoList.openPageNamed("home");
        // Add "Feed the cat" and "Walk the dog" to the list
        todoList.addTasks(listFrom(initialTaskList));

        // Complete "Feed the cat"
        todoList.completeTask(completedTask);

        // Filter by "Active"
        todoList.filterBy(StatusList.valueOf(status));

        // Check that only "Walk the dog" appears
        Serenity.reportThat("The number of tasks displayed should be " + listFrom(displayedTasksList).size(),
                () -> assertThat(todoList.tasks().size()).isEqualTo(listFrom(displayedTasksList).size())
        );
        Serenity.reportThat("The task list displayed should be correct",
                () -> assertThat(todoList.tasks()).containsExactlyElementsOf(listFrom(displayedTasksList))
        );

        driver.quit();
    }

    private List<String> listFrom(String stringParameter) {
        return stream(stringParameter.split(";")).map(String::trim).collect(Collectors.toList());
    }

}
