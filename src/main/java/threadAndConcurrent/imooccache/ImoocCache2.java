package threadAndConcurrent.imooccache;

import threadAndConcurrent.imooccache.computable.Computable;
import threadAndConcurrent.imooccache.computable.ExpensiveFunctionService;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:   ImoocCache1版本的缓存功能与计算业务方法聚合在一个类里面完成了，这样将严重引起 与业务聚合，缓存没有与业务进行分离
 *                  做到无入侵式。
 *
 * @author: gezx    该类将要演示： 用装饰者模式，给计算业务类自动添加缓存功能 —— 这是一个只提供缓存功能的类，具体计算业务需要各自实现类
 *                      去完成自己的计算逻辑，所以需要抽象出一个顶层计算接口：该缓存功能类 以及 各个计算业务实现类都需要去 实现抽象 计算接口
 * @date: 2021/2/21 9:45
 */
public class ImoocCache2<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();

    // 需要知道装饰者模式是为 已有的 东西进行装饰，所以会有一个相关的业务类引用
    private final Computable<A,V> computeService;   // 该类为 各个业务类 提供缓存功能，所以需要持有一个各业务类的顶层引用，以便后面使用业务类的计算方法

    public ImoocCache2(Computable<A, V> computeService) {
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
        ImoocCache2<String,Integer> service = new ImoocCache2<>(new ExpensiveFunctionService());
        Integer result = service.compute("666");
        System.out.println("第一次计算的结果为：" + result);
        result = service.compute("666");
        System.out.println("第二次计算的结果为：" + result);
    }
}
