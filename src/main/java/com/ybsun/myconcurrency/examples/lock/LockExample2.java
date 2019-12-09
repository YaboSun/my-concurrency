package com.ybsun.myconcurrency.examples.lock;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Sun
 * @date 2019/12/9 20:50
 * ReentrantReadWriteLock 简单使用
 */
public class LockExample2 {

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    private static Map<String, Data> map = new TreeMap<>();

    private Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    private Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    private void put(String key, Data value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }


    private class Data {

    }
}
