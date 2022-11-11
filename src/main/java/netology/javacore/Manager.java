package netology.javacore;

import java.util.ArrayDeque;
import java.util.Deque;

public class Manager {

    protected Deque<Command> stack;
    protected Todos todos;

    public Manager(Todos todos) {
        stack = new ArrayDeque<>();
        this.todos = todos;
    }

    public void log(String type, String task) {
        stack.push(new Command(type, task));
    }

    public void execute(String type, String task) {
        switch (type) {
            case "ADD":
                if (todos.addTask(task)) {
                    log(type, task);
                }
                break;
            case "REMOVE":
                if (todos.removeTask(task)) {
                    log(type, task);
                }
                break;
            default:
                System.out.println("Unexpected value: " + type);
                break;
        }
    }

    public void restore() {
        if (!stack.isEmpty()) {
            Command lastCommand = stack.poll();
            if (lastCommand.getType().equals("ADD")) {
                execute("REMOVE", lastCommand.getTask());
            } else {
                execute("ADD", lastCommand.getTask());
            }
        }
    }
}
