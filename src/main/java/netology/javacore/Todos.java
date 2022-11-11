package netology.javacore;

import java.util.*;

public class Todos {
    private List<String> tasks;
    private final int CAPACITY = 7;

    public Todos() {
        tasks = new ArrayList<>(CAPACITY);
    }

    public boolean addTask(String task) {
        if (tasks.size() < CAPACITY) {
            if (!tasks.contains(task)) {
                tasks.add(task);
                return true;
            }
        }
        return false;
    }

    public boolean removeTask(String task) {
        return tasks.remove(task);
    }

    public List<String> getTasks() {
        return tasks;
    }

    public String getAllTasks() {
        tasks.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        tasks.forEach(string -> {
            sb.append(string);
            sb.append(" ");
        });
        return sb.toString().trim();
    }
}
