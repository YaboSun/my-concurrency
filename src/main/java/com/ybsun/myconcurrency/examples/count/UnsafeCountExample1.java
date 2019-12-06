package com.ybsun.myconcurrency.examples.count;

import com.ybsun.myconcurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@NotThreadSafe
@Slf4j
public class UnsafeCountExample1 {

    // 总用户请求数
    private static int clientTotal = 50000;

    // 总并发数
    private static int threadTotal = 200;

    // 统计最终count值
    private static int count = 0;

    public static void main(String[] args) throws Exception {

        // 通过线程池创建来实现，这里实现方式有很多种
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

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
        executorService.shutdown();
        System.out.println("count:" + count);
    }

    private static void add() {
        count++;
    }
}
