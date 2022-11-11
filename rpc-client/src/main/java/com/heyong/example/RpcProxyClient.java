package com.heyong.example;

import java.lang.reflect.Proxy;
import java.rmi.server.RemoteObjectInvocationHandler;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 22:15
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[] {interfaceCls},
                new RemoteInvocationHandler(host,port));
    }
}
