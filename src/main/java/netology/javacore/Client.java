package netology.javacore;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "localhost";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<String> tasks = List.of("Пробежка", "Акробатика", "Брейк", "Алгоритмы", "Spring", "Docker", "Bash");
        List<String> commands = List.of("ADD", "REMOVE");
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", commands.get(RANDOM.nextInt(commands.size())));
        jsonObj.put("task", tasks.get(RANDOM.nextInt(tasks.size())));

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(),true)) {
            out.println(jsonObj.toJSONString());

            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
