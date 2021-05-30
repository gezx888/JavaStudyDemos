package moduleexercise.reflect.proxydemo.proxydesignpattern;

/**
 * @description:   该类是 代理类 & 业务实现类 需要实现的接口类     Jdk的动态代理 底层通过反射实现
 *                      使用Jdk的 动态生成代理对象 要求业务实现类 必须实现接口
 * @author: gezx
 * @date: 2021/5/30 23:18
 */
public interface Subject {
    void say(String name,int age);
}
