package com.ximo.java8.chap9;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 朱文赵
 * @date 2018/4/19 19:06
 * @description
 */
public class ProxyFactory implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        method.invoke(target, objects);
        return null;
    }
}
