package com.vincent.concurrency.example.singleton;

import com.vincent.concurrency.annotations.ThreadSafe;

/**
 * 单例模式：饿汉（单例实例在类装载时进行创建）
 */
@ThreadSafe
public class SingletonExa2 {

    //构造方法必须私有，不允许随意创建对象
    //不足：饿汉模式下如果构造函数进行过多处理，类加载会很慢
    private SingletonExa2()
    {

    }

    //单例的对象(静态域声明)
    private static SingletonExa2 instance = new SingletonExa2();

    //静态的工厂方法来创建对象
    public static SingletonExa2 getInstance()
    {

        return instance;
    }
}
