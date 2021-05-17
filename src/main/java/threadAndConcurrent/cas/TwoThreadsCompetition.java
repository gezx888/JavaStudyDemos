package threadAndConcurrent.cas;

/**
 * @description:    两个线程竞争，并且通过 debug模式来查看代码执行情况
 * @author: gezx
 * @date: 2021/5/1 11:50
 */
public class TwoThreadsCompetition  implements Runnable{
    private volatile int value;         // 通过volatile关键字来保证可见性

    public synchronized int compareAndSwap(int expectValue,int newValue){
        int oldValue =  value;
        if(oldValue == expectValue){
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r,"Thread 1");
        Thread t2 = new Thread(r,"Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }
}
