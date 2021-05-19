package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @description:    演示wait notifyAll方法，线程先start并不代表线程先启动执行
 *                      3个线程，线程1和线程2首先被阻塞，线程3唤醒它们
 * @author: gezx
 * @date: 2021/5/19 21:06
 */
public class WaitNotifyAll implements Runnable{

    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll run = new WaitNotifyAll();
        Thread th1 = new Thread(run);
        Thread th2 = new Thread(run);
        Thread th3 = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "调用了notifyAll方法");
                object.notifyAll();
            }
        });

        th1.start();
        th2.start();

     //   TimeUnit.MILLISECONDS.sleep(50);

        th3.start();

    }



    @Override
    public void run() {
        synchronized (object){
            System.out.println(Thread.currentThread().getName() + "拿到锁了开始执行");

            System.out.println(Thread.currentThread().getName() + "开始 wait");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束 wait");

        }
    }
}
