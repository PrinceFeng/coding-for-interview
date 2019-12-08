package com.prince.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/12/8 15:01
 */

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        IntStream.range(0, 5).forEach(i -> {
            new Thread(new Worker(startSignal, doneSignal)).start();
        });

        TimeUnit.SECONDS.sleep(5);
        startSignal.countDown();
        doneSignal.await();
        System.out.println("All done.");

    }

    static class Worker implements Runnable {
        private CountDownLatch startSignal;
        private CountDownLatch doneSignal;

        public Worker(CountDownLatch  startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doWork() throws InterruptedException {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " has finished the work.");
        }
    }
}
