package badaThreadCoreCourse.threadcoreknowledge.sixstates;

/**
 * @description:    演示线程的几中状态
 * @author: gezx
 * @date: 2021/5/17 23:41
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出Timed_Waiting状态，因为正在执行Thread.sleep(1000);
        System.out.println(thread1.getState());
        //打印出BLOCKED状态，因为thread2想拿得到sync()的锁却拿不到
        System.out.println(thread2.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出TIMED_WAITING状态，因为执行了wait()
        System.out.println(thread1.getState());

    }



    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);     // 让拿到锁的线程休眠1秒
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
