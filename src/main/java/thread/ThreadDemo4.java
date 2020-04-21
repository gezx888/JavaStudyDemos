package thread;

import org.junit.Test;

/**
 * 本类主要演示线程里面一些方法的使用
 * @author gezx
 * @date 2020/4/21 10:05
 */
public class ThreadDemo4 {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 myThread = new MyThread1();
        Thread thread = new Thread(myThread);
        thread.setName("t1");  // 设置线程名字

        thread.start();

        Thread.sleep(3000); // 让主线程休眠3秒

        // 主线程中的for循环
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName() +  "输出------" + i);
        }
    }

    /**
     * 测试join方法，这个方法是起到合并线程的效果，是一个成员方法，在一个线程中调用另一个线程的join方法，
     * 程序将等到另一个线程执行完了之后再去执行本线程 (感觉就像单线程执行一样)
     */
    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(new MyThread1());
        t1.setName("t1");
        t1.start();  // 启动线程

        t1.join(); // 本方法也会抛出中断异常

        for(int i=0;i<10;i++){
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() +  "输出------" + i);
        }
    }

    /**
     * 测试yield() 方法，让出当前的cpu资源，给相同优先级的线程cpu执行时间，与sleep()方法效果一样，
     * 但是不能控制时间，并且不能保证效果
     */
    @Test
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(new MyThread1());
        t1.setName("t1");
        t1.start();  // 启动线程

        for(int i=0;i<10;i++){
            Thread.yield();
            Thread.sleep(500);

            System.out.println(Thread.currentThread().getName() +  "输出------" + i);
        }
    }

    @Test
    public void test3() throws InterruptedException {
        Thread t1 = new Thread(new MyThread1());
        t1.setName("t1");
        t1.start();  // 启动线程

        Thread t2 = new Thread(new MyThread1());
        t2.setName("t2");
        t2.start();  // 启动线程

        // 设置线程优先级
        t1.setPriority(4);
        t2.setPriority(6);

    }


}


class MyThread1 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +  "输出------" + i);
        }
    }
}
