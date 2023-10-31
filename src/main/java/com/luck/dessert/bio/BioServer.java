package com.luck.dessert.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangtingting
 * @date 2023/10/17
 */
public class BioServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Bio server is started，waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Bio server connects client success！");

                // 创建一个新的线程来处理客户端请求
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String request;
                while ((request = reader.readLine()) != null) {
                    System.out.println("Bio server accept input：" + request);

                    String response = processRequest(request);

                    writer.println(response);
                    writer.flush();
                }

                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processRequest(String request) {
            return "Bio server process success";
        }
    }
}
