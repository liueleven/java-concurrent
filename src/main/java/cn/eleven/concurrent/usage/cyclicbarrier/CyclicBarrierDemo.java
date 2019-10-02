package cn.eleven.concurrent.usage.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier循环栅栏的用法，它和countDownlatch一样，可以达到
 * 让线程协同效果，但是它还可以循环使用，reset()方法可以恢复线程数
 * @date: 2019-09-17 14:28
 * @author: 十一
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        try {
            new CyclicBarrierDemo().go();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void go() throws InterruptedException {

        // 第一个参数是要等待的数量，另一个是执行的操作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程已经完成：" + System.currentTimeMillis());
            }
        });



        new Thread(new Task(cyclicBarrier), "Thread1").start();
        new Thread(new Task(cyclicBarrier), "Thread2").start();
        new Thread(new Task(cyclicBarrier), "Thread3").start();
        new Thread(new Task(cyclicBarrier), "Thread1").start();

        cyclicBarrier.reset();
        new Thread(new Task(cyclicBarrier), "Thread1").start();
        new Thread(new Task(cyclicBarrier), "Thread2").start();
        new Thread(new Task(cyclicBarrier), "Thread3").start();

    }

    class Task implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + " 已到达 " + System.currentTimeMillis());
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
