package com.ybsun.myconcurrency.examples.immutable;

import com.google.common.collect.Maps;
import com.ybsun.myconcurrency.annotations.NotThreadSafe;

import java.util.Map;

/**
 * @author Sun
 */

@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        /*a = 2;
        b = "3";
        map = Maps.newHashMap();*/

        // 可以看到对于final修饰常量及变量时是无法赋值的
        // 当修饰引用类型是不能重新指向其他引用
        // 但是本身的值是可以改变的
        map.put(1, 3);
    }
}
