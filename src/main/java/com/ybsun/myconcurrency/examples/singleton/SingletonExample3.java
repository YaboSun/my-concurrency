package com.ybsun.myconcurrency.examples.singleton;

import com.ybsun.myconcurrency.annotations.NotRecommend;
import com.ybsun.myconcurrency.annotations.ThreadSafe;

/**
 * @author Sun
 * 懒汉模式 通过synchronized保证线程安全 第一次使用进行创建
 * 主要编写测试对象发布的内容
 * 通过对单例模式的一步步完善实现
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 私有构造函数
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 通过静态的工厂方法来获取单例对象
     * 因为外界无法获得类对象，所以需要通过类方法来创建，因此需要声明为静态
     * 对应的单例对象也需要为static的
     * 通过添加同步操作保证同一时间只能有一个线程访问，保证了线程安全
     * 但同时带来了性能损耗
     * @return instance 创建的单例对象
     */
    public static synchronized SingletonExample3 getInstance() {
        // 判断当前对象是否为空
        if (instance == null) {
            instance = new SingletonExample3();
        }

        return instance;
    }
}
