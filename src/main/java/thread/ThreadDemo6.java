package thread;

/**
 * @description: 本类主要演示死锁的形成
 * @author: gezx
 * @date: 2020/4/22 23:55
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        // 共享数据对象(o1与o2对象内存地址一样)
        Object o1 = new Object();
        Object o2 = new Object();

        Thread t1 = new Thread(new T1(o1, o2));
        Thread t2 = new Thread(new T2(o1, o2));

        t1.start();
        t2.start();
    }
}
class T1 implements Runnable{
    private Object o1;
    private Object o2;
    T1(Object o1, Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }
    @Override
    public void run() {
        synchronized(o1){
            try {
                Thread.sleep(1000); // 将本线程休眠1秒下来，保证让对方线程执行锁住另一个对象
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(o2){
                System.out.println(Thread.currentThread().getName() + "----->执行中" );
            }
        }
    }
}
class T2 implements Runnable{
    private Object o1;
    private Object o2;
    T2(Object o1, Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }
    @Override
    public void run() {
        synchronized(o2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(o1){
                System.out.println(Thread.currentThread().getName() + "----->执行中" );
            }
        }
    }
}
