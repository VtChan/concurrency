package com.vincent.concurrency.example.singleton;

import com.vincent.concurrency.annotations.ThreadSafe;

/**
 * 单例模式：饿汉（单例实例在类装载时进行创建）
 */
@ThreadSafe
public class SingletonExa6 {

    //构造方法必须私有，不允许随意创建对象
    //不足：饿汉模式下如果构造函数进行过多处理，类加载会很慢
    private SingletonExa6()
    {

    }


    //单例的对象
    private static SingletonExa6 instance = null;

    //静态代码块
    static {
        instance = new SingletonExa6();
    }

    //静态的工厂方法来创建对象
    public static SingletonExa6 getInstance()
    {

        return instance;
    }

    public static void main(String[] args)
    {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}
