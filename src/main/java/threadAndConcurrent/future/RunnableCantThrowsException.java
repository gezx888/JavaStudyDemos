package threadAndConcurrent.future;

/**
 * @description:  该类主要演示在 run 方法中无法抛出checked Exception —— 这点可以从源码可以看得出结论 ，由源码这样的设计
 * @author: gezx
 * @date: 2021/2/19 23:13
 */
public class RunnableCantThrowsException {

    public void add() throws Exception{

    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {   // 因为Runnable接口类的runner方法的设计缘故。一般都是在run方法中进行异常追踪处理
                try {
                    throw new Exception();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }
}
