package threadAndConcurrent.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 该类主要演示多线程关于读写锁的用法:从运行结果来看，读锁可以支持多线程并发读取，但写锁只能是一个线程写入，需要等其中一个
 *                  线程释放了写锁之后，另外一个线程才能获取到
 * @author: gezx
 * @date: 2021/1/4 20:37
 */
public class CinemaReadWrite {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    private static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> read(),"Thread1").start();
        new Thread(() -> read(),"Thread2").start();
        new Thread(() -> write(),"Thread3").start();
        new Thread(() -> write(),"Thread4").start();
    }
}
