package javaBase.thread;

/**
 * @description: 通过实现Runnable接口并实现run方法创建线程
 * @author: gezx
 * @date: 2020/4/20 22:57
 */
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread(); // 创建并初始化对象
        Thread thread = new Thread(myThread); // 通过Thread创建新线程
        thread.start();  // 启动线程
        for(int i=0;i<10;i++){
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "------" + i);
        }
    }
}

 class MyThread implements Runnable{
    @Override
    public void run(){
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(100); // 注意这里只能通过try catch进行异常处理
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "------" + i);
        }
    }
}
