package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Description CyclicBarrier 每一个线程到达栅栏后大家一起前行
 * @Author prince Chen
 * @Date 2019/11/30 17:36
 */

public class CyclicBarrierTest {

    /**
     * 与CountDownLatch 不同的是，CyclicBarrier各个线程会互相等待彼此完成，而且任务完成后可以回调
     */

    public static void main(String[] args) {
        int count = 2;
        CyclicBarrier barrier = new CyclicBarrier(count, () -> {
            System.out.println("All parties tripped...");
        });

        for (int i=0; i<count+2; i++) {
            new Worker(barrier).start();
        }
    }

    static class Worker extends Thread {
        private CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到达栅栏");
            System.out.println(Thread.currentThread().getName() + " parties required: " + barrier.getParties());
            System.out.println(Thread.currentThread().getName() + " parties waiting: " + barrier.getNumberWaiting());
            try {
                barrier.await();

                System.out.println(Thread.currentThread().getName() + "大家都到了，冲鸭。。。");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "执行完成！");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
