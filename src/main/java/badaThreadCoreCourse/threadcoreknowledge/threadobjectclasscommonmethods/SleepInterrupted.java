package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @className: SleepInterrupted
 * @description:               该类演示每隔1秒输出当前时间，然后被中断
 *                      主要需要学习 TimeUnit.HOURS.sleep() 这种优雅写法
 * @author: gezx
 * @date: 2021/5/19
 **/
public class SleepInterrupted implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6000);
        thread.interrupt();
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了");
                e.printStackTrace();
            }
        }
    }
}
