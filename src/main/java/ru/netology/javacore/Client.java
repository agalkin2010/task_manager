package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client {

    final static int PORT = 8989;

    public static void main(String[] args) {

        try (Socket client = new Socket("localhost", PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);) {

            TodoReq todo = new TodoReq("ADD", "Учёба");
            //Reqest todo = new Reqest("RESTORE");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            out.println(gson.toJson(todo));

            System.out.println("FromServer> " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
