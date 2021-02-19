package threadAndConcurrent.future;

import java.util.concurrent.*;

/**
 * @className: GetException
 * @description:    演示future的get方法过程中抛出异常，for循环是为了演示抛出exception的时机：并不是说一产生异常就立马抛出，而是直等到我们调用get执行结果返回的时候，才会抛出
 * @author: gezx
 * @date: 2021/2/19
 **/
public class GetException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());

        try{
            for (int i = 0; i < 5; i++) {   // for循环是为了演示抛出exception的时机
                System.out.println(i);
                Thread.sleep(500);
            }

            System.out.println(future.isDone());
            future.get();    // 调用get执行结果返回的时候抛出异常

        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");
        }
    }

    static class CallableTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
