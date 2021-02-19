package threadAndConcurrent.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className: CountDownLatchDemo2
 * @description:  演示 CountDownLatch的另一种使用用法：用来多等一的情况：模拟100米跑步，五个运动员都准备好了，只等待裁判一声发令枪响
 *                      同样是一个发令枪响的倒数事件
 * @author: gezx
 * @date: 2021/2/19
 **/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch beginLatch = new CountDownLatch(1);   // 因为倒数的事件，发令枪响这有一个，所以参数传 1
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("NO." + no +"准备好了，等待枪响、、、");
                    try {
                        beginLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("NO." + no + "开始跑了");
                }
            };
         service.submit(runnable);
        }

        Thread.sleep(5000);  // 用来模拟裁判检查场地等事务逻辑处理
        System.out.println("发令枪响，比赛激烈的开始了..");
        beginLatch.countDown();
    }
}
