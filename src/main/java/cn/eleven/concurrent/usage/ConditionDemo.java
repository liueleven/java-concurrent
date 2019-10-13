package cn.eleven.concurrent.usage;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Condition的使用
 * @date: 2019-10-13 19:53
 * @author: 十一
 */
public class ConditionDemo {


    public static void main(String[] args) {
        WaitSignal waitSignal = new WaitSignal();
        new Thread(() -> {
            try {
                System.out.println("等待中...");
                waitSignal.await();
                System.out.println("被唤醒了，等待结束...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitSignal.signal();
            System.out.println("唤醒");
        }).start();


    }
    static class WaitSignal {

        private final ReentrantLock lock;

        private final Condition condition;

        public WaitSignal() {
            lock = new ReentrantLock();
            condition = lock.newCondition();
        }

        public void await() throws InterruptedException {
            // 先获取锁
            lock.lock();
            condition.await();
            lock.unlock();

        }

        public void signal() {
            lock.lock();
            condition.signal();
            lock.unlock();
        }
    }
}
