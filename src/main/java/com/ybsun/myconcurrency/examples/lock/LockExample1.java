package com.ybsun.myconcurrency.examples.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sun
 * @date 2019/12/9 20:43
 */
@Slf4j
public class LockExample1 {


    private final static int clientTotal = 50000;

    private final static int threadTotal = 200;

    private static Semaphore semaphore = new Semaphore(threadTotal);

    private static CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    private final static Lock LOCK = new ReentrantLock();

    private static int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        log.info("count: {}", count);
        executorService.shutdown();
    }

    private static void add() {
        LOCK.lock();
        try {
            count++;
        } finally {
            LOCK.unlock();
        }

    }
}
