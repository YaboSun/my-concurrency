package com.ybsun.myconcurrency.examples.threadlocal;

/**
 * @author Sun
 */
public class RequestHolder {

    private final static ThreadLocal<Long> request = new ThreadLocal<>();

    public static void set(Long id) {
        request.set(id);
    }

    public static Long getId() {
        return request.get();
    }

    /**
     * 不做移除可能会造成内存泄漏
     */
    public static void remove() {
        request.remove();
    }
}
