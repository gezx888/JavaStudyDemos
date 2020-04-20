package thread;

/**
 * @description: 通过继承线程类Thread并重写run方法创建线程
 * @author: gezx
 * @date: 2020/4/20 22:18
 */
public class ThreadDemo1 {
    // 下面main方法所在线程--主线程
    public static void main(String[] args) {
        MyThread myThread = new MyThread();  // 创建并初始化MyThread类型的对象
        myThread.start(); // 启动一个新的线程，这时将开辟一个新的线程栈内存，并且线程的run方法将会被自动调用
    }

    /**
     * 这里将线程类写成了内部类，但为了方便直接在上面main方法中new线程对象，所以需要定义成静态内部类
     */
    static class MyThread extends Thread{
        // 无参构造
        MyThread(){ }
        // 申明一个带参构造方法
        MyThread(String name){
            super(name);
        }
        // 重写run方法
        public void run(){
            System.out.println(Thread.currentThread().getName());
            for(int count=1,row=1;row<10;count++,row++){
                for(int i=0;i<count;i++){
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }
}