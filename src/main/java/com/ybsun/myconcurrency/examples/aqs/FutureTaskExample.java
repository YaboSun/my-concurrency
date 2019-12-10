package com.ybsun.myconcurrency.examples.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Sun
 * @date 2019/12/10 15:56
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something callable");
                Thread.sleep(5000);
                return "done";
            }
        });

        new Thread(futureTask).run();
        log.info("do something in main");
        Thread.sleep(3000);

        String result = futureTask.get();
        log.info("result:{}", result);
    }
}
