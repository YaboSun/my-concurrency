package com.ybsun.myconcurrency.examples.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    private void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1, -{}", i);
            }
        }
    }

    private synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2, -{}", i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();

        /**
         * 通过运行结果可以看到对于修饰代码块和方法来说，如果方法内部与代码块实现相同，
         * 那其实二者是等效的
         * 1.不同线程调用同一对象的同一方法，结果两者都是交替打印
         * 2.不同线程调用不同对象的同一方法，结果两者都是随机交叉打印
         */
        executorService.execute(() -> {
            example1.test2();
        });

        executorService.execute(() -> {
            example2.test2();
        });

        executorService.execute(() -> {
            example1.test1();
        });

        executorService.execute(() -> {
            example2.test1();
        });

        executorService.shutdown();
    }
}
