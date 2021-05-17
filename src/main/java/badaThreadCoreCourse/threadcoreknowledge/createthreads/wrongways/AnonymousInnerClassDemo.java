package badaThreadCoreCourse.threadcoreknowledge.createthreads.wrongways;

/**
 * @description:        该包下面的几个类也都可以启动新线程，但不属于oracle官文文档的创建新线程的说法，只是形式不同罢了
 *                      该类 为匿名内部类的方式
 * @author: gezx
 * @date: 2021/5/17 23:24
 */
public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
