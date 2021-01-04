package threadAndConcurrent.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示锁里面的 公平锁 与 非公平锁：这个是指是否需要排队什么的，对于公平锁很简单肯定不能插队的，对于非公平锁，一般需要看任务队列里面的首节点位置是否是 写锁，如果是写锁，
 *      则不允许插入，否则会造成写锁的阻塞饥饿，一直得不到执行，如果头结点是读锁，则允许读锁进行插队（可以设计一个这样的代码例子进行演示）
 *      读写锁并发执行机制： 要么多读，要么一写；不能出现写读共存，或是多写等情况
 * @author gezx
 * @date 2021/1/4 16:24
 */
public class FairLock {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Job implements Runnable{

    PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始打印");
        printQueue.printJob();
        System.out.println(Thread.currentThread().getName()+"打印结束");
    }
}

class PrintQueue{
    private  Lock lock = new ReentrantLock(false);

    public void printJob(){
        lock.lock();
        try {
            int count = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName()+"正在打印，需要"+count);
            Thread.sleep(count*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            int count = new Random().nextInt(10) + 1;
            System.out.println(Thread.currentThread().getName()+"正在打印，需要"+count);
            Thread.sleep(count*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
