package com.vincent.concurrency.example.concurrency;

import com.vincent.concurrency.annotations.ThreadSafe;

import java.util.Set;
import java.util.concurrent.*;


@ThreadSafe
public class CopyOnWriteArraySetEg {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发的线程数
    public static int threadTotal = 200;

    public static Set<Integer> set = new CopyOnWriteArraySet<>();

    public static void main (String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();  //创建线程池
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++)
        {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
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
        System.out.println("size : "+ set.size());
    }

    private static void update(int i){
        set.add(i);
    }
}
