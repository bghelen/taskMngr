package netology.javacore;

import java.util.*;

public class Todos {
    private Set<String> tasks;
    private static final int CAPACITY = 7;

    public Todos() {
        tasks = new TreeSet<>();
    }

    public boolean addTask(String task) {
        if (tasks.size() < CAPACITY) {
            return tasks.add(task);
        }
        return false;
    }

    public boolean removeTask(String task) {
        return tasks.remove(task);
    }

    public Set<String> getTasks() {
        return tasks;
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        tasks.forEach(string -> {
            sb.append(string);
            sb.append(" ");
        });
        return sb.toString().trim();
    }
}
