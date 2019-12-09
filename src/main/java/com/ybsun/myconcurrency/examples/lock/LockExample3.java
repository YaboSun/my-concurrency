package com.ybsun.myconcurrency.examples.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sun
 * @date 2019/12/9 21:06
 */
@Slf4j
public class LockExample3 {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {

            try {
                reentrantLock.lock();
                // 1
                log.info("wait signal");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 4
            log.info("get signal");
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            // 2
            log.info("get lock");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            // 3
            log.info("signal ~");
            reentrantLock.unlock();
        }).start();
    }
}
