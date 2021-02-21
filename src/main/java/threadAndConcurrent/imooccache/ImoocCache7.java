package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @description:   本类通过利用 FutureTask 类，避免重复计算问题
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache7<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache7(Computable<A, V> computeService) {
        this.computeService = computeService;
    }

    @Override
    public V compute(A arg) throws Exception {
        Future<V> future = cache.get(arg);
        if(future == null){
            Callable<V> callable = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return computeService.compute(arg);
                }
            };

            FutureTask<V> ft = new FutureTask<>(callable);
            future = ft;
            cache.put(arg,ft);
            System.out.println("从FutureTask调用了计算函数");
            ft.run();
        }
        return future.get();
    }

    public static void main(String[] args) throws Exception {
        ImoocCache7<String,Integer> service = new ImoocCache7<>(new ExpensiveFunctionService());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = service.compute("666");
                    System.out.println("第一次计算的结果为：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = service.compute("666");
                    System.out.println("第三次计算的结果为：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = service.compute("667");
                    System.out.println("第二次计算的结果为：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
