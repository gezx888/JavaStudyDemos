package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:   本类通过一个有可能出计算失败的实现类演示了出错的可能性：从而我们通过二次迭代完成避免计算出错的问题
 *                  迭代一：我们通三个不同的catch 语句块来追踪不同的错误，当时取消或是中断的时候，属于人为直接抛出相应异常；
 *                          当时由于业务类出现计算失败的时候，我们需要进行计算任务的重试，以拿到正确结果，通过一个while(true) 循环保证
 *                              这个需要对业务熟悉知道可以通过循环来保证拿到正确结果的基础上完成
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache9<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache9(Computable<A, V> computeService) {
        this.computeService = computeService;
    }

    @Override
    public V compute(A arg) {
        while(true){
            Future<V> future = cache.get(arg);
            if(future == null){
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computeService.compute(arg);
                    }
                };

                FutureTask<V> ft = new FutureTask<>(callable);
                future = cache.putIfAbsent(arg, ft);
                if(future == null){
                    future = ft;
                    System.out.println("从FutureTask调用了计算函数");
                    ft.run();
                }
            }
            try{
                return future.get();
            }catch (CancellationException e){
                System.out.println("任务被取消了");
                throw e;
            }catch (InterruptedException e){

            }catch (ExecutionException e){
                System.out.println("计算失败，需要重试");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ImoocCache9<String,Integer> service = new ImoocCache9<>(new MayFail());
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
