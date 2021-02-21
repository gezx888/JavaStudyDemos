package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @description:   ImoocCache7 版本还是存在极小可能重复计算的可能性 —— 两个计算相同任务的线程非常前后脚的启动计算然后几乎相同时间去拿，
 *                    这样拿到的两个结果会出现都为空的时候，从而产生重复计算的可能性。
 *                    本类通过 原子操作 putIfAbsent 来解决此类问题
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache8<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache8(Computable<A, V> computeService) {
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
            future = cache.putIfAbsent(arg, ft);  //原子操作保证，并且当同时两个线程操作时，第一个线程放时返回的结果是放之前键为 null 的结果，那么值也为null
            if(future == null){     // 这样肯定会出现两个线程前后put这是由currentHashMap线程安全保证的，从而稍稍稍晚的第二个线程放的时候会返回上一个线程放的
                future = ft;      // future对象，这样就不会进入if语句，从而避免了重复计算了      从程序运行结果可以看出，只打印了两次下面输出语句
                System.out.println("从FutureTask调用了计算函数");
                ft.run();
            }
        }
        return future.get();
    }

    public static void main(String[] args) throws Exception {
        ImoocCache8<String,Integer> service = new ImoocCache8<>(new ExpensiveFunctionService());
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
