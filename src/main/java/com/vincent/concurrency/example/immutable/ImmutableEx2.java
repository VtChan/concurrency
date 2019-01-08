package com.vincent.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.vincent.concurrency.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

/**
 *final关键字
 */
@ThreadSafe
public class ImmutableEx2 {


    private static Map<Integer, Integer> map = Maps.newHashMap();

    static
    {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

        map.put(1, 3); //会抛出异常
        System.out.println(map.get(1));
    }
}
