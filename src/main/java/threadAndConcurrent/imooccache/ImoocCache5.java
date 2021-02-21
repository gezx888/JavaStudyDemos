package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:   ImoocCache4版本演示的问题，本类继续优化 —— 本来我们的 hashMap 就是线程不安全的类，所以没必要通过加锁 synchronized 等手段来进行
 *                     同步升级，没必要，我们可以直接使用线程安全的类：ConcurrentHashMap 类进行问题解决
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache5<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache5(Computable<A, V> computeService) {
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
        ImoocCache5<String,Integer> service = new ImoocCache5<>(new ExpensiveFunctionService());
        Integer result = service.compute("666");
        System.out.println("第一次计算的结果为：" + result);
        result = service.compute("666");
        System.out.println("第二次计算的结果为：" + result);
    }
}
