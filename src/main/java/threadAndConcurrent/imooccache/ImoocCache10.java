package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:   出于安全性考虑，缓存需要设置有效期，需要具有到期自动失效功能；否则如果缓存一直不失效，那么将有可能带来缓存数据不一致等问题
 *                  （既然是到期自动失效，这个时候我们可以使用之前学过的具有延时执行任务特性的延时执行线程池来完成需求）
 *                  本类：通过几个步骤，最终将实现缓存自动失效功能 && 以及可以设置随机过期时间，防止缓存同时失效，在高并发请求的情况下导致缓存穿透
 *                  缓存雪崩等。
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache10<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache10(Computable<A, V> computeService) {
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

    // 如果下面主函数调用该方法的时候将会得到一个产生随机过期时间的缓存功能的实现
    public V computeRandomExpire(A arg) throws InterruptedException {
        long randomExpire = (long) (Math.random() * 10000);
        return compute(arg,randomExpire);
    }

    public final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    // 来一个方法的重载： 带有一个缓存失效时间的计算方法
    public V compute(A arg,long expire) throws InterruptedException {
        if(expire > 0){
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    expire(arg);      // 秉着一件方法只做一件事原则，这里单独抽出一个方法，进行业务实现
                }
            },expire,TimeUnit.MILLISECONDS);
        }
        return compute(arg);
    }

    // 清除过期时间到了的缓存数据，同时为了程序的兼容性利用了 future.isDone()方法等保证
    private synchronized void expire(A key) {
        Future<V> future = cache.get(key);
        if(future != null){
            if(!future.isDone()){
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(key);
        }
    }

    public static void main(String[] args) throws Exception {
        ImoocCache10<String,Integer> service = new ImoocCache10<>(new ExpensiveFunctionService());
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
                    Integer result = service.compute("666",7000L);
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

         Thread.sleep(6000L);
        Integer result = service.compute("666");
        System.out.println("主线程的计算结果：" + result);
    }
}
