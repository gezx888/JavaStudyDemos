package moduleexercise.reflect.proxydemo.proxydesignpattern;

/**
 * @description:        模拟项目中 需要被代理的 业务实现类
 * @author: gezx
 * @date: 2021/5/30 23:22
 */
public class RealSubject implements Subject{
    @Override
    public void say(String name, int age) {
        System.out.println(name + "  " + age);
    }
}
