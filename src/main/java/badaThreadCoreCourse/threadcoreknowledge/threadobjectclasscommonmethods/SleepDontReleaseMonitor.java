package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @className: SleepDontReleaseMonitor
 * @description:        该类演示 线程执行sleep方法期间不会释放 synchronized 监视器的锁，需要等到代码正常结束以后才释放锁
 * @author: gezx
 * @date: 2021/5/19
 **/
public class SleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println(Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "释放了锁");
    }
}
