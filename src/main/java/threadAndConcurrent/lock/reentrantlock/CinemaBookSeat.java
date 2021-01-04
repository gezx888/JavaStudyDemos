package threadAndConcurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示多线程预定电影票：可见只有对资源进行加锁后，才能一个一个线程去预定，要不然会出现并发问题
 * @author gezx
 * @date 2021/1/4 16:02
 */
public class CinemaBookSeat {
    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始预定座位");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"结束预定");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
    }
}
