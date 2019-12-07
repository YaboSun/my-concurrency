package com.ybsun.myconcurrency.examples.singleton;

import com.ybsun.myconcurrency.annotations.ThreadSafe;

/**
 * @author Sun
 * 懒汉模式 通过双重校验锁实现
 * 主要编写测试对象发布的内容
 * 通过对单例模式的一步步完善实现
 */
@ThreadSafe
public class SingletonExample4 {

    /**
     * 私有构造函数
     */
    private SingletonExample4() {

    }

    /**
     * 单例对象 禁止指令重排 volatile
     */
    private static volatile SingletonExample4 instance = null;

    /**
     * 通过静态的工厂方法来获取单例对象
     * 因为外界无法获得类对象，所以需要通过类方法来创建，因此需要声明为静态
     * 对应的单例对象也需要为static的
     * 由于单纯的双重检测在多线程环境下对象的new过程中会存在指令重排的问题
     * 所以无法保证线程安全，因此需要将instance对象声明为volatile防止指令重排
     * @return instance 创建的单例对象
     */
    public static SingletonExample4 getInstance() {
        // 判断当前对象是否为空
        // 双重检测
        if (instance == null) {
            // 同步锁
            synchronized (SingletonExample1.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
