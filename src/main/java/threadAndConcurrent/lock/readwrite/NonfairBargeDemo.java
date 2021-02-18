package threadAndConcurrent.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 该类主要用来演示非公平与公平锁的ReentrantReadWriteLock的插队与否的策略：1、由参数控制；2、true 公平锁时：子线程创建的线程不会插队，
 *                      3、false 非公平锁时：子线程创建的线程会在下面情况下进行插队以提高效率，即是当存放线程的阻塞队列的头结点为读锁的时候，这个时候
 *                      为了浪费唤醒头结点的读锁而引起的效率降低，新建的子线程（抢读锁）会插队从而立即进行读取；注意当头结点为写锁时候，就不会插队，避免
 *                      造成饥饿等待。
 * @author: gezx
 * @date: 2021/1/4 21:07
 */
public class NonfairBargeDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void read(){
        System.out.println(Thread.currentThread().getName()+"尝试获取读锁");
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"得到读锁，正在读取");
            Thread.sleep(20);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放了读锁");
            readLock.unlock();
        }
    }

    public static void write(){
        System.out.println(Thread.currentThread().getName()+"尝试获取写锁");
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"得到了写锁，正在写入");
            Thread.sleep(40);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread[] threads = new Thread[1000];
                // 初始化1000个线程去读，营造插队环境
                for (int i = 0; i < 1000; i++) {
                    threads[i] = new Thread(() -> read(),"子线程创建的Thread" + i);
                }
                for (int i = 0; i < 1000; i++) {
                    threads[i].start();
                }
            }
        }).start();
    }
}
