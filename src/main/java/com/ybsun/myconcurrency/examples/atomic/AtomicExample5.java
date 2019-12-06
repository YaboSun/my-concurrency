package com.ybsun.myconcurrency.examples.atomic;

import com.ybsun.myconcurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
@Slf4j
public class AtomicExample5 {

    // 这个实际中使用不是很多
    private static final AtomicIntegerFieldUpdater<AtomicExample5> updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");
    @Getter
    public volatile int count = 100; // 要修改的字段必须是原子性的，而且不能是static

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }

    }

}
