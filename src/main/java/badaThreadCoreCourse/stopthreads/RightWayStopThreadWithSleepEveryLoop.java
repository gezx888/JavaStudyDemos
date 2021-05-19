package badaThreadCoreCourse.stopthreads;

/**
 * @className: RightWayStopThreadWithSleepEveryLoop
 * @description:        执行过程中，如果在每一次循环中都调用sleep或者wait方法的话，那么不需要在每一次迭代的时候都检查是否已中断，可以直接通过这些方法能够抛出异常
 *                      响应中断的能力进行中断；
 *                      但是这个时候需要注意try catch语句块的位置（因为由于Java设计者对sleep方法的设计决定的，内部清除了中断标志位）所以在循环内部进行try catch语句块
 *                      的时候也是不会被中断的。
 * @author: gezx
 * @date: 2021/5/18
 **/
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {

        // 如果while里面放try/catch，会导致中断失效
//        Runnable runnable = () -> {
//            int num=0;
//            while (num<=10000){
//                if(num%100==0){
//                    System.out.println(num + "是100的倍数");
//                }
//                num++;
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };

        Runnable runnable = () -> {
            int num=0;
            try{
                while (num<=10000){
                    if(num%100==0){
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
