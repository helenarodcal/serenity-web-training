package todomvc.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

public class TodoListPage {

    public static final String INPUT_TASK = ".new-todo";
    public static final String TASK_LIST = ".todo-list label";
    public static final String TASK_ITEM = "//label[.='{0}']";
    public static final String COMPLETE_TASK_CHECKBOX = TASK_ITEM + "//preceding-sibling::input[@type='checkbox']";
    public static final String DELETE_TASK_BUTTON = TASK_ITEM + "//following-sibling::button";
    public static final String FILTER_BUTTON = "//a[.='{0}']";

}
