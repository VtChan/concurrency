package com.vincent.concurrency.example.atomic;

import com.vincent.concurrency.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class AtomicExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发的线程数
    public static int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);
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
        System.out.println("count :"+count.get());
    }

    private static void add(){
        count.incrementAndGet();
    }
}
