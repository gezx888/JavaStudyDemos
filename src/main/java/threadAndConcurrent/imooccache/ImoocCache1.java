package threadAndConcurrent.imooccache;

import java.util.HashMap;

/**
 * 该包下面的练习一个 简单的缓存的逐渐迭代过程，从最最简单点的 hashMap 开始迭代
 *
 * @description:   最简单的缓存形式：hashMap
 * @author: gezx
 * @date: 2021/2/21 9:28
 */
public class ImoocCache1 {

    private final HashMap<String,Integer> cache = new HashMap<>();

    // 由于hashMap为线程不安全，所以这版暂时使用 synchronized 来保证线程安全，但是synchronized性能不高，后续版本继续迭代优化
    public synchronized Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        if(result == null){
            result = doComputer(userId);
            cache.put(userId,result);
        }
        return result;
    }

    private Integer doComputer(String userId) throws InterruptedException {
        Thread.sleep(3000);      // 模拟计算耗时
        return Integer.valueOf(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        ImoocCache1 imoocCache1 = new ImoocCache1();
        System.out.println("开始计算了");
        Integer result = imoocCache1.computer("13");
        System.out.println("第一次计算结果：" + result);
        result = imoocCache1.computer("13");         // 第二次计算相同的值时候会利用缓存功能
        System.out.println("第二次计算结果：" + result);
    }


}
