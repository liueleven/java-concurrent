package cn.eleven.concurrent.sync.syncdemo;

/**
 * @description: 演示并发问题
 * @date: 2019-09-16 12:28
 * @author: 十一
 */
public class SynchronizedDemo1 implements Runnable{

    private int count = 5;

    @Override
    public void run() {
        try {
            // 为了效果，否则太快了
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        // 打印结果会出现多个相同的count值
        System.out.println(count);
    }

    public static void main(String[] args) {
        SynchronizedDemo1 s1 = new SynchronizedDemo1();

        for (int i=0; i< 5; i++) {
            new Thread(s1).start();
        }

    }
}
