package com.vincent.concurrency.example.syncContainer;

import com.vincent.concurrency.annotations.NotThreadSafe;

import javax.jnlp.IntegrationService;
import java.util.Iterator;
import java.util.Vector;


/**
 * foreach 和 Iterator 循环中尽量不要进行更新操作
 */
@NotThreadSafe
public class VectorEg3 {

    /**
     * 以下写法会
     * Exception in thread "main" java.util.ConcurrentModificationException
     * @param v1
     */
    private static void test1 (Vector<Integer> v1)
    {
        for (Integer i : v1)
        {
            if (i.equals(3))
            {
                v1.remove(i);
            }
        }
    }

    /**
     * Exception in thread "main" java.util.ConcurrentModificationException
     * @param v1
     */
    //迭代器遍历
    private static void test2 (Vector<Integer> v1)
    {
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext())
        {
            Integer i = iterator.next();
            if (i.equals(3))
            {
                v1.remove(i);
            }
        }
    }

    /**
     *不会抛出异常，正常执行
     * @param v1
     */
    private static void test3 (Vector<Integer> v1)
    {
        for (int i = 0; i < v1.size(); i++)
        {
            if (v1.get(i).equals(3))
            {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        vector.add(1);
        vector.add(2);
        vector.add(3);
        //test1(vector);
        //test2(vector);
        test3(vector);
    }
}
