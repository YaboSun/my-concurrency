package com.ybsun.myconcurrency.examples.commonunsafe;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Sun
 * @date 2019/12/7 21:12
 */

@Slf4j
public class StringBuilderExample {

    private static StringBuilder stringBuilder = new StringBuilder();

    private final static int clientTotal = 5000;
    private final static int threadLocal = 200;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        Semaphore semaphore = new Semaphore(threadLocal);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d")
                .build();
        ExecutorService executorService = new ThreadPoolExecutor(threadLocal, threadLocal, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
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
