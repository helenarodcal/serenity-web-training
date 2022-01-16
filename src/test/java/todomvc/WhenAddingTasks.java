package todomvc;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Different ways of adding tasks")
class WhenAddingTasks {

    @Managed
    WebDriver driver;

    @Steps
    TodoListActions todoList;

    @BeforeEach
    void openWeb() {
        todoList.openPageNamed("home");
    }

    @Nested
    class ToAnEmptyList {
        @DisplayName("Adding just one task")
        @Test
        void addingASingleTask() {
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

        @DisplayName("Adding several tasks")
        @Test
        void addingMultipleTasks() {
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

    @Nested
    class ToAnExistingList {

       @BeforeEach
       void aListWithSomeEntriesAdded() {
           List<String> newTasks = Arrays.asList("Feed the cat", "Walk the dog");
           todoList.addTasks(newTasks);
       }

       @Test
        void theNewItemShouldBeAddedToTheEndOfTheList() {
           todoList.addTask("Buy some milk");
           Serenity.reportThat("The new item should be at the end",
                   () -> assertThat(todoList.tasks()).containsExactly("Feed the cat", "Walk the dog","Buy some milk")
           );
       }
    }

    @AfterEach
    public void closeWeb() {
        todoList.clearList();
    }
}
