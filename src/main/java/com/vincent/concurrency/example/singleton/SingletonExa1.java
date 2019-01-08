package com.vincent.concurrency.example.singleton;

import com.vincent.concurrency.annotations.NotThreadSafe;

/**
 * 单例模式：懒汉（单例实例在第一次使用时进行创建）
 */
@NotThreadSafe
public class SingletonExa1 {

    //构造方法必须私有，不允许随意创建对象
    private SingletonExa1()
    {

    }

    //单例的对象
    private static SingletonExa1 instance = null;

    //静态的工厂方法来创建对象
    public static SingletonExa1 getInstance()
    {
        /**
         * 多线程时，同时看到对象为空，会创建多个对象
         */
        if (instance == null)
        {
            instance = new SingletonExa1();
        }
        return instance;
    }
}
