package threadAndConcurrent.flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @className: CyclicBarrierDemo
 * @description:  主要演示CyclicBarrier类的用法，循环栅栏 —— 情景，游乐场过山车等人满发车，就是等到若干个线程到了之后再一起发车去干接下的事
 *                      区别于 contDownLatch类，这个是倒数线程的，并且这个类可以循环使用，比如for循环有10个，但是这个类只要等足5个，那么这个
 *                      可以使用两次即可以循环使用。
 * @author: gezx
 * @date: 2021/2/19
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("大家都到了，现在统一出发");
            }
        });

        for (int i = 0; i < 5; i++) {
            new Thread(new Task(i,barrier)).start();
        }
    }

    static class Task implements Runnable{

        int count;
        CyclicBarrier cyclicBarrier;

        public Task(int count, CyclicBarrier cyclicBarrier) {
            this.count = count;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + count + "前往集合地点");
            try {
                Thread.sleep((long)(Math.random()*10000));

                System.out.println("线程" + count + "开始等其他人达到集合地点");
                cyclicBarrier.await();
                System.out.println("线程" + count + "开始出发了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


}
