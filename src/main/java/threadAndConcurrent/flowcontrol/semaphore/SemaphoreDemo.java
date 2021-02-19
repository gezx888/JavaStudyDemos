package threadAndConcurrent.flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @className: SemaPhoreDemo
 * @description:    主要演示 Semaphore 类的用法，信号量，情景：污染水厂排污许可证的获取，该类主要用来限制同一个时间 可以做任务的线程个数，一些注意点：
 *                         1、可与在构造方法内部传入大于1的参数，表示需要拿多个许可证； 2、一般拿几个就释放几个 ，编码规约 3、可以再多个线程之间再去释放 许可证
 *                         4、一般设置为公平的 传 true
 * @author: gezx
 * @date: 2021/2/19
 **/
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(5,true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {  // for循环往线程池提交执行任务
            service.submit(new Task());
        }
        service.shutdown();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release(2);
        }
    }
}
