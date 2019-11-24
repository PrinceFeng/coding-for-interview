package com.prince.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 两个线程，间隔的打印奇数和偶数
 * @Author prince Chen
 * @Date 2019/11/24 19:07
 */

public class PrintOddAndEvenNumberByTwoThread {
    private static final Object obj = new Object();
    private static final int total = 100;
    private static volatile int i = 0;
    private static CountDownLatch latch = new CountDownLatch(2);

    /**
     * 采用synchronized，效率比较低
     */
    public static void solution01() {
        Thread t1 = new Thread(() -> {
            while (i <= total) {
                synchronized(obj) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "--" + i++);
                        obj.notify();
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            latch.countDown();
        }, "偶数线程");

        Thread t2 = new Thread(() -> {
            while (i <= 100) {
                synchronized (obj) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ">>" + i++);
                        obj.notify();
                    } else
                        try{
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
            latch.countDown();
        }, "奇数线程");

        t1.start();
        t2.start();
    }

    private static volatile boolean flag = true;
    private static AtomicInteger num = new AtomicInteger(0);
    /**
     * 利用并发包中的AtomicInteger原子类结合volatile关键字
     */
    public static void solution02() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (num.get() <= total) {
                    if (flag) {
                        System.out.println("-----" + num.getAndIncrement());
                        flag =false;
                    }
                }
                latch.countDown();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while(num.get() <= total) {
                    if (!flag) {
                        System.out.println(">>>>>" + num.getAndIncrement());
                        flag = true;
                    }
                }
                latch.countDown();
            }
        };

        t1.start();
        t2.start();
    }




    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
//        solution01();
        solution02();

        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end -  start));
    }
}
