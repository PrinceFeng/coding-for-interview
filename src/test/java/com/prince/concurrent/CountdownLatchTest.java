package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/30 14:30
 */

public class CountdownLatchTest {

    /**
     * 等指挥官到了，大家才能行动
     */
    @Test
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("110 in position.");
            try {
                countDownLatch.await();
                System.out.println("110 move...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("120 in position.");
            try {
                countDownLatch.await();
                System.out.println("120 move...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("Commander in position....");
                countDownLatch.countDown();
        }).start();

    }
}
