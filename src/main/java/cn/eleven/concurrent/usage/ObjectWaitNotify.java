package cn.eleven.concurrent.usage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: wait和notify的使用，他们执行前必须先获得锁，位于 Synchronized 构建的同步代码块
 * @date: 2019-10-13 19:26
 * @author: 十一
 */
public class ObjectWaitNotify {


    public static void main(String[] args) {
        ObjectWaitNotify objectWaitNotify = new ObjectWaitNotify();

        new Thread(() -> {
            try {

                System.out.println("等待中...");
                synchronized (objectWaitNotify) {
                    objectWaitNotify.wait();
                }
                System.out.println("被唤醒了，等待结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectWaitNotify) {
                objectWaitNotify.notify();
            }
            System.out.println("唤醒...");
        }).start();


    }
}
