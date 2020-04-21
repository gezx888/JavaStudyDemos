package thread;

/**
 * @author gezx
 * @date 2020/4/21 11:11
 */
public class ThreadDemo5 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread2());
        t1.setName("t1");
        t1.setPriority(1);


        Thread t2 = new Thread(new MyThread2());
        t2.setName("t2");
        t2.setPriority(10);

        t1.start();  // 启动线程
        t2.start();  // 启动线程

        // 设置线程优先级


    }
}

class MyThread2 implements Runnable{
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