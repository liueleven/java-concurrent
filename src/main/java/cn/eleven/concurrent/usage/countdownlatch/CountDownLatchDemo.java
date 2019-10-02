package cn.eleven.concurrent.usage.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @description: CountDownLatch 同步使用
 * @date: 2019-09-17 14:24
 * @author: 十一
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        try {
            new CountDownLatchDemo().go();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void go() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(new Task(countDownLatch),"Thread1").start();

        new Thread(new Task(countDownLatch),"Thread2").start();

        new Thread(new Task(countDownLatch),"Thread3").start();


        countDownLatch.await();
        System.out.println("所有线程已经到达，主线程开始执行 " + System.currentTimeMillis());

    }

    class Task implements Runnable {
        private CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + " 已到达 " + System.currentTimeMillis());
            // 减一个
            this.countDownLatch.countDown();
        }
    }
}
