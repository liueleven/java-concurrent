package cn.eleven.concurrent.usage.pool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description: 一定要写注释啊
 * @date: 2019-10-13 20:43
 * @author: 十一
 */
public class SchedulerPoolDemo {

    /**
     * 创建工作线程数为1
     * 默认使用AbortPolicy策略
     */
    private static ScheduledExecutorService schedulerPool = Executors.newSingleThreadScheduledExecutor();
    public static void main(String[] args) {
        /**
         * command 执行的任务
         * initialDelay 延迟多久
         * delay 执行任务的间隔
         * unit 时间单位
         */
        schedulerPool.scheduleWithFixedDelay(new MyTask(), 3,1, TimeUnit.SECONDS);
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            try {
                // 因为核心线程数是1，所以下一任务来临会被阻塞，直到上一个任务完成
                int i = new Random().nextInt(5);
                System.out.println("等待 " + i + " 秒");
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis()/1000);
        }
    }
}
