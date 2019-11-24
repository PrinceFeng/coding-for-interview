package com.prince.concurrent;

/**
 * @Description 单例模式
 * @Author prince Chen
 * @Date 2019/11/24 18:56
 */

public class Singleton {

    /**
     * 这里也可以不用volatile修饰
     * 但是，用volatile修饰，可以让刚new出来的instance立即被其他线程可见，提高效率
     *
     * volatile语义规范：
     *      1，读取volatile修饰的变量时，必须从主内存重新加载，并且保证read和load是连续的
     *      2，修改变量时，必须立马同步回主内存，并且保证store和write是连续的
     */
    private static volatile Singleton instance;

    /**
     * 私有化构造函数
     */
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            /**
             * synchronized语义规范：
             *      1，进入同步块前，先清空工作内存中的所有变量，从主内存中重新读取
             *      2，释放锁前，将修改过的变量同步回主内存
             */
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


}
