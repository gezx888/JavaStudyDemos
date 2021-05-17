package badaThreadCoreCourse.threadcoreknowledge.createthreads;

/**
 * @description:  演示通过实现Runnable接口创建新线程
 * @author: gezx
 * @date: 2021/5/17 22:52
 */
public class RunnableStyle {
    public static void main(String[] args) throws InterruptedException {
        Task task = new RunnableStyle().new Task();            // 或者使用静态内部类的方式实现
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
    }

    class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("通过实现Runnable接口创建新线程");
            System.out.println(Thread.currentThread().getName());
        }
    }
}
