package com.vincent.concurrency.example.threadPool;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolEg4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule ran");
            }
        }, 3, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
