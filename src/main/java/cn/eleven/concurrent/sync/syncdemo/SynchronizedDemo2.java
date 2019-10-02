package cn.eleven.concurrent.sync.syncdemo;

/**
 * @description: synchronized 对象锁，一个对象使用一把锁，在该方法中是被某个对象持有，
 *               如果该对象没有释放，后面的会阻塞等待，直到该对象释放，这样就保证了数据的正确性
 * @date: 2019-09-16 12:28
 * @author: 十一
 */
public class SynchronizedDemo2 implements Runnable{

    private int count = 5;


    @Override
    public synchronized void run() {
        try {
            // 为了效果，否则太快了
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        // 打印结果每个count值不相等
        System.out.println(count);
    }

    public static void main(String[] args) {
        SynchronizedDemo2 s1 = new SynchronizedDemo2();

        // 一个对象多次访问共享数据，需要在方法中加synchronized，对象锁来保证数据正确，
        for (int i=0; i< 5; i++) {
            new Thread(s1).start();
        }

    }
}
