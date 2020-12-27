package threadAndConcurrent.threadPool;

/**
 * 演示：当任务量少时，通过for循环创建多个线程来执行任务（这是在演示线程池之前的一个演示，以进行两者之间的一个对比）
 * @author gezx
 * @date 2020/12/25 19:05
 */
public class ThreadForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("=====执行任务了====");
        }
    }
}
