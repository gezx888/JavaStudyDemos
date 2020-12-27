package threadAndConcurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @description: 1.0
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
