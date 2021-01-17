package threadAndConcurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @description: 此类用来演示线程池工作的时候可能发生内存溢出的情况：通过新建一个只有单个线程的线程池去执行任务
 *                  然后每个任务都是一直在休眠很难完成，这样就将造成内存溢出
 * @author: gezx
 * @date: 2020/12/27 18:39
 */
public class FixedThreadPoolOOM {

    private  static  ExecutorService fixedThreadPool =  Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            fixedThreadPool.execute(new SubTask());

        }
    }

    static class SubTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
