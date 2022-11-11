package com.heyong.example;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 22:26
 */
public class RpcNetTransport {

    private int port;
    private String host;

    public RpcNetTransport(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public Socket newSocket() {
        try {
            Socket socket = new Socket(host,port);
            return socket;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object send(RpcRequest request) {
        Socket socket = newSocket();
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //写哪些信息到服务器
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
