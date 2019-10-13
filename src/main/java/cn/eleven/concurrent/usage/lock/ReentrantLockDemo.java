package cn.eleven.concurrent.usage.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 公平锁和非公平锁
 * @date: 2019-09-17 14:14
 * @author: 十一
 */
public class ReentrantLockDemo implements Runnable{

    /**
     * true设置为公平锁，反之
     */
    private ReentrantLock lock = new ReentrantLock(true);


    @Override
    public void run() {
        for (int i=0; i<2; i++) {
            try {
                // 先获取锁
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock!");
                Thread.sleep(1);

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                // 一定要在finally释放锁
                lock.unlock();
            }
        }

    }
    public static void main(String[] args) {

        ReentrantLockDemo rntrt = new ReentrantLockDemo();
        Thread t1 = new Thread(rntrt,"T1");
        Thread t2 = new Thread(rntrt,"T2");
        // 打印结果，交替获取锁
        t1.start();
        t2.start();

//        rntrt.lock.tryLock(10, TimeUnit.SECONDS) 可以等


    }

}
