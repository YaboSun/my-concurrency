package com.ybsun.myconcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyTest {

    // 总用户请求数
    private static int clientTotal = 5000;

    // 总并发数
    private static int threadTotal = 200;

    // 统计最终count值
    private static int count = 0;

    public static void main(String[] args) {
        // 通过线程池创建来实现，这里实现方式有很多种
        ExecutorService executorService = Executors.newCachedThreadPool();

    }

    private static void add() {
        count++;
    }
}
