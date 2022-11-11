package com.heyong.example;

import com.sun.javafx.scene.layout.region.BorderImageWidthsSequenceConverter;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 21:23
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service){
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest)inputStream.readObject();
            System.out.println("收到客户端请求：" + rpcRequest.toString());

            Object rs = invoke(rpcRequest);

            outputStream =  new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getType());
        return method.invoke(service,rpcRequest.getParams());
    }
}
