package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTests {

    Todos todos;
    String[] task = {"Пробежка", "Акробатика", "Учёба", "Работа", "Сон", "Хобби", "Чтение", "Тренировка"};
    int maxSize = 7;

    @Test
    public void todoAddTest() {
        todos = new Todos();
        String before = todos.getAllTasks();
        boolean isConsistBefore = before.indexOf(task[0]) != -1;
        todos.addTask(task[0]);
        String after = todos.getAllTasks();
        boolean isConsistAfter = after.indexOf(task[0]) != -1;
        Assertions.assertTrue(!isConsistBefore && isConsistAfter);
    }

    @Test
    public void todoRemoveTest() {
        todos = new Todos();
        todos.addTask(task[0]);
        String before = todos.getAllTasks();
        boolean isConsistBefore = before.indexOf(task[0]) != -1;
        todos.removeTask(task[0]);
        String after = todos.getAllTasks();
        boolean isConsistAfter = after.indexOf(task[0]) != -1;
        Assertions.assertTrue(isConsistBefore && !isConsistAfter);
    }

    @Test
    public void todoMaxLimitTest() {
        todos = new Todos();
        Todos.MAXTODOS = 7;
        for (int i = 0; i < task.length; i++) {
            todos.addTask(task[i]);
        }
        String[] after = todos.getAllTasks().split(" ");
        Assertions.assertTrue(after.length <= maxSize);
    }
}
