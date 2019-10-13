package cn.eleven.concurrent.usage.semaphore;

import cn.eleven.concurrent.usage.pool.MyPool;
import cn.eleven.concurrent.usage.pool.MyThreadFactory;

import java.util.concurrent.*;

/**
 * @description: 一定要写注释啊
 * @date: 2019-09-17 14:53
 * @author: 十一
 */
public class SemaphoreDemo {

    private static ExecutorService pool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new MyThreadFactory("my-test"));

    public static void main(String[] args) {

        // 同时获取的数量
        Semaphore semaphore = new Semaphore(3);

        //模拟用户访问
        for (int i = 0; i < 10;  i++) {
            final int No = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    try {
                        // 拿到许可
                        semaphore.acquire();
                        System.out.println("Accessing: " + No);
                        Thread.sleep(2000);
                        // 释放
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            // 放到线程池中执行,可以改变线程池的core数，观察结果
            pool.execute(runnable);
        }
    }
}