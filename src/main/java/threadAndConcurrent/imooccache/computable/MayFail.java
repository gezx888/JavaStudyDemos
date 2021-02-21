package threadAndConcurrent.imooccache.computable;

import java.io.IOException;

/**
 * @description:   业务计算另一实现类，这个实现类是一个有概率计算失败的实现类
 * @author: gezx
 * @date: 2021/2/21 13:50
 */
public class MayFail implements Computable<String,Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if(random > 0.5){
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
