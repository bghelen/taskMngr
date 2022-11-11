package netology.javacore;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "localhost";
    public static JSONObject jsonObj = new JSONObject();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t1 - добавить задачу\n\t2 - удалить задачу\n");
        sb.append("\t3 - отменить последнюю операцию (в этом случае название задачи вводить не нужно)\n");
        sb.append("\nВведите номер операции");

        int operation;
        String task = "";
        System.out.println(sb);
        String input = scanner.nextLine().trim();
        try {
            operation = Integer.parseInt(input);
            if (operation == 1 || operation == 2) {
                System.out.println("Введите название задачи");
                task = scanner.nextLine().trim();
            }

            switch (operation) {
                case 1:
                    jsonObj.put("type", "ADD");
                    jsonObj.put("task", task);
                    break;
                case 2:
                    jsonObj.put("type", "REMOVE");
                    jsonObj.put("task", task);
                    break;
                case 3:
                    jsonObj.put("type", "RESTORE");
                    break;
                default:
                    System.out.println("Проверьте номер операции");
            }
        } catch (NumberFormatException e) {
            System.out.println("Следует вводить только числа!");
        }
        sendMessage();
    }

    public static void sendMessage() {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println(jsonObj.toJSONString());

            String response = in.readLine();
            System.out.println("Ваш список задач: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
