package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/30 17:36
 */

public class CyclicBarrierTest {

    /**
     * 与CountDownLatch 不同的是，CyclicBarrier各个线程会互相等待彼此完成，而且任务完成后可以回调
     */
    @Test
    public void test() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("All tasks finished.");
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println(" Task A is finished.");
                barrier.await();
                System.out.println("A===The other task are all finished...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println(" Task B is finished.");
                barrier.await();
                System.out.println("B===The others are finished...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(30);
    }
}
