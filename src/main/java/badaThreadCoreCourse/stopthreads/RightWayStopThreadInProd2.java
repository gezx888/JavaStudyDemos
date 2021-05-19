package badaThreadCoreCourse.stopthreads;

/**
 * @className: RightWayStopThreadInProd2
 * @description:        最佳实践2：被调用方法不直接在方法签名中抛出异常，而是在catch语句块中去调用 Thread.currentThread().interrupt() 方法来恢复设置中断状态，
 *                      以便于在后续执行中，被调用方能够检查到刚刚发生的中断。
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RightWayStopThreadInProd2 implements Runnable{

    @Override
    public void run() {
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("该线程被中断了，程序结束");
                break;
            }
            reInturrept();
        }
    }

    private void reInturrept() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();     // 恢复设置中断状态
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RightWayStopThreadInProd2 run = new RightWayStopThreadInProd2();
        Thread thread = new Thread(run);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
