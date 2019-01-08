package com.vincent.concurrency.example.publish;

import com.vincent.concurrency.annotations.NotThreadSafe;

import java.util.Arrays;

/**
 * 对象发布:是一个对象可被当前范围之外的代码所使用
 */
@NotThreadSafe
public class UnsafePublish {

    private String[] states= {"a", "b", "c"};

    public String[] getStates()  //public发布
    {
        return states;
    }

    public static void main(String[] args)
    {
        UnsafePublish unsafePublish = new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.getStates()));
        //尝试对它私有属性的数组进行修改
        unsafePublish.getStates()[0] = "d";
        System.out.println(Arrays.toString(unsafePublish.getStates()));
    }
}
