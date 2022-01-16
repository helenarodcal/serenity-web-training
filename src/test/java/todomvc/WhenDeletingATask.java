package todomvc;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import todomvc.actions.TodoListActions;
import todomvc.domain.StatusList;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhenDeletingATask{
    @Managed
    WebDriver driver;

    @BeforeEach
    void openWeb() {
        todoList.openPageNamed("home");
    }

    @Steps
    TodoListActions todoList;

    @Test
    void deletedItemsShouldDisappearFromTheList() {
        // Add "Feed the cat" and "Walk the dog" to the list
        List<String> newTasks = Arrays.asList("Feed the cat", "Walk the dog");
        todoList.addTasks(newTasks);

        // Delete "Feed the cat"
        todoList.deleteTasks(newTasks.get(0));

        // Filter by "Active"
        todoList.filterBy(StatusList.All); //redundant step just in case

        // Check that only "Walk the dog" appears
        Serenity.reportThat("The number of tasks displayed should be 1",
                () -> assertThat(todoList.tasks().size()).isEqualTo(1)
        );
        Serenity.reportThat("The deleted task should not be displayed",
                () -> assertThat(todoList.tasks()).doesNotContain(newTasks.get(0))
        );
        Serenity.reportThat("The remaining task should be displayed",
                () -> assertThat(todoList.tasks()).containsExactly(newTasks.get(1))
        );
    }
}