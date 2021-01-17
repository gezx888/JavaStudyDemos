package threadAndConcurrent.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 该类主要用来演示非公平与公平锁的ReentrantReadWriteLock的插队与否的策略：发现公平锁不会插队，非公平锁锁允许插队，但当队列头结点为
 *                  写锁时候这个时候是不允许读锁插队，而当头节点为读锁的时候是允许读锁进行插队。
 * @author: gezx
 * @date: 2021/1/4 21:07
 */
public class NonfairBargeDemo {
    // 可以改变下面这个参数：false与true来观察控制台输出情况
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read(){
        System.out.println(Thread.currentThread().getName()+"开始尝试获取读锁");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到读锁，正在读取");
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    private static void write(){
        System.out.println(Thread.currentThread().getName()+"开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到写锁，正在写入");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(),"Thread1").start();
        new Thread(() -> read(),"Thread2").start();
        new Thread(() -> read(),"Thread3").start();
        new Thread(() -> write(),"Thread4").start();
        new Thread(() -> read(),"Thread5").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread[] threads = new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    threads[i] = new Thread(() -> read(),"子线程创建的Thread"+i);
                }
                for (int i = 0; i < 1000; i++) {
                    threads[i].start();
                }
            }
        }).start();
    }
}
