package cn.eleven.concurrent.sync.syncdemo;

/**
 * @description: synchronized 的可重入性
 * @date: 2019-09-16 12:28
 * @author: 十一
 */
public class SynchronizedDemo5 {


    public  synchronized void method1() {
        System.out.println("method1");
        method2();

    }

    public  synchronized void method2() {
        System.out.println("method2");
        method3();

    }
    public  synchronized void method3() {
        System.out.println("method3");
    }

    public static void main(String[] args) {
        SynchronizedDemo5 s1 = new SynchronizedDemo5();
        // synchronized 的可重入性
        s1.method1();
    }
}
