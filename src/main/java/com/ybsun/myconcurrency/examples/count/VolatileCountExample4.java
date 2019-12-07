package com.ybsun.myconcurrency.examples.count;

import com.ybsun.myconcurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ybsun
 */
@NotThreadSafe
@Slf4j
public class VolatileCountExample4 {

    /**
     * 总用户请求数
     */
    private static int clientTotal = 50000;

    /**
     * 总并发数
     */
    private static int threadTotal = 200;

    /**
     * 统计最终count值
     */
    private volatile static int count = 0;

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
        // 1取出 count 值
        // 2.count + 1
        // 3.写回主存
        // 其中第二步和第三步对于不同线程可能出现同时写回的情况，导致写入丢失
        count++;
    }
}
