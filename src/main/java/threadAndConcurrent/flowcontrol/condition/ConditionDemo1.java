package threadAndConcurrent.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: ConditionDemo1
 * @description:  演示Condition类的基本用法： 一般是某个线程没有达到执行某个任务的条件，那就需要等待，等条件满足后继续往下执行
 * @author: gezx
 * @date: 2021/2/19
 **/
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("条件不满足，开始await");
            condition.await();
            System.out.println("条件满足了，开始执行后续任务");
        }finally {
            lock.unlock();
        }
    }

    void method2(){
        lock.lock();
        try {
            System.out.println("准备工作完成，开始唤醒其他线程");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    conditionDemo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        conditionDemo1.method1();
    }
}
