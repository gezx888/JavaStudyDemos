package threadAndConcurrent.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 演示ReentrantReadWriteLock可以降级，但不可以升级
 * @author: gezx
 * @date: 2021/1/17 23:33
 */
public class Upgrading {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void readUpgrading(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了读锁，正在读取");
            Thread.sleep(1000);
            System.out.println("想从读锁升级为写锁，但是这样不行，升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取到了写锁，升级成功");  // 能输出这句话，表示读锁能成功升级到写锁
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    private static void writeDowngrading(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了写锁，正在写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println("在不释放写锁的情况下，直接获取读锁，能输出该句话表示不会阻塞，写锁可以直接降级到读锁成功");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示写锁的降级，这个是可以在不释放写锁的情况下，直接获取读锁，降级是能成功的");
        Thread thread1 = new Thread(() -> writeDowngrading(),"Thread1");
        thread1.start();
        thread1.join();
        System.out.println("---------------");
        System.out.println("演示读锁升级成写锁是不可行的");
        Thread thread2 = new Thread(() -> readUpgrading(),"Thread2");
        thread2.start();
    }

}
