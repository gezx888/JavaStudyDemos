package threadAndConcurrent.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 演示lock的一个可中断锁的方法
 * @author: gezx
 * @date: 2021/1/3 18:13
 */
public class LockInterruptibly implements Runnable{
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread1 = new Thread(lockInterruptibly);
        Thread thread2 = new Thread(lockInterruptibly);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"尝试获取锁");
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+"获取到了锁");
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"睡眠期间被中断了");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"获得锁期间被中断了");
        }
    }
}
