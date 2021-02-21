package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:   该类演示一个由synchronized保证线程安全时候带来的一个性能问题： 因为synchronized锁是需要线程持有到锁才可以进行同步代码的
 *                  执行，所以，在没有拿到锁之前线程将一直阻塞处于等锁状态，这样有时候出现 比 不用缓存功能还差的效果，起不到缓存的作用
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache3<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache3(Computable<A, V> computeService) {
        this.computeService = computeService;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if(result == null){
            result = computeService.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache3<String,Integer> service = new ImoocCache3<>(new ExpensiveFunctionService());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = service.compute("6666");
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
                    Integer result = service.compute("6666");
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
                    Integer result = service.compute("6677");
                    System.out.println("第二次计算的结果为：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
