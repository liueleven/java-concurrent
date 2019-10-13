package cn.eleven.concurrent.usage.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description: 一定要写注释啊
 * @date: 2019-10-13 20:17
 * @author: 十一
 */
public class IntegerAtomicDemo {


    public static void main(String[] args) throws InterruptedException {

        AtomicLong atomicLong = new AtomicLong(100);
        System.out.println(atomicLong.get());

        atomicLong.set(101);
        System.out.println(atomicLong.get());

        System.out.println(atomicLong.compareAndSet(100, 102));
        System.out.println(atomicLong.compareAndSet(101, 500));
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.getAndIncrement());
        System.out.println(atomicLong.get());
    }
}
