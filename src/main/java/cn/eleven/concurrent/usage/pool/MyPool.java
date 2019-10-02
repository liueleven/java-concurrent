package cn.eleven.concurrent.usage.pool;

import java.util.concurrent.*;

/**
 * @description: 一定要写注释啊
 * @date: 2019-09-17 13:59
 * @author: 十一
 */
public class MyPool {


    private ExecutorService pool = new ThreadPoolExecutor(10, 10,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(),
        new MyThreadFactory("my-test"));

    private MyPool() {

    }

    private static class SingletonInstance {
        private static final MyPool instance = new MyPool();
    }

    public static MyPool getInstance() {
        return SingletonInstance.instance;
    }

    public void execute(Runnable runnable) {
        pool.execute(runnable);
    }
}
