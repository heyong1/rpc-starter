package com.heyong.example;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 21:12
 */
public class SayHelloServiceImpl implements ISayHelloService{
    @Override
    public String sayHello(String txt) {
        System.out.println("request data:------" + txt);
        return "Hi,boy," + txt;
    }
}
