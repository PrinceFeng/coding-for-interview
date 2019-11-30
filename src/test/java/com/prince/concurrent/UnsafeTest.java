package com.prince.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/27 23:11
 */

public class UnsafeTest {
    public static void main(String[] args) throws InterruptedException {
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println("===>" + unsafe);  // SecurityException: Unsafe  无法拿到实例

        // 通过无孔不入的反射机制
//        System.out.println(getUnsafe());


        /**
         * StupidCounter:  无锁
         * ===>counter result: 9502964
         * ===>time: 507
         *  SyncCounter:  使用Synchronized
         * ===>counter result: 10000000
         * ===>time: 949
         * LockCounter:  使用ReentrantLock
         * ===>counter result: 10000000
         * ===>time: 353
         * AtomicCounter:  使用原子类AtomicLong
         * ===>counter result: 10000000
         * ===>time: 253
         *
         */
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
//        Counter counter = new StupidCounter();
//        SyncCounter counter = new SyncCounter();
//        LockCounter counter = new LockCounter();
        AtomicCounter counter = new AtomicCounter();
        long start = System.currentTimeMillis();
        for (int i=0; i<1000; i++) {
            executorService.submit(new CounterRunnable(counter, 10000));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();

        System.out.println("===>counter result: " + counter.getCounter());
        System.out.println("===>time: " + (end-start));
    }

    /**
     * 利用反射获取Unsafe实例
     * @return unsafe
     */
    private static Unsafe getUnsafe() {
        try {
            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);
            return (Unsafe) unsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    interface Counter {
        void increment();
        long getCounter();
    }

    /**
     * 线程不安全
     */
    static class StupidCounter implements Counter {
        private long counter;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 使用重量级锁 synchronized
     */
    static class SyncCounter implements Counter {
        private long counter;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 使用Lock
     */
    static class LockCounter implements Counter {
        private long counter;
        private Lock lock = new ReentrantLock();
        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    /**
     * 使用原子类
     */
    static class AtomicCounter implements Counter {
        private AtomicLong al = new AtomicLong();

        @Override
        public void increment() {
            al.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return al.get();
        }
    }

    static class CounterRunnable implements Runnable {
        private final Counter counter;
        private final int num;

        CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i=0; i<num; i++) {
                counter.increment();
            }
        }
    }
}
