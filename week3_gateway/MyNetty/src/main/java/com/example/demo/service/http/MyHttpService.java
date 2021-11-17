package com.example.demo.service.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyHttpService {
    private final int port;

    public MyHttpService(int port){
        this.port = port;
    }

    public void run() throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            try {
                Thread.sleep(1000);
                final Socket socket = serverSocket.accept();
                executor.execute(() -> service(socket));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void service(Socket socket) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "this is a http client demo, port is : " + port;
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(printWriter != null){
                printWriter.close();
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
