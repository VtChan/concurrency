package com.vincent.concurrency.example.singleton;

import com.vincent.concurrency.annotations.NotRecommend;
import com.vincent.concurrency.annotations.ThreadSafe;

/**
 * 单例模式：懒汉（单例实例在第一次使用时进行创建）
 */
@ThreadSafe
@NotRecommend
public class SingletonExa3 {

    //构造方法必须私有，不允许随意创建对象
    private SingletonExa3()
    {

    }

    //单例的对象
    private static SingletonExa3 instance = null;

    //静态的工厂方法来创建对象
    public static synchronized SingletonExa3 getInstance()
    {
        /**
         * 多线程时，同时看到对象为空，会创建多个对象
         * (加synchronized可以解决此问题，但是同一时刻只能有一个线程拥有锁)
         */
        if (instance == null)
        {
            instance = new SingletonExa3();
        }
        return instance;
    }
}
