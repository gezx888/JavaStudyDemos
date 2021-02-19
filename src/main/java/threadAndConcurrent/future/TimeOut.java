package threadAndConcurrent.future;

import java.util.concurrent.*;

/**
 * @description:  演示get的超时方法，需要注意超时后的处理，调用future.cancel()。 演示cancel方法传入true和false的区别，
 *                  代表是否中断正在执行的任务。 这个需要根据具体情况去是否真正中断执行的任务
 * @author: gezx
 * @date: 2021/2/19 23:19
 */
public class TimeOut {

    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");
    private static final ExecutorService exce = Executors.newFixedThreadPool(10);

    static class Ad{
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad>{

        @Override
        public Ad call() throws Exception {
            try{
                Thread.sleep(3000);   // 模拟选播广告业务处理耗时
            }catch(InterruptedException e){
                System.out.println("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("旅游订票哪家强？找某程");
        }
    }

    public void printAd(){
        Future<Ad> future = exce.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = future.get(2000, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            ad = new Ad("被中断时候的默认广告");
        }catch(ExecutionException e){
            ad = new Ad("异常时候的默认广告");
        }catch (TimeoutException e){
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        exce.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        TimeOut timeout = new TimeOut();
        timeout.printAd();
    }

}
