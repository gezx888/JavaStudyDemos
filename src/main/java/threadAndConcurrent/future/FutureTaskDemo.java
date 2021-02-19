package threadAndConcurrent.future;

import java.util.concurrent.*;

/**
 * @description:  演示FutureTask类的使用方法：从该类的源码继承实现关系图可以得知：该类可以有 Runnable & Future 的双重角色
 * @author: gezx
 * @date: 2021/2/19 23:50
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);  // 由构造方法设计得知，这块需要传入参数
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(integerFutureTask);   // 当做Runnable可执行的任务使用

        try{
            System.out.println("task的运行结果为：" + integerFutureTask.get());   // 通通过get方法拿取执行返回结果
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}

class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算处理任务");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
             sum += i;
        }
        return sum;
    }
}
