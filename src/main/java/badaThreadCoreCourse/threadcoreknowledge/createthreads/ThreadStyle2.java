package badaThreadCoreCourse.threadcoreknowledge.createthreads;

/**
 * @description:    该类演示 —— 通过继承Thread类，重写他里面的run方法来实现创建线程,通过调用start()方法启动线程
 * @author: gezx
 * @date: 2021/5/17 22:22
 */
public class ThreadStyle2 {
    public static void main(String[] args) {
        Task task = new ThreadStyle2().new Task();
        task.start();
        System.out.println(Thread.currentThread().getName());
    }

    class Task extends Thread{
        @Override
        public void run() {
            System.out.println("通过继承Thread类创建线程");
            System.out.println(Thread.currentThread().getName());
        }
    }
}
