package threadAndConcurrent.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁并发执行机制： 要么多读，要么一写；不能出现写读共存，或是多写等情况,从参数控制公平与非公平锁的情况来看，
 *                  写锁都不会去插对的，需要得到持有锁的其他线程释放了锁之后，拿到了同步资源锁之后，才去执行写入逻辑
 * @author: gezx
 * @date: 2021/1/4 20:37
 */
public class CinemaReadWriteQueue {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
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
        new Thread(() -> write(),"Thread1").start();
        new Thread(() -> read(),"Thread2").start();
        new Thread(() -> read(),"Thread3").start();
        new Thread(() -> write(),"Thread4").start();
        new Thread(() -> read(),"Thread5").start();
    }
}
