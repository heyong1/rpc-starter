package com.heyong.example;

import sun.plugin2.message.GetAppletMessage;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Description
 *
 * @Author heyong
 * @Date 2022/11/8 21:14
 */

public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] params;
    private Class[] Type;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getType() {
        return Type;
    }

    public void setType(Class[] type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                ", Type=" + Arrays.toString(Type) +
                '}';
    }
}
