package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/30 14:30
 */

public class CountdownLatchTest {

    /**
     * 等指挥官到了，大家才能行动
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("110 in position.");
            try {
                TimeUnit.SECONDS.sleep(5);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("120 in position.");
            countDownLatch.countDown();
        }).start();


        countDownLatch.await();
        System.out.println("Other threads finished....");


    }
}
