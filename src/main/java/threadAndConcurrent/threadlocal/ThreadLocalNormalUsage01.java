package threadAndConcurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 需求：需要打印多个日期，从而你会想到用多个线程打印，当然在数量不够大的时候你可以使用for循环来实现需求
 * 没问题，10个线程打印日期
 * @author: gezx
 * @date: 2021/1/18 0:13
 */
public class ThreadLocalNormalUsage01 {
    public String date(int seconds){
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(seconds * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int count = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(count);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }
    }
}
