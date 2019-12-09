package com.ybsun.myconcurrency.examples.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Sun
 * @date 2019/12/9 15:40
 */
@Slf4j
public class SemaphoreExample1 {

    private final static int threadTotal = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadTotal; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 这里通过设置信号量，每次允许三个线程同时访问test方法
                    semaphore.acquire();
                    test(threadNum);
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(2000);
    }
}
