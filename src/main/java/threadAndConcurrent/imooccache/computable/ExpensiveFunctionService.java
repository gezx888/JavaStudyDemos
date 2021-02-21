package threadAndConcurrent.imooccache.computable;

/**
 * @description:  耗时计算的具体实现类，实现了计算接口，但是本身不具备缓存功能，而且也不需要自己去考虑缓存的事情，缓存功能类会做好
 *                  在实现计算接口的时候，业务类可以根据自己的业务需要直接指定相关的泛型了
 * @author: gezx
 * @date: 2021/2/21 9:59
 */
public class ExpensiveFunctionService implements Computable<String,Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(4000);
        return Integer.valueOf(arg);
    }
}
