package com.prince.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description 自己实现一个线程池
 * @Author prince Chen
 * @Date 2019/12/9 22:28
 */

public class FixSizeThreadPool {

    /**
     * 存放任务的队列
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * 存放线程的容器
     */
    private List<Thread> workers;

    /**
     * 构造给定大小的线程池
     *
     * @param poolSize  线程池的大小
     * @param queueSize 任务队列大小
     */
    public FixSizeThreadPool(int poolSize, int queueSize) {
        this.workQueue = new LinkedBlockingQueue<>(queueSize);
        this.workers = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * 提交任务到任务队列
     * @param task 任务
     * @return
     */
    public boolean submit(Runnable task) {
        if (this.isWorking) {
            return this.workQueue.offer(task);
        } else {
            return false;
        }
    }

    /**
     * 线程池是否关闭的标志
     */
    private volatile boolean isWorking = true;

    /**
     * 关闭线程池
     *  1. 停止接收任务
     *  2. 还有任务？ 继续执行完毕
     *  3. 线程去拿任务，不要阻塞了
     *  4. 已经阻塞的线程？ 中断
     */
    public void close() {
        this.isWorking = false;
        for (Thread worker : workers){
            if (worker.getState().equals(Thread.State.BLOCKED)) {
                worker.interrupt();
            }
        }
    }


    static class Worker extends Thread {

        private FixSizeThreadPool pool;

        public Worker(FixSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (this.pool.isWorking || this.pool.workQueue.size() > 0) {
                Runnable task = null;
                try {
                    if (this.pool.isWorking) {
                        // 线程池没有关闭，从任务队列取任务，如果队列为空则会阻塞
                        task = this.pool.workQueue.take();
                    } else {
                        // 线程池已经关闭
                        task = this.pool.workQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    // 执行提交的Runnable中的run
                    task.run();
                    System.out.println("线程：" + Thread.currentThread().getName() + "执行完成。");
                }
            }

        }
    }
}
