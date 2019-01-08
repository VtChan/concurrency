package com.vincent.concurrency.example.concurrency;

import com.vincent.concurrency.annotations.ThreadSafe;


import java.util.List;
import java.util.concurrent.*;

/**
 * 可重入锁（更新会加锁）
 * 适用于读多写少
 */
@ThreadSafe
public class CopyOnWriteArrayListEg {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发的线程数
    public static int threadTotal = 200;

    public static List<Integer> list = new CopyOnWriteArrayList<>();

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
        System.out.println("size : "+list.size());
    }

    private static void update(int i){
        list.add(i);
    }
}
