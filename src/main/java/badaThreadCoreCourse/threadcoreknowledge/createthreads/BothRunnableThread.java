package badaThreadCoreCourse.threadcoreknowledge.createthreads;

/**
 * @description:    演示同时使用thread 和 Runnable两种可结合后的一种现象 : 控制台输出——我来自Thread
 *                  解析：其实下面这是一种匿名内部类的方式，并且重写了Thread类的 run 方法,那么这样的话，分类的那三行代码将不会执行。、
 *                  所以传入的可运行的Runnable型的target不会被执行，所以输出上面的现象
 * @author: gezx
 * @date: 2021/5/17 23:14
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }

}
