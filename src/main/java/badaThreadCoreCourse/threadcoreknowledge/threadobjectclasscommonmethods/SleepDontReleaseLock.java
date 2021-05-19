package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: SleepDontReleaseLock
 * @description:           该类演示 ReentrantLock sleep方法期间不释放锁  （可以通过起来两个线程来证明，一个线程sleep 看另一个线程能否拿到锁，不能的话说明不释放锁）
 * @author: gezx
 * @date: 2021/5/19
 **/
public class SleepDontReleaseLock implements Runnable{

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SleepDontReleaseLock());
        Thread thread2 = new Thread(new SleepDontReleaseLock());
        thread.start();
        thread2.start();
    }
}
