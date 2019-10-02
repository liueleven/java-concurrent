package cn.eleven.concurrent.usage.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 一定要写注释啊
 * @date: 2019-09-17 15:06
 * @author: 十一
 */
public class FutureDemo {
    ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureDemo futureDemo = new FutureDemo();
        futureDemo.subJob();
    }

    public void subJob() throws ExecutionException, InterruptedException {
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Callable<String> callable = new FutureJob(i);
            Future<String> future = pool.submit(callable);
            futureList.add(future);
        }

        for (Future future : futureList) {
            System.out.println(future.get());
        }

    }


    class FutureJob implements Callable<String> {

        private int num;

        public FutureJob(int num) {
            this.num = num;
        }

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName() + "--" + num;
        }
    }
}

