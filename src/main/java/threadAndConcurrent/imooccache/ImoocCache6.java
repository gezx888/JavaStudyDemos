package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:   ConcurrentHashMap 类可以解并发不安全问题，同时提高性能，各个线程可以同时进入计算方法； 所以这样也就容易引起另外一个问题
 *                  当先后同时计算两个相同的数字时候，这样在前面那个还没有完成计算把计算结果放入到缓存之前，后面计算相同数字的线程也会进入计算逻辑
 *                  并且拿到缓存里面的结果发现为空，从而自己也去计算一遍，从而引起计算重复问题，缓存没有起到相应的作用
 *                    下个版本将对这个问题进行优化
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache6<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache6(Computable<A, V> computeService) {
        this.computeService = computeService;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if(result == null){
            result = computeService.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache6<String,Integer> service = new ImoocCache6<>(new ExpensiveFunctionService());
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
