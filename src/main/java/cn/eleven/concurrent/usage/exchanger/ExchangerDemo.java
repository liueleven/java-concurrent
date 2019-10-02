package cn.eleven.concurrent.usage.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 一定要写注释啊
 * @date: 2019-09-17 14:58
 * @author: 十一
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            try {
                // 男士说
                String exchangeBefore = "我是男的...";
                String boy = exchanger.exchange(exchangeBefore);
                System.out.println("交换前：" + exchangeBefore + "交换后：" + boy);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            try {
                // 女士说
                String exchangeBefore = "我是女的...";
                String girl = exchanger.exchange("我是女的...");

                System.out.println("交换前：" + exchangeBefore + "交换后：" + girl);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}