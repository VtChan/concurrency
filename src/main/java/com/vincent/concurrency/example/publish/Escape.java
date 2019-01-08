package com.vincent.concurrency.example.publish;

import com.vincent.concurrency.annotations.NotRecommend;
import com.vincent.concurrency.annotations.NotThreadSafe;

/**
 * 对象溢出：当一个对象还没被构造完成就被其他线程看到（一种错误的发布）
 */
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape ()
    {
        new InnerClass();
    }

    //定义个内部类
    public class InnerClass
    {
        public InnerClass()
        {
            System.out.println(Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args)
    {
        new Escape();
    }
}
