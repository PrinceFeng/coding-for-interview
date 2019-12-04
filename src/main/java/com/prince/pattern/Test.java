package com.prince.pattern;

import java.lang.reflect.Proxy;

/**
 * @Description 动态代理
 * @Author prince Chen
 * @Date 2019/12/4 20:37
 */

public class Test {

    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        Calculator cal = (Calculator) getProxy(calculator);
        System.out.println(cal.add(3, 4));
    }

    /**
     * 只要给我一个对象，我就能给你一个增强后的代理对象
     * @param target 需要被代理的对象
     * @return 代理对象
     */
    private static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("before....");
            Object result = method.invoke(target, args);
            System.out.println("after...");
            return result;
        });
    }
}
