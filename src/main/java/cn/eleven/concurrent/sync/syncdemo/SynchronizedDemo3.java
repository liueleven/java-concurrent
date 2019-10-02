package cn.eleven.concurrent.sync.syncdemo;

/**
 * @description: 多个对象时，此时对象锁在该场景不起作用了
 * @date: 2019-09-16 12:28
 * @author: 十一
 */
public class SynchronizedDemo3 {

    private static int count = 6;

    public synchronized  void printNum() {
        try {
            // 为了效果，否则太快了
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        // 打印的结果有问题，static修饰的变量是该类所有对象共享的,synchronized是对象锁，不会互斥
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }


    public static void main(String[] args) {
        SynchronizedDemo3 s1 = new SynchronizedDemo3();
        SynchronizedDemo3 s2 = new SynchronizedDemo3();
        SynchronizedDemo3 s3 = new SynchronizedDemo3();
        SynchronizedDemo3 s4 = new SynchronizedDemo3();
        SynchronizedDemo3 s5 = new SynchronizedDemo3();

        new Thread(()-> { s1.printNum(); }).start();
        new Thread(()-> { s2.printNum(); }).start();
        new Thread(()-> { s5.printNum(); }).start();
        new Thread(()-> { s3.printNum(); }).start();
        new Thread(()-> { s4.printNum(); }).start();

    }
}
