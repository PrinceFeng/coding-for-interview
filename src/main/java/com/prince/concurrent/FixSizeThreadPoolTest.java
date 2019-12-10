package com.prince.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Description 测试自己实现的线程池
 * @Author prince Chen
 * @Date 2019/12/9 23:04
 */

public class FixSizeThreadPoolTest {

    public static void main(String[] args) {
        FixSizeThreadPool pool = new FixSizeThreadPool(3, 6);
        for (int i = 0; i < 7; i++) {
            pool.submit(() -> {
                System.out.println("来了一个任务！");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            });
        }

        pool.close();
    }
}
