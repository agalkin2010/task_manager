package ru.netology.javacore;

import java.util.*;

public class Todos {
    List<String> todos = new ArrayList<>();
    final int MAXTODOS = 7;

    public void addTask(String task) {
        if (todos.size() < MAXTODOS) {
            todos.add(task);
        }
    }

    public void removeTask(String task) {
        if (todos.indexOf(task) != -1) {
            todos.remove(todos.indexOf(task));
        }
    }

    public String getAllTasks() {
        Optional<String> optStr = todos.stream()
                .sorted(Comparator.reverseOrder())
                .reduce((value, combValue) -> combValue + " " + value);
        return (optStr.isPresent()) ? optStr.get() : "";
    }

}
