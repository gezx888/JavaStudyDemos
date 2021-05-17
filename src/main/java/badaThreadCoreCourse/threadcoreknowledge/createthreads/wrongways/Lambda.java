package badaThreadCoreCourse.threadcoreknowledge.createthreads.wrongways;

/**
 * @description:   lambda表达式创建线程
 * @author: gezx
 * @date: 2021/5/17 23:27
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
