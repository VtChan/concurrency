package com.vincent.concurrency.example.atomic;

import com.vincent.concurrency.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");


    public volatile int count = 100;

    private static AtomicExample5 example5 = new AtomicExample5(); //包含了count

    public static void main(String[] args)
    {
        if (updater.compareAndSet(example5, 100, 120))
        {
            System.out.println("update success:"+example5.count);
        }

    }
}
