package com.vincent.concurrency.example.singleton;


import com.vincent.concurrency.annotations.NotThreadSafe;

/**
 * 单例模式：懒汉（单例实例在第一次使用时进行创建） -->双重同步锁待定模式
 */
@NotThreadSafe //指令重排
public class SingletonExa4 {

    //构造方法必须私有，不允许随意创建对象
    private SingletonExa4()
    {

    }
    //单线程
    // 1、memory = allocate（） 分配对象的空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    /**
     * 多线程
     * JVM和CPU优化，发生指令重排
     * 1、memory = allocate（） 分配对象的空间
     *  3、instance = memory 设置instance指向刚分配的内存
     * 2、ctorInstance() 初始化对象
     */

    //单例的对象
    private static SingletonExa4 instance = null;

    //静态的工厂方法来创建对象
    public static  SingletonExa4 getInstance()
    {
        if (instance == null) //双重检测机制
        {
            //对这个判断进行加锁，不会引起性能问题
            synchronized (SingletonExa4.class) //同步锁   // 2）B检测到对象不为空，直接返回。但是对象还没初始化，调用时会出问题
            {
                if(instance == null)
                {
                    instance = new SingletonExa4();  // 1）A  步骤3
                }
            }

        }
        return instance;
    }
}
