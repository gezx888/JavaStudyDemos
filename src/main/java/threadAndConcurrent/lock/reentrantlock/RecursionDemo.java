package threadAndConcurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *   用递归方法调用的方式演示 ReentrantLock锁为可重入锁：就是同一把锁不需要释放便可以再次进入该锁的锁称为可重入锁
 * @author gezx
 * @date 2021/1/4 15:47
 */
public class RecursionDemo {
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource(){
        lock.lock();
        try {
            System.out.println("已经对资源进行了上锁同步处理了");
            if(lock.getHoldCount()<5){
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
