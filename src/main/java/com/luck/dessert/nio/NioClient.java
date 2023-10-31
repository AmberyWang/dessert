package com.luck.dessert.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.UUID;

/**
 * @author wangtingting
 * @date 2023/10/17
 */
public class NioClient {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new NioClientThread());
            thread.start();
        }
    }

    public static class NioClientThread implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
                System.out.println("Nio client connect to Nio server successfully");

                String request = "Hello, this is Nio client request!" + UUID.randomUUID();
                ByteBuffer buffer = ByteBuffer.wrap(request.getBytes());
                socketChannel.write(buffer);

                ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
                int bytesRead = socketChannel.read(responseBuffer);
                if (bytesRead > 0) {
                    responseBuffer.flip();
                    byte[] bytes = new byte[responseBuffer.remaining()];
                    responseBuffer.get(bytes);
                    String response = new String(bytes);
                    System.out.println("Nio client get response：" + response);
                }

                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
