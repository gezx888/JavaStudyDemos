package threadAndConcurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:  实现 1000 个 打印日期的任务，用线程池实现 —— 错误演示
 * @author: gezx
 * @date: 2021/2/18 23:27
 */
public class ThreadLocalNormalUsage03 {

    // 先创建一个具有10个核心线程数的线程
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    // 放在类中省去重复建1000个对象，但是该工具类对象不是线程安全的，这样是不行的！
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        // for循环去往线程池提交任务 进行任务的执行
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage03().date(finalI);
                    System.out.println(date);
                }
            });
        }
        // 最后关闭线程池即可
        threadPool.shutdown();
    }

    public String date(int seconds){
        Date date = new Date(1000 * seconds);
        return simpleDateFormat.format(date);
    }
}
