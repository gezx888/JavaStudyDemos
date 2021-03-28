package javaBase.thread.threadobjectclasscommonmethods;

/**
 * @className: WaitNotifyPrintOddEveWait
 * @description:        该类完成两个线程交替打印 1-100 的奇偶数的任务，交替打印，本来使用synchronized锁来进行两个线程间的通信：即符合当前
 *                      线程该打印的内容则输出打印，否则不输出，该种方案必须持有到锁进行打印，如果一个线程经常抢到锁，将会使得哦另外一个
 *                      线程经常拿不到锁 从而不能输出，从而效率低下，下面使用比较好的方案是：使用 wait/notify 线程通信机制
 * @author: gezx
 * @date: 2021/3/24
 **/
public class WaitNotifyPrintOddEveWait {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new TurningRunner(), "偶数").start();
        new Thread(new TurningRunner(), "奇数").start();
    }

    // 1、拿到锁，符合打印条件我们就打印
    // 2、打印完，唤醒其他线程，自己就休眠
    static class TurningRunner implements Runnable{

        @Override
        public void run() {
            while(count <= 100){
                synchronized(lock){
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if(count <= 100){
                        // 如果任务还没结束，就让出当前锁，让那个另外一个线程拿到锁打印，并自己休眠
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
