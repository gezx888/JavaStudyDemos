package threadAndConcurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 同样演示ReentrantLock为可重入锁
 * @author gezx
 * @date 2021/1/4 15:58
 */
public class GetHoldCount {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
         lock.unlock();
        System.out.println(lock.getHoldCount());
         lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
