package javaBase.thread.threadobjectclasscommonmethods;

/**
 * @className: WaitNotifyPrintOddEvenSyn
 * @description:    该类完成两个线程交替打印 0-100 的奇偶数的任务，交替打印，本来使用synchronized锁来进行两个线程间的通信：即符合当前
 *  *                      线程该打印的内容则输出打印，否则不输出，该种方案必须持有到锁进行打印，如果一个线程经常抢到锁，将会使得哦另外一个
 *  *                      线程经常拿不到锁 从而不能输出，从而效率低下，比较好的方案是：使用 wait/notify 线程通信机制
 * @author: gezx
 * @date: 2021/3/24
 **/
public class WaitNotifyPrintOddEvenSyn {

    private static int count = 1;
    private static final Object lock = new Object();

    // 新建两个线程
    // 一个只处理偶数，另一个只处理奇数（奇偶数的判断可以使用 对2取余==0？但这个没有位运算效率高）
    // 使用synchronized来通信
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count < 100){
                    synchronized(lock){
                        if((count & 1) == 0){
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        },"偶数").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count < 100){
                    synchronized(lock){
                        if((count & 1) == 1){
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        },"奇数").start();
    }

}
