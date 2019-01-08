package com.vincent.concurrency.example.singleton;


import com.vincent.concurrency.annotations.ThreadSafe;

/**
 * 单例模式：懒汉（单例实例在第一次使用时进行创建） -->双重同步锁待定模式
 */
@ThreadSafe//volatile限制指令重排
public class SingletonExa5 {

    //构造方法必须私有，不允许随意创建对象
    private SingletonExa5()
    {

    }

    // 1、memory = allocate（） 分配对象的空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存


    //单例的对象  volatile + 双重检测机制 ——> 禁止指令重排
    private volatile static SingletonExa5 instance = null;

    //静态的工厂方法来创建对象
    public static SingletonExa5 getInstance()
    {
        if (instance == null) //双重检测机制
        {
            //对这个判断进行加锁，不会引起性能问题
            synchronized (SingletonExa5.class) //同步锁
            {
                if(instance == null)
                {
                    instance = new SingletonExa5();
                }
            }

        }
        return instance;
    }
}
