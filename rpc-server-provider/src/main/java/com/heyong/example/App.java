package com.heyong.example;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 21:11
 */
public class App {
    public static void main(String[] args) {
        ISayHelloService service = new SayHelloServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(service,8080);
    }
}
