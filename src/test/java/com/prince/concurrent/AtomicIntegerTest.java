package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/25 20:54
 */

public class AtomicIntegerTest {

    private AtomicInteger ac = new AtomicInteger();
    private final int total = 100;
    private volatile boolean flag = true;

    @Test
    public void casTest() {
        new Thread(() -> {
            while (ac.get() <= total) {
                if (flag) {
                    System.out.println("---" + ac.getAndAdd(1));
                    flag = false;
                }
            }
        }).start();

        new Thread(() -> {
            while (ac.get() <= total) {
                if(!flag) {
                    System.out.println(">>>" + ac.getAndAdd(1));
                    flag = true;
                }
            }
        }).start();
    }

    /**
     * CAS: Compare And Set
     */
    @Test
    public void testCAS() {
        AtomicInteger ac = new AtomicInteger(10);
        boolean b = ac.compareAndSet(12, 15);
        System.out.println(ac.get());  // 10
    }

}
