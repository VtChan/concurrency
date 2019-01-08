package com.vincent.concurrency.example.sync;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncExample1 {

    /**
     * 修饰一个代码块
     * 作用于调用的对象(不同对象互不影响)
     */
    public void test1(int j)
    {
        //同步语句块
        synchronized (this)
        {
            for(int i = 0; i < 10; i++)
            {
                System.out.println("test1-"+j+"-"+i);
            }
        }
    }

    /**
     * 修饰一个方法
     * 作用于调用的对象
     */
    public synchronized void test2(int j)
    {
        for(int i = 0; i < 10; i++)
        {
            System.out.println("test1-"+j+"-"+i);
        }
    }


    public static void main(String[] args)
    {
        SyncExample1 example1 = new SyncExample1();
        SyncExample1 example2 = new SyncExample1();
        //声明一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
           example1.test2(1);
           //example1.test2();
        });
        executorService.execute(() ->{
            example2.test2(2);
            //example1.test2();
        });
    }
}
