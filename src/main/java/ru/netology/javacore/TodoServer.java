package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TodoServer {
    int port;
    Todos todos;
    List<TodoReq> history = new ArrayList<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
        Todos.MAXTODOS = 7;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket server = new ServerSocket(port);) {
            while (true) {

                try (
                        Socket client = server.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);) {

                    System.out.println("New connection accepted");
                    String line = in.readLine();
                    System.out.println("From client> " + line);

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    Reqest req = gson.fromJson(line, Reqest.class);
                    ;

                    if (req.type.equals("RESTORE")) {
                        if (!history.isEmpty()) {
                            if (history.get(history.size() - 1).type.equals("ADD")) {
                                this.todos.removeTask(history.get(history.size() - 1).task);
                            } else if (history.get(history.size() - 1).type.equals("REMOVE")) {
                                this.todos.addTask(history.get(history.size() - 1).task);
                            }
                            history.remove(history.size() - 1);
                        }
                    } else {
                        TodoReq todo = gson.fromJson(line, TodoReq.class);
                        history.add(todo);
                        if (todo.type.equals("ADD")) {
                            this.todos.addTask(todo.task);
                        } else if (todo.type.equals("REMOVE")) {
                            this.todos.removeTask(todo.task);
                        }
                    }

                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
