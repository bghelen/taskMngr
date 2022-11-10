package netology.javacore;

public class Command {
    private String type;
    private String task;

    public Command(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}
