package threadAndConcurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * threadlocal该包下面的几个程序用来一步一步演示ThreadLocal的引入的原因以及必要性的迭代程序；同时演示ThreadLocal的两个
 * 常见的使用场景
 *
 * @description: 两个线程打印日期 常规代码
 * @author: gezx
 * @date: 2021/1/18 0:04
 */
public class ThreadLocalNormalUsage00 {
    public String date(int seconds){
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(seconds * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(104707);
                System.out.println(date);
            }
        }).start();
    }
}
