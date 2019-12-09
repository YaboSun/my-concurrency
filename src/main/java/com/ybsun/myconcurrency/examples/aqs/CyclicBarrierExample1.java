package com.ybsun.myconcurrency.examples.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sun
 * @date 2019/12/9 19:42
 */
@Slf4j
public class CyclicBarrierExample1 {

    private final static int threadTotal = 10;
    private final static int barrierNum = 5;
    private static CyclicBarrier barrier = new CyclicBarrier(barrierNum);
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < threadTotal; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    private static void race(int threadNum) throws BrokenBarrierException, InterruptedException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        barrier.await();
        log.info("{} is continue", threadNum);
    }
}
