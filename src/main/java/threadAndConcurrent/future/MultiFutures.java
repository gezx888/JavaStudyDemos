package threadAndConcurrent.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @className: MultiFutures
 * @description:            演示批量提交任务时候，可以使用list来批量接受结果
 * @author: gezx
 * @date: 2021/2/19
 **/
public class MultiFutures {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);

        ArrayList<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {      // 可以使用list来批量接受任务，将任务执行返回结果添加到list中，然后后面再一起遍历输出
            Future<Integer> future = service.submit(new CallableTask());
            futures.add(future);
        }

        Thread.sleep(5000);

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                Integer value = future.get();
                System.out.println(value);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }

    static class CallableTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return new Random().nextInt();
        }
    }

}
