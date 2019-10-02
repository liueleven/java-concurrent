package cn.eleven.concurrent.usage.pool;

/**
 * @description: 一定要写注释啊
 * @date: 2019-09-17 14:04
 * @author: 十一
 */
public class MyPoolTest {

    public static void main(String[] args) {

        for (int i=0; i<10; i++) {
            MyPool.getInstance().execute(new Sms(i));
        }

    }

    static class Sms implements Runnable {



        private int num;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "--" + num);
        }

        public Sms(int num) {
            this.num = num;
        }
    }
}
