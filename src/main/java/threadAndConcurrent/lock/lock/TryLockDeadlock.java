package threadAndConcurrent.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 用tryLock() 方法可以用来避免死锁
 * @author: gezx
 * @date: 2021/1/3 16:51
 */
public class TryLockDeadlock  implements Runnable{
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();
    int flag = 1;

    public static void main(String[] args) {
        TryLockDeadlock r1 = new TryLockDeadlock();
        TryLockDeadlock r2 = new TryLockDeadlock();
        r1.flag=1;
        r2.flag=0;
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if(flag==1){
                try {
                    if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        try{
                            System.out.println("线程1成功获取到了锁1");
                            if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程1成功获取到了锁2");
                                    System.out.println("线程1成功获取到了两把锁");
                                    break;
                                }finally {
                                    lock2.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            }else {
                                System.out.println("线程1获取锁2失败，已重试");
                            }
                        }finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程1获取锁1失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(flag==0){
                try {
                    if(lock2.tryLock(2000, TimeUnit.MILLISECONDS)){
                        try{
                            System.out.println("线程2成功获取到了锁2");
                            if(lock1.tryLock(2000,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程2成功获取到了锁1");
                                    System.out.println("线程2成功获取到了两把锁");
                                    break;
                                }finally {
                                    lock1.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            }else {
                                System.out.println("线程2获取锁1失败，已重试");
                            }
                        }finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程2取锁2失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
