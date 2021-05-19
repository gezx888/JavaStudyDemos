package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @description:        用两个线程交替打印 0·100范围内的 奇偶数  该类通过synchronized关键字实现线程间通信 实现，这种方法效率比较低
 * @author: gezx
 * @date: 2021/5/19 21:17
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count = 0;
    private static final Object resource = new Object();          // 代表某中临界资源

    /**
     * 步骤1：新建两个线程，一个线程负责打印奇数，另一个线程负责打印偶数
     * 2：只要拿到锁并且符合自己需要打印的值就打印
     * 3：直到count超过100 跳出打印任务循环
     * 备注：抢锁，所以有可能存在专门一个线程抢到锁的情况，但是又不符合他打印的任务，这样的话就浪费了机会，所以效率相比较 wait notify来说更加低
     */
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (resource){
                        if((count&1) == 1){
                           System.out.println(Thread.currentThread().getName() + " " + count++);
                        }
//                        if(count == 100){                         // 这三行代码可以证明专门一个线程抢到锁的情况，但是又不符合他打印的任务，这样的话就浪费了机会
//                            System.out.println("=====");
//                        }
                    }
                }
            }
        },"打印奇数线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (resource){
                        if((count&1) == 0){
                            System.out.println(Thread.currentThread().getName() + " " + count++);
                        }
                    }
                }
            }
        },"打印偶数线程").start();
    }



}
