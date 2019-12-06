package com.ybsun.myconcurrency.examples.atomic;

import com.ybsun.myconcurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@ThreadSafe
@Slf4j
public class AtomicExample3 {

    // 总用户请求数
    private static int clientTotal = 50000;

    // 总并发数
    private static int threadTotal = 200;

    // 统计最终count值
    private static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception {

        // 通过线程池创建来实现，这里实现方式有很多种
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 信号量使用可以控制同时能运行多少个任务
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 使用 CountDownLatch 来控制总共有多少请求，每次处理完一个减一
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
        count.increment();
    }
}
