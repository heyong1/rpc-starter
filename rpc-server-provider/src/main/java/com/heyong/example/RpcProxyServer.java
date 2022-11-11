package com.heyong.example;

import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 21:19
 */
public class RpcProxyServer {

    public void publisher(Object service,int port){
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                executorService.submit(new ProcessorHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
