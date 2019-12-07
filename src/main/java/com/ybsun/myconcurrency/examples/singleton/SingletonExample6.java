package com.ybsun.myconcurrency.examples.singleton;

import com.ybsun.myconcurrency.annotations.Recommend;
import com.ybsun.myconcurrency.annotations.ThreadSafe;

/**
 * 枚举模式，性能和安全性都是最好的
 */
@ThreadSafe
@Recommend
public class SingletonExample6 {

    private SingletonExample6() {

    }

    private static SingletonExample6 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        /**
         * singleton 对象的枚举实例
         */
        INSTANCE;

        private SingletonExample6 singleton;

        /**
         * JVM 保证这个方法绝对只被执行一次
         */
        Singleton() {
            singleton = new SingletonExample6();
        }

        public SingletonExample6 getSingleton() {
            return singleton;
        }
    }
}

