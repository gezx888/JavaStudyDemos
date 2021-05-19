package badaThreadCoreCourse.stopthreads;

/**
 * @className: RightWayStopThreadInProd
 * @description:        最佳实践：在被调用的方法中用了sleep等方法的时候，处理catch了InterruptedExcetion之后的优先选择是：在方法签名中抛出异常，
 *                  这样在调用方run() 方法中就会被强制进行 try catch了。  这样run方法的编写者就需要正确的在catch语句块中去处理中断信息了 比如保存日志、停止信息等。
 *                  同时，这里可以看到interrupt方法会清楚中断标志位，需要在catch语句块中 Thread.currentThread().interrupt(); 去恢复设置中断状态
 *
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RightWayStopThreadInProd implements Runnable{


    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()){
            System.out.println("go");
            try {
                interrupt();
            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                // 保存日志、停止程序等
//                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void interrupt() throws InterruptedException {
        Thread.sleep(1000);
    }


    public static void main(String[] args) throws InterruptedException {
        RightWayStopThreadInProd run = new RightWayStopThreadInProd();
        Thread thread = new Thread(run);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
