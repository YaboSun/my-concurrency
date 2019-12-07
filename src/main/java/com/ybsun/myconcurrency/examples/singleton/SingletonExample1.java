package com.ybsun.myconcurrency.examples.singleton;

import com.ybsun.myconcurrency.annotations.NotThreadSafe;

/**
 * @author Sun
 * 懒汉模式 第一次使用进行创建
 * 主要编写测试对象发布的内容
 * 通过对单例模式的一步步完善实现
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造函数
     */
    private SingletonExample1() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 通过静态的工厂方法来获取单例对象
     * 因为外界无法获得类对象，所以需要通过类方法来创建，因此需要声明为静态
     * 对应的单例对象也需要为static的
     * @return instance 创建的单例对象
     */
    public static SingletonExample1 getInstance() {
        // 判断当前对象是否为空
        if (instance == null) {
            instance = new SingletonExample1();
        }

        return instance;
    }
}
