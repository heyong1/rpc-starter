package com.heyong.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.PrivateKey;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 22:18
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        rpcRequest.setType(method.getParameterTypes());

        RpcNetTransport transport = new RpcNetTransport(port, host);
        Object result = transport.send(rpcRequest);
        return result;
    }
}
