package threadAndConcurrent.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @className: OneFuture
 * @description:    该类主要用来演示 一个Future的使用方法，可以用Future来接受Callable任务向线程池中提交执行后的返回结果
 *                      演示 lambda 形式，从而简化代码开发  或者使用内部类的形式也可以
 * @author: gezx
 * @date: 2021/2/19
 **/
public class OneFutureLambda {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

//        Callable<Integer> callable = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                Thread.sleep(2000);
//                return new Random().nextInt();
//            }
//        };
//
//        Future<Integer> future = service.submit(callable);

        Future<Integer> future = service.submit(() -> {
            Thread.sleep(2000);
            return new Random().nextInt();
        });
        try {
            Integer integer = future.get();  // 使用future.get()方法来获取Callable线程任务执行完的返回结果
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
