package com.ybsun.myconcurrency.examples.singleton;

import com.ybsun.myconcurrency.annotations.ThreadSafe;

/**
 * @author Sun
 * 饿汉模式 类装载时进行创建，能够保证线程安全
 * 使用静态块来实现
 * 主要编写测试对象发布的内容
 * 通过对单例模式的一步步完善实现
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有构造函数
     */
    private SingletonExample5() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample5 instance = null;

    /**
     * 需要注意的时静态代码块运行的顺序和代码的顺序时一致的
     * 所以顺序不能和上面的交换
     */
    static {
        instance = new SingletonExample5();
    }

    /**
     * 通过静态的工厂方法来获取单例对象
     * 因为外界无法获得类对象，所以需要通过类方法来创建，因此需要声明为静态
     * 对应的单例对象也需要为static的
     * @return instance 创建的单例对象
     */
    public static SingletonExample5 getInstance() {
        return instance;
    }
}
