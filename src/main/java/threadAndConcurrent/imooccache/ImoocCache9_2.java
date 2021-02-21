package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.MayFail;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:   本类通过一个有可能出计算失败的实现类演示了出错的可能性：从而我们通过二次迭代完成避免计算出错的问题
 *                  迭代二：缓存污染问题的解决，通过运行 ImoocCache9类程序，我们容易发现控制台一直打印 “计算失败，需要重试” 语句
 *                          说明一直没有拿到正确结果，是由于这个时候缓存里面已经有了错误的结果，所以一直出错
 *                          解决方法：需要在catch语句块里面 清除 已存在的缓存
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache9_2<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache9_2(Computable<A, V> computeService) {
        this.computeService = computeService;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
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
                cache.remove(arg);
                throw e;
            }catch (InterruptedException e){
                cache.remove(arg);
                throw e;
            }catch (ExecutionException e){
                System.out.println("计算失败，需要重试");
                cache.remove(arg);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ImoocCache9_2<String,Integer> service = new ImoocCache9_2<>(new MayFail());
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
