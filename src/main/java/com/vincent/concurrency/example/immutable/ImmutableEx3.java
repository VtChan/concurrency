package com.vincent.concurrency.example.immutable;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.vincent.concurrency.annotations.ThreadSafe;

/**
 *final关键字
 */
@ThreadSafe
public class ImmutableEx3 {

    private final static ImmutableList list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    public static void main(String[] args) {
        /**
         * 以下都会抛出异常
         */
        list.add(1);
        set.add(5);
        map.put(4, 5);
    }
}
