package badaThreadCoreCourse.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @className: Wait
 * @description:            演示 wait 和 notify的相关方法，可以研究代码执行顺序，证明wait方法释放锁的特性 —— 所以需要一个公共的临界资源
 *
 *                          通过更换两个线程的启动顺序将会得到不的控制台输出
 * @author: gezx
 * @date: 2021/5/19
 **/
public class Wait {

    private static final Object object = new Object();       // 定义一个对象，代表某种锁，因为我们的锁是对象级别，也就是锁是属于哪一个对象的锁

    /**
     * 新建两个线程，其中一个线程拿到锁之后，调用wait方法，进入wait状态，通过主线程调用sleep方法。，是的另外一个线程后一点启动，并且而后调用notify方法用于唤醒前面线程
     */
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "获取到了锁，开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "重新获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread2.start();
//        Thread.sleep(50);
//        TimeUnit.MILLISECONDS.sleep(5000);
        thread1.start();
    }



}
