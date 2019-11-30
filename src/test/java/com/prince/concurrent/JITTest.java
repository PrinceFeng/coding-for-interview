package com.prince.concurrent;

/**
 * @Description 测试一个奇怪的地方
 * @Author prince Chen
 * @Date 2019/11/25 20:06
 */

public class JITTest {

    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!flag) {
                /**
                 * 奇怪现象：
                 * 这里的while循环内部如果没有语句，则此线程不会停止
                 * 但是里面随便加一条语句，此线程就会停止
                 * 解释：
                 *   JIT优化导致的
                 *   如果while内部没有语句，则这个while语句就相当于 while(true){}  此情况下线程肯定永远不会结束了。。。
                 *   因此这种多线程情况下的共享变量，最好设置为volatile
                 */
//                System.out.println(flag);
            }
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            flag = true;
            System.out.println("set flag to true");
        }).start();
    }
}
