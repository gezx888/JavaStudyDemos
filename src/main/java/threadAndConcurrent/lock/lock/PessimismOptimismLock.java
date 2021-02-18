package threadAndConcurrent.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: PessimismOptimismLock
 * @description:  该类主要用来演示锁的种类：悲观锁 & 乐观锁 —— 原子类就是一种乐观锁的代表   synchronized锁为一种悲观锁
 * @author: gezx
 * @date: 2021/2/18
 **/
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    public synchronized void testMethod(){
        a++;
    }
}
