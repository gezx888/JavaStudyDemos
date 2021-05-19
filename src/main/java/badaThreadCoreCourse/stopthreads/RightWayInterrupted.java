package badaThreadCoreCourse.stopthreads;

/**
 * @className: RightWayInterrupted
 * @description:        注意 Thread.interrupted()静态方法的目标对象是 “运行该语句的当前线程对象” 而不管方法来自于哪个对象
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RightWayInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (; ; ) {

                        }
                    }
                }
        );
        // 启动线程
        thread.start();
        //设置中断标志
        thread.interrupt();
        //获取中断标志
        System.out.println("isInterrupted：" + thread.isInterrupted());
        //获取中断标志并重置
        System.out.println("isInterrupted：" + thread.interrupted());     // 运行该语句的线程对象都是 main线程，所以打印false
        //获取中断标志并重置
        System.out.println("isInterrupted：" + Thread.interrupted());        // 运行该语句的线程对象都是 main线程，所以打印false
        //获取中断标志
        System.out.println("isInterrupted：" + thread.isInterrupted());
        thread.join();
        System.out.println("Main thread is over.");
    }
}
