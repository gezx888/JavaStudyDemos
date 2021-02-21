package threadAndConcurrent.imooccache.computable;

/**
 * @description:  顶层计算函数接口，抽离公共的计算接口方法：缓存功能类  以及 各个具体计算业务类需要实现该计算接口
 *                  这样就可以无侵入式实现缓存功能        注意既然是顶层计算接口：所以需要设计成如下所示的泛型形式
 * @author: gezx
 * @date: 2021/2/21 9:55
 */
public interface Computable<A,V> {
    V compute(A arg) throws Exception;
}
