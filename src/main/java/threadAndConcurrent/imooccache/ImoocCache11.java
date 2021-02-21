package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:   本类主要 演示复习了之前学到的 CountDownLatch 类  &&  ThreadLocal 类的使用
 *                      使用CountDownLatch类可以营造多个线程同时放行的压力测试环境。
 * @author: gezx
 * @date: 2021/2/21 15:11
 */
public class ImoocCache11 {

    static ImoocCache10<String,Integer> expensiveComputer = new ImoocCache10<>(new ExpensiveFunctionService());

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            service.submit( () -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    countDownLatch.await();
                    SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
                    String time = dateFormat.format(new Date());
                    System.out.println(Thread.currentThread().getName() + "   " + time + "被放行");
                    result = expensiveComputer.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }
        Thread.sleep(5000);    // 让各个线程先做好准备 到时候统一放行
        countDownLatch.countDown();
        service.shutdown();
    }

}

class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){

        // 每一个线程会调用本方法一次，用于初始化
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("mm:ss");
        }

        // 首次调用本方法时，会调用 initialValue(); 后面的调用会返回第一次创建的值
        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };

}
