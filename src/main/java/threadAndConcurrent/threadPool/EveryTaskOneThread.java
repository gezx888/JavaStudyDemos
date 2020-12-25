package threadAndConcurrent.threadPool;

/**
 * 该类用来演示线程的简单用法，并且一个任务用一个线程来执行
 * @author gezx
 * @date 2020/12/25 16:10
 */
public class EveryTaskOneThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
