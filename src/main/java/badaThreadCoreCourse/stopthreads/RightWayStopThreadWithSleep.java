package badaThreadCoreCourse.stopthreads;

/**
 * @className: RightWayStopThreadWithSleep
 * @description:            带有sleep的中断线程的写法,该sleep方法是在while循环外面,这个时候会发现不管是否在循环入口上加还是没加
 *                  !Thread.currentThread().isInterrupted() 中断检测条件都会发现中断不会被中断，
 *                  （具体原因是由于Java设计者对sleep方法的设计决定的，内部清除了中断标志位）  该演示demo可以 RightWayStopThreadWithSleepEveryLoop 进行对比。
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RightWayStopThreadWithSleep {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {         // 匿名内部类的形式创建可运行任务
            int num=0;
            try{
                while (num<=300 && !Thread.currentThread().isInterrupted()){
                    if(num%100==0){
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();

    }
}
