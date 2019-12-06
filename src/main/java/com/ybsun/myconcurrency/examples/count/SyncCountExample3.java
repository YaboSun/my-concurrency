package com.ybsun.myconcurrency.examples.count;

import com.ybsun.myconcurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class SyncCountExample3 {

    private static final int clientTotal = 50000;

    private static final int threadTotal = 200;

    private static Semaphore semaphore = new Semaphore(threadTotal);

    private static CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

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

    private static synchronized void add() {
        count++;
    }
}
