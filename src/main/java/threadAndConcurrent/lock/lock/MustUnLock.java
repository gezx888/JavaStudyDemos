package threadAndConcurrent.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:  Lock不会像synchronized一样，异常的时候自动释放锁，所以最佳实践（编程规约）是：一般都需要在finally中手动释放锁，
 *                  以便保证发生异常的时候锁可以被及时释放
 *                  ReentrantLock 为 lock的常见实现
 * @author: gezx
 * @date: 2021/1/3 16:27
 */
public class MustUnLock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //  获取本锁保护的资源，进行业务代码逻辑
            System.out.println(Thread.currentThread().getName()+"开始执行本锁需要执行的业务逻辑代码");
        }finally {
            lock.unlock();  // 一般需要在这里手动释放锁
        }
    }
}
