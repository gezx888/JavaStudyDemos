package threadAndConcurrent.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: CountDownLatchDemo1
 * @description:   该类主要用来演示 CountDownLatch 的用法：比如 工厂中，质检，5个工人检查，所有人都认为通过，才通过；
 *                  所以，CountDownLatch这个类主要是用来 针对 某一个需要倒数的事件  需要区别于：CyclicBarrier 这个类，这个类是用来针对 线程 ，后面会有他的用法演示
 *                      一等多的场景
 * @author: gezx
 * @date: 2021/2/19
 **/
public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {  // 用一个for循环去往线程池中提交执行任务
             final int no = i+1;
             Runnable runnable = new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Thread.sleep((long) (Math.random()*10000));   //模拟检查工序耗时
                         System.out.println("No." + no + "完成了检查");
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }finally {
                         countDownLatch.countDown();
                     }
                 }
             };
             executorService.submit(runnable);
        }

        System.out.println("等待五个人检查完....");
        countDownLatch.await();   // 主线程 等待 其他子线程完成任务检查工作
        System.out.println("所有人都完成了检查工作，即进入下一个工作环节");
    }
}
