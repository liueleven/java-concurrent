package cn.eleven.concurrent.sync.syncdemo;

/**
 * @description: 多个对象访问共同资源，需要升级为类锁，才能保证数据正确性
 * @date: 2019-09-16 12:28
 * @author: 十一
 */
public class SynchronizedDemo4{
    private static int count = 6;

    public synchronized static void printNum() {
        try {
            // 为了效果，否则太快了
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        // 正确打印，synchronized类锁
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }


    public static void main(String[] args) {
        SynchronizedDemo4 s1 = new SynchronizedDemo4();
        SynchronizedDemo4 s2 = new SynchronizedDemo4();
        SynchronizedDemo4 s3 = new SynchronizedDemo4();
        SynchronizedDemo4 s4 = new SynchronizedDemo4();
        SynchronizedDemo4 s5 = new SynchronizedDemo4();

        // 多个对象访问共同资源，需要升级为类锁，才能保证数据正确性
        new Thread(()-> { s1.printNum(); }).start();
        new Thread(()-> { s2.printNum(); }).start();
        new Thread(()-> { s5.printNum(); }).start();
        new Thread(()-> { s3.printNum(); }).start();
        new Thread(()-> { s4.printNum(); }).start();

    }


}
