package badaThreadCoreCourse.stopthreads.wrongways;

/**
 * @className: WrongWayVolatile
 * @description:    该类演示通过 volatile 关键字来停止线程，当前类演示的场景下是可以正确停止的
 * @author: gezx
 * @date: 2021/5/18
 **/
public class WrongWayVolatile implements Runnable{

    private volatile boolean cancel = false;

    @Override
    public void run() {
        int num=0;
        while (num<1000000 && !cancel){
            if(num%100==0){
                System.out.println(num + "是100的倍数");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
        }
        System.out.println("线程被停止了，任务退出");
    }


    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile run = new WrongWayVolatile();
        Thread thread = new Thread(run);
        thread.start();
        Thread.sleep(5000);
        run.cancel = true;
    }


}
