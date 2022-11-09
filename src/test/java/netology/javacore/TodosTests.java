package netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TodosTests {
    Todos todos;

    @BeforeEach
    void setUp() {
        todos = new Todos();
        todos.getTasks().add("Уборка");
        todos.getTasks().add("Стирка");
        todos.getTasks().add("Погладить бельё");
        todos.getTasks().add("Покормить кота");
        todos.getTasks().add("Проверить дневник");
        todos.getTasks().add("Дописать курсовую");
    }

    @DisplayName("Testing addition of a new task")
    @ParameterizedTest
    @ValueSource(strings = { "Купить продукты", " ", "Oплатить счета" })
    void addTask(String task) {
        Assertions.assertEquals(6,todos.getTasks().size());
        todos.addTask(task);
        Assertions.assertEquals(7,todos.getTasks().size());
    }

    @DisplayName("Testing addition of a new task when list is full")
    @ParameterizedTest
    @ValueSource(strings = { "Купить продукты", " ", "Oплатить счета" })
    void addTaskWhenListIsFull(String task) {
        todos.getTasks().add("Выгулять собаку");
        Assertions.assertEquals(7,todos.getTasks().size());

        todos.addTask(task);
        Assertions.assertEquals(7,todos.getTasks().size());
    }

    @DisplayName("Testing addition of the existing task")
    @ParameterizedTest
    @ValueSource(strings = { "Стирка", "Покормить кота", "Дописать курсовую", "Уборка" })
    void addTaskExisting(String task) {
        Assertions.assertEquals(6,todos.getTasks().size());

        todos.addTask(task);
        Assertions.assertEquals(6,todos.getTasks().size());
    }

    @DisplayName("Testing removing of the existing task")
    @ParameterizedTest
    @ValueSource(strings = { "Стирка", "Покормить кота", "Дописать курсовую", "Уборка" })
    void removeTask(String task) {
        Assertions.assertEquals(6,todos.getTasks().size());

        todos.removeTask(task);
        Assertions.assertEquals(5,todos.getTasks().size());
    }

    @DisplayName("Testing removing of the non-existing task")
    @ParameterizedTest
    @ValueSource(strings = { "Купить продукты", " ", "Oплатить счета" })
    void removeTaskWhenNoTask(String task) {
        Assertions.assertEquals(6,todos.getTasks().size());

        todos.removeTask(task);
        Assertions.assertEquals(6,todos.getTasks().size());
    }

    @DisplayName("Testing getting of a sorted list of all tasks")
    @Test
    void getAllTasks() {
        String expected =
                "Дописать курсовую Погладить бельё Покормить кота Проверить дневник Стирка Уборка";
        Assertions.assertEquals(expected, todos.getAllTasks());
    }








}
