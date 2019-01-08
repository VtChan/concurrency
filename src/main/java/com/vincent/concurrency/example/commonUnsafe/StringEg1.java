package com.vincent.concurrency.example.commonUnsafe;

import com.vincent.concurrency.annotations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class StringEg1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发的线程数
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

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
                    update();
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
        System.out.println("size :"+stringBuilder.length());
    }

    private static void update(){
        stringBuilder.append("1");
    }
}
