package threadAndConcurrent.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock的用法，演示被打断: 在输出打印字符的方法中需要对关键方法进行加锁，要不然会出现字符错乱，这是锁的最基本用法
 * @author gezx
 * @date 2021/1/4 15:14
 */
public class LockDemo {
    public static void main(String[] args) {
        new LockDemo().init();
    }

    private void init(){
        final Output output = new Output();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    output.output("悟空");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    output.output("大师兄");
                }
            }
        }).start();

    }

    static class Output{
        Lock lock = new ReentrantLock();
        public void output(String name){
            int length = name.length();
            try {
                lock.lock();
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }

        }
    }

}
