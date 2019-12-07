package com.ybsun.myconcurrency.examples.commonunsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Sun
 * @date 2019/12/7 21:12
 */

@Slf4j
public class UnsafeExample1 {

    private static StringBuilder stringBuilder = new StringBuilder();

    private final static int clientTotal = 5000;
    private final static int threadLocal = 200;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        Semaphore semaphore = new Semaphore(threadLocal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("len: {}" , stringBuilder.length());
        executorService.shutdown();
    }

    private static void update() {
        stringBuilder.append("1");
    }

}
