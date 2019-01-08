package com.vincent.concurrency.example.syncContainer;

import com.vincent.concurrency.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * 同步容器里面多个线程的同步方法因为顺序的差异，会导致线程不安全
 */
@NotThreadSafe
public class VectorEg2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
