package threadAndConcurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:  实现 1000 个 打印日期的任务，用线程池实现 —— 错误演示的基础上，我们可以用加锁的方法解决 线程安全 问题
 *                      用synchronized 加锁可以解决线程安全问题，但是该关键字的性能一点也不高！ 各个线程需要互相等待，对方释放了锁之后拿到锁
 *                      才可以去执行同步代码块，相当于串行执行
 *
 *                      所以：通过利用 ThreadLocal，给每一个线程分配自己的 dateFormat对象，这样可以保证了线程安全，同时高效利用内存（避免重复新建多余对象）
 * @author: gezx
 * @date: 2021/2/18 23:27
 */
public class ThreadLocalNormalUsage05 {

    // 先创建一个具有10个核心线程数的线程
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        // for循环去往线程池提交任务 进行任务的执行
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage05().date(finalI);
                    System.out.println(date);
                }
            });
        }
        // 最后关闭线程池即可
        threadPool.shutdown();
    }

    public String date(int seconds){
        Date date = new Date(1000 * seconds);
//        String s ;
//        synchronized(ThreadLocalNormalUsage05.class){
//            s = simpleDateFormat.format(date);
//        }
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal2.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
