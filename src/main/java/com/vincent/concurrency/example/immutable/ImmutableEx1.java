package com.vincent.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.vincent.concurrency.annotations.NotThreadSafe;

import java.util.Map;

/**
 *final关键字
 */
@NotThreadSafe
public class ImmutableEx1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static
    {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //以下三个编译出错
//        a = 3;
//        b = "3";
//        map = Maps.newHashMap();

        //可以修改map的值（线程安全问题）
        map.put(1, 3);
        System.out.println(map.get(1));
    }
}
