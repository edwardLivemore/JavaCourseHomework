package com.example.demo;

import com.example.demo.inbound.HttpInboundServer;
import com.example.demo.service.http.MyHttpService;

import java.io.IOException;
import java.util.Arrays;

public class MyNettyApplication {
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort","8888");

        String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            // 开启8801服务
            Thread threadA = new Thread(() -> {
                MyHttpService myHttpService = new MyHttpService(8801);
                try {
                    myHttpService.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadA.start();

            // 开启8802服务
            Thread threadB = new Thread(() -> {
                MyHttpService myHttpServer = new MyHttpService(8802);
                try {
                    myHttpServer.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadB.start();

            // 开启网关服务8888
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
