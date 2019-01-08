package com.vincent.concurrency.example.count;

import com.vincent.concurrency.annotations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class CountExample4 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发的线程数
    public static volatile int threadTotal = 200;

    public static int count = 0;
    public static void main (String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();  //创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++)
        {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count :"+count);
    }

    private static void add(){
        count++;
        // 1.读count  （两个线程读取最新值）
        // 2. +1
        // 3.写count   （两个线程同时写，丢失一次加一的操作）
    }
}
