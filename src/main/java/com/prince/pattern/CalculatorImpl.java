package com.prince.pattern;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/12/4 20:37
 */

public class CalculatorImpl implements Calculator {
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int sub(int x, int y) {
        return x - y;
    }
}
