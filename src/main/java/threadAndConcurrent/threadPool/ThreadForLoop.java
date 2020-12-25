package threadAndConcurrent.threadPool;

/**
 * @author gezx
 * @date 2020/12/25 19:05
 */
public class ThreadForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new EveryTaskOneThread.Task());
            thread.start();
        }
    }
}
