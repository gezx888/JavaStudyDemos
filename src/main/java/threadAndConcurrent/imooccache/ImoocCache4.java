package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:   ImoocCache3版本演示的问题，可以通过缩小锁同步代码块的粒度进行一个简单的优化，如本类所示，但是这样依然保证不了线程安全问题
 *
 * @author: gezx
 * @date: 2021/2/21 9:45
 */
public class ImoocCache4<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();

    private final Computable<A,V> computeService;

    public ImoocCache4(Computable<A, V> computeService) {
        this.computeService = computeService;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if(result == null){
            result = computeService.compute(arg);
            synchronized(this){
                cache.put(arg,result);   // 往hashMap里面写数据，加锁 变成同步代码块
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache4<String,Integer> service = new ImoocCache4<>(new ExpensiveFunctionService());
        Integer result = service.compute("666");
        System.out.println("第一次计算的结果为：" + result);
        result = service.compute("666");
        System.out.println("第二次计算的结果为：" + result);
    }
}
