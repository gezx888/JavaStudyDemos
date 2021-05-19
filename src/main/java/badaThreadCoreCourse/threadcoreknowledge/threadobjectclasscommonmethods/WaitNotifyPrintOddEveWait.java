package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * @description:  两个线程交替打印 0~100范围内的奇偶数，该类通过使用 wait notify方法进行线程间的通信，这样相比较于仅仅使用 synchronized 关键字效率会更高！
 *                     因为避免了同一个线程多次拿到锁 但又不符合打印条件而浪费拿到锁的机会
 *                     使用 wait notify，方法，每一次拿到锁就打印，打印完就让出锁，让另外一个线程打印
 *                     两点注意：1、在每一次调用 wait方法前检查一下任务是否已经打印完毕，防止一直阻塞的情况发生；因为每一次notify前也是检查了
 *                     2、wait notify方法Java设计者在设计的时候本身设计的时候就是在 synchronized 关键字内部使用的，因为在外部使用的话可能发生死锁等
 *                             避免死锁 一直阻塞等问题
 * @author: gezx
 * @date: 2021/5/19 22:13
 */
public class WaitNotifyPrintOddEveWait {

    private static int count = 0;
    private static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        PrintRunner run = new PrintRunner();
        new Thread(run,"偶数线程").start();

        TimeUnit.MILLISECONDS.sleep(20);          // 让偶数线程先启动执行

        new Thread(run,"奇数线程").start();
    }

    static class PrintRunner implements Runnable{

        @Override
        public void run() {
            while (count<=100){
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName() + ":" + count++);

                    obj.notify();

                    if(count<=100){                 // 这个条件判断可以防止最后打印偶数的线程一直阻塞的情况，可以注释该行代码看控制台效果
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
