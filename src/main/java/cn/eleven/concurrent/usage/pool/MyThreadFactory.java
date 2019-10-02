package cn.eleven.concurrent.usage.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 一定要写注释啊
 * @date: 2019-09-17 13:58
 * @author: 十一
 */
public class MyThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;

    public MyThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix + "-pool-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(true);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }

        return t;
    }
}
