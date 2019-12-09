package com.ybsun.myconcurrency.examples.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Sun
 * @date 2019/12/9 16:36
 */
@Slf4j
public class SemaphoreExample2 {

    private final static int threadTotal = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadTotal; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 这里通过判断当前是否能够拿到许可
                    // 只有能拿到才执行，如果拿不到直接跳出执行下面的代码
                    if (semaphore.tryAcquire()) {
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
    }
}
