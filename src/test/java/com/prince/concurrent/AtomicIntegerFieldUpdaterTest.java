package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/26 23:23
 */

public class AtomicIntegerFieldUpdaterTest {

    @Test
    public void test() throws InterruptedException {
        AtomicIntegerFieldUpdater<Clazz> updater = AtomicIntegerFieldUpdater.newUpdater(Clazz.class, "i");
        Clazz clazz = new Clazz();
        int i = updater.get(clazz);
//        System.out.println(i);

        new Thread(() -> {
            for (int j=0; j<20; j++) {
                int rs = updater.getAndAdd(clazz, 1);
                System.out.println("---" + rs);
            }
        }).start();

        new Thread(() -> {
            for (int j=0; j<20; j++) {
                int rs = updater.getAndAdd(clazz, 1);
                System.out.println(">>>" + rs);
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);

    }

    class Clazz {
        volatile int i;
    }
}
