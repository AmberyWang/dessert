package com.luck.dessert.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

/**
 * @author wangtingting
 * @date 2023/10/17
 */
public class BioClient {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new BioClientThread());
            thread.start();
        }
    }

    static class BioClientThread implements Runnable {
        @Override
        public void run() {
            try {
                // 连接服务器
                Socket socket = new Socket("127.0.0.1", 8080);
                System.out.println("Bio client connect to Bio server successfully");

                // 获取输入输出流
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                // 接收响应
                String response = reader.readLine();
                System.out.println("Bio client get response：" + response);

                // 发送请求
                writer.println("Hello, this is Bio client request!" + UUID.randomUUID());
                writer.flush();

                // 关闭连接
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
