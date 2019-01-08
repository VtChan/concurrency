package com.vincent.concurrency.example.sync;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncExample2 {

    /**
     * 修饰一个类
     * 作用于所有的对象(不同对象有影响)
     */
    public static void test1(int j)
    {
        //同步语句块
        synchronized (SyncExample2.class)
        {
            for(int i = 0; i < 10; i++)
            {
                System.out.println("test1-"+j+"-"+i);
            }
        }
    }

    /**
     * 修饰一个静态方法
     * 作用于所有的对象
     */
    public static synchronized void test2(int j)
    {
        for(int i = 0; i < 10; i++)
        {
            System.out.println("test1-"+j+"-"+i);
        }
    }


    public static void main(String[] args)
    {
        SyncExample2 example1 = new SyncExample2();
        SyncExample2 example2 = new SyncExample2();
        //声明一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
           example1.test1(1);
           //example1.test2();
        });
        executorService.execute(() ->{
            example2.test1(2);
            //example2.test2();
        });
    }
}
