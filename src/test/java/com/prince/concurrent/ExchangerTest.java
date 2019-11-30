package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/30 19:49
 */

public class ExchangerTest {

    /**
     * two thread exchange data.
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(()-> {
            Object objA = new Object();
            try {
                System.out.println("Thread A has object ==> " + objA);
                Object objB = exchanger.exchange(objA);
                System.out.println("Thread A got object ==> " + objB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            Object objB = new Object();
            try {
                System.out.println("Thread B has object ==> " + objB);
                Object objA = exchanger.exchange(objB);
                System.out.println("Thread B got object ==> " + objA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
    }
}
