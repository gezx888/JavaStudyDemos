package badaThreadCoreCourse.stopthreads;

/**
 * @className: RightWayStopThreadWithoutSleep
 * @description:        线程run方法里面没有sleep 或者 wait方法时候停止线程 demo演示
 *                     !Thread.currentThread().isInterrupted() 当在while循环里面没有能够响应中断的方法存在的时候，其他线程直接通过 interrupt() 方法去中断某一个线程
 *                     的时候，该线程不会响应中断的请求，直等到线程任务结束退出，
 *                     这个时候我们可以在每一次迭代循环进入的时候加一个检测条件是否被中断的条件 !Thread.currentThread().isInterrupted() 只有不被中断的时候才进入循环
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RightWayStopThreadWithoutSleep implements Runnable {
    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num<= Integer.MAX_VALUE/2){
            if(num%10000==0){
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        RightWayStopThreadWithoutSleep task = new RightWayStopThreadWithoutSleep();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(200);
        thread.interrupt();
    }
}
