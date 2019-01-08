package com.vincent.concurrency.example.singleton;

import com.vincent.concurrency.annotations.Recommend;
import com.vincent.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExa7 {

    //构造方法必须私有，不允许随意创建对象
    private SingletonExa7()
    {

    }
    //静态的工厂方法来创建对象
    public static SingletonExa7 getInstance()
    {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton
    {
        INSTANCE;

        private SingletonExa7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton()
        {
            singleton = new SingletonExa7();
        }

        public SingletonExa7 getInstance()
        {
            return singleton;
        }
    }
}
