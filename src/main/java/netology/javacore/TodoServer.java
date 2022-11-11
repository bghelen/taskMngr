package netology.javacore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int PORT;
    protected Todos todos;

    public TodoServer(int port, Todos todos) {
        this.PORT = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Starting server at " + PORT + "...");
            Manager manager = new Manager(todos);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    String json = in.readLine();
                    System.out.println(json);

                    JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
                    String type = jsonObject.get("type").getAsString();

                    if (type.equals("RESTORE")) {
                        manager.restore();
                    } else {
                        String task = jsonObject.get("task").getAsString();
                        manager.execute(type, task);
                    }
                    String output = todos.getAllTasks();
                    out.println(output);
                }
            }
        }
    }
}
