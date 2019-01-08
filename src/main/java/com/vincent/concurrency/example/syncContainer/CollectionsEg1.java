package com.vincent.concurrency.example.syncContainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vincent.concurrency.annotations.ThreadSafe;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Collections.synchronized... 生成同步容器
 */


@ThreadSafe
public class CollectionsEg1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发的线程数
    public static int threadTotal = 200;

    public static Set<Integer> set = Collections.synchronizedSet(Sets.newHashSet());
    public static Map<Integer, Integer> map = Collections.synchronizedMap(Maps.newHashMap());
    public static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

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
        System.out.println("Listsize : "+list.size());
        System.out.println("Setsize : "+set.size());
        System.out.println("Mapsize : "+map.size());
    }

    private static void update(int i){
        set.add(i);
        list.add(i);
        map.put(i, i);
    }
}
